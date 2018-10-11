/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductCategoryServiceImpl
 * Author: Administrator
 * Date: 2018-09-16 12:13
 * Description: 商品类别接口实现类
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.o2o.dao.ProductCategoryDao;
import org.test.o2o.dao.ProductDao;
import org.test.o2o.dto.ProductCategoryExecution;
import org.test.o2o.entity.ProductCategory;
import org.test.o2o.enums.ProductCategoryStateEnum;
import org.test.o2o.exceptions.ProductCategoryOperationException;
import org.test.o2o.service.ProductCategoryService;

import java.util.List;

/**

 * 〈功能简述〉<br>

 * 〈商品类别接口实现类〉

 *

 * @author Administrator

 * @create 2018-09-16

 * @since 1.0.0

 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Autowired
    private ProductDao productDao;

    //查询指定某个店铺下的所有商品类别信息
    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Override
    @Transactional
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        //productCategoryList是否为空的状态判断
        if (productCategoryList!=null && productCategoryList.size()>0) {
            try {
                int effectedNum=productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (effectedNum<=0) {
                    throw new ProductCategoryOperationException("店铺类别创建失败");
                } else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            } catch (Exception e) {
                throw new ProductCategoryOperationException("batchAddProductCategory error:"+e.getMessage());
            }
        } else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }

    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException {
        //将此商品类别下的商品的类别Id置为空 解除tb_product里的商品与该productcategoryId的关联
        try {
            int effectedNum = productDao.updateProductCategoryToNull(productCategoryId);
            if(effectedNum <= 0){
                throw new ProductCategoryOperationException("店铺类别更新失败");
            }
        }catch (Exception e){
            throw new ProductCategoryOperationException("deleteProductCategory error:"+e.getMessage());
        }

        //删除productCategory
        try{
            int effectedNum=productCategoryDao.deleteProductCategory(productCategoryId,shopId);
            if(effectedNum<=0){
                throw new ProductCategoryOperationException("商品类别删除失败");
            }else{
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        }catch (Exception e){
            throw new ProductCategoryOperationException("deleteProductCategory error:"+e.getMessage());
        }
    }
}