/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductServiceImp
 * Author: Administrator
 * Date: 2018-09-18 12:09
 * Description: 商品相关业务
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.o2o.dao.ProductDao;
import org.test.o2o.dao.ProductImgDao;
import org.test.o2o.dto.ImageHolder;
import org.test.o2o.dto.ProductExecution;
import org.test.o2o.entity.Product;
import org.test.o2o.entity.ProductImg;
import org.test.o2o.enums.ProductStateEnum;
import org.test.o2o.exceptions.ProductOperationException;
import org.test.o2o.service.ProductService;
import org.test.o2o.util.ImageUtil;
import org.test.o2o.util.PageCalculator;
import org.test.o2o.util.PathUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**

 * 〈功能简述〉<br>

 * 〈商品相关业务〉

 *

 * @author Administrator

 * @create 2018-09-18

 * @since 1.0.0

 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    /**
     * 添加商品步骤：
     * 1.处理缩略图,获取缩略图相对路径并赋值给product
     * 2.往tb_product写入商品信息,获取productId
     * 3.结合productId批量处理商品详情图
     * 4.将商品详情图列表批量插入tb_product_img中
     * @param product
     * @param imageHolder //缩略图
     * @param imageHolderList //详情图列表
     * @return
     * @throws ProductOperationException
     * */
    @Override
    @Transactional
    public ProductExecution addProduct(Product product, ImageHolder imageHolder, List<ImageHolder> imageHolderList) throws ProductOperationException {
        //空值判断
        if(product!=null && product.getShop() !=null && product.getShop().getShopId()!=null){
            //给商品设置上默认属性
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            //默认为上架状态
            product.setEnableStatus(1);
            //若商品缩略图不为空则添加
            if(imageHolder!=null){
                addThumbnail(product,imageHolder);
            }
            try {
                //创建商品
                int effectedNum=productDao.insertProduct(product);
                if(effectedNum<=0){
                    throw new ProductOperationException("创建商品失败");
                }
            }catch (Exception e){
                throw new ProductOperationException("创建商品失败:"+e.toString());
            }
            //若商品详情图不为空则添加
            if(imageHolderList!=null &&imageHolderList.size()>0){
                addProductImgList(product,imageHolderList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS,product);
        }else{
            //传入为空则返回空值错误信息
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    //通过商品Id查询唯一的商品信息
    @Override
    public Product getProductById(long productId) {
        return productDao.queryProductById(productId);
    }


    @Override
    @Transactional
    /*
    商品信息更新
    1.若缩略图参数有值,则处理缩略图
      若原先存在缩略图则先删除再添加新图,之后获取缩略图相对路径并赋值给product
    2.若商品详情图列表参数有值,对商品详情图片列表进行同样的操作
    3.将tb_product_img下面的该商品原先的商品详情图记录全部清除
    4.更新tb_product,tb_product_img的信息
     */

    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) {
        //空值判断
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            //给商品设置上默认属性
            product.setLastEditTime(new Date());
            //若商品缩略图不为空并且原有缩略图不为空则删除原有缩略图并添加
            if (thumbnail != null) {
                //先获取一遍原有信息,因为原来的信息里有原图片地址
                Product tempProduct = productDao.queryProductById(product.getProductId());
                if (tempProduct.getImgAddr() != null) {
                    ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
                }
                addThumbnail(product, thumbnail);
            }
            //如果有新存入的商品详情图,则将原先的删除,并添加新的图片
            if (productImgHolderList != null && productImgHolderList.size() > 0) {
                deleteProductImgList(product.getProductId());//删除图片
                addProductImgList(product, productImgHolderList);//添加图片
            }
            try {
                //更新商品信息
                int effectedNum = productDao.updateProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("更新商品信息失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS, product);
            } catch (Exception e) {
                throw new RuntimeException("更新商品信息失败:" + e.toString());
            }
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    /**
    * 功能描述:
    *〈查询商品列表并分页〉
    * @param: productCondition
    * @param: pageIndex
    * @param: pageSize
    * @return:
    */
    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        //页面转换成数据库的行码,并调用doa层取回指定页码的商品列表
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
        //基于同样的查询条件返回该查询条件下的商品总数
        int count = productDao.queryProductCount(productCondition);
        ProductExecution pe = new ProductExecution();
        pe.setProductList(productList);
        pe.setCount(count);
        return pe;
    }

    /**
     * 删除某个商品下的所有详情图
     * @param productId
     */
    private void deleteProductImgList(long productId){
        //根据productId获取原来的图片
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        //干掉原来的图片
        for(ProductImg productImg:productImgList){
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());
        }
        //删除数据库里原有图片的信息
        productImgDao.deleteProductImgByProductId(productId);
    }

    /**
     * 批量添加图片
     * @param product
     * @param imageHolderList
     */
    private void addProductImgList(Product product, List<ImageHolder> imageHolderList) {
        //获取图片存储路径,这里直接存放到相应店铺的文件夹下
        String targetAddr = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList=new ArrayList<>();

        //遍历图片一次去处理,并添加进productImg实体类里
        for(ImageHolder productImgHolder:imageHolderList){
            String imgAddr = ImageUtil.generateNormalImg(productImgHolder,targetAddr);
            ProductImg prodcutImg = new ProductImg();
            prodcutImg.setImgAddr(imgAddr);
            prodcutImg.setProductId(product.getProductId());
            prodcutImg.setCreateTime(new Date());
            productImgList.add(prodcutImg);
        }
        //如果确实是有图片需要添加的,就执行批量添加操作
        if(productImgList.size()>0){
            try{
                int effectedNum=productImgDao.batchInsertProductImg(productImgList);
                if(effectedNum<=0){
                    throw new ProductOperationException("创建商品详情图片失败");
                }
            }catch (Exception e){
                try {
                    throw new ProductOperationException("创建商品详情图片失败"+e.toString());
                } catch (ProductOperationException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 添加缩略图
     * @param product
     * @param imageHolder
     */
    private void addThumbnail(Product product, ImageHolder imageHolder){
        String dest= PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr= ImageUtil.generateThumbnail(imageHolder,dest);
        product.setImgAddr(thumbnailAddr);
    }
}