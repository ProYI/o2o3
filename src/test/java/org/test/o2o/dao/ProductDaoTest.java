/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductDaoTest
 * Author: Administrator
 * Date: 2018-09-17 21:03
 * Description: 商品详情测试
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dao;


import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.test.o2o.entity.Product;
import org.test.o2o.entity.ProductCategory;
import org.test.o2o.entity.ProductImg;
import org.test.o2o.entity.Shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**

 * 〈功能简述〉<br>

 * 〈商品详情测试〉

 *

 * @author Administrator

 * @create 2018-09-17

 * @since 1.0.0

 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    @Test
    @Ignore
    public void testAInsertProduct() throws Exception{
        Shop shop1=new Shop();
        shop1.setShopId(1L);
        ProductCategory pc1=new ProductCategory();
        pc1.setProductCategoryId(1L);
        //初始化三个商品实例并添加进shopId为1的店铺里,
        //同时商品类别id也为1
        Product product1=new Product();
        product1.setProductName("测试1");
        product1.setProductDesc("测试Desc1");
        product1.setImgAddr("test1");
        product1.setPriority(1);
        product1.setEnableStatus(1);
        product1.setCreateTime(new Date());
        product1.setLastEditTime(new Date());
        product1.setShop(shop1);
        product1.setProductCategory(pc1);

        Product product2=new Product();
        product2.setProductName("测试2");
        product2.setProductDesc("测试Desc2");
        product2.setImgAddr("test2");
        product2.setPriority(2);
        product2.setEnableStatus(1);
        product2.setCreateTime(new Date());
        product2.setLastEditTime(new Date());
        product2.setShop(shop1);
        product2.setProductCategory(pc1);

        Product product3=new Product();
        product3.setProductName("test3");
        product3.setProductDesc("测试Desc3");
        product3.setImgAddr("test3");
        product3.setPriority(3);
        product3.setEnableStatus(1);
        product3.setCreateTime(new Date());
        product3.setLastEditTime(new Date());
        product3.setShop(shop1);
        product3.setProductCategory(pc1);

        //判断添加是否成功
        int effectedNum=productDao.insertProduct(product1);
        System.out.println(effectedNum);
        effectedNum=productDao.insertProduct(product2);
        System.out.println(effectedNum);
        effectedNum=productDao.insertProduct(product3);
        System.out.println(effectedNum);
    }

    @Test
    @Ignore
    public void testBQueryProductList() throws Exception {
        Product product = new Product();
        //分页查询,预期返回三条结果
        List<Product> productList = productDao.queryProductList(product, 0, 3);
        Assert.assertEquals(3, productList.size());
        int count = productDao.queryProductCount(product);
        Assert.assertEquals(7, count);
        //查询名称为测试的商品总数
        //使用商品名称模糊查询,预期返回两条结果
        product.setProductName("测试");
        productList = productDao.queryProductList(product, 0, 3);
        Assert.assertEquals(3, productList.size());
        count = productDao.queryProductCount(product);
        Assert.assertEquals(5, count);
    }

    @Test
    @Ignore
    public void testCQueryProductByProductId() throws Exception {
        long productId = 1;
        //初始化两个商品详情图实例作为productId为1的商品下的详情图片
        //批量插入到商品详情列表中
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("图片1");
        productImg1.setImgDesc("测试图片1");
        productImg1.setPriority(1);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(productId);
        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("图片2");
        productImg2.setPriority(1);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(productId);
        List<ProductImg> productImgList = new ArrayList<>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        int effectedNum = productImgDao.batchInsertProductImg(productImgList);
        System.out.println(effectedNum);
        //查询productId为1的商品信息并校验返回的详情图实例列表size是否为2
        Product product = productDao.queryProductById(productId);
        System.out.println(product.getProductImgList().size());
        //删除新增的这两个商品详情图实例
        effectedNum = productImgDao.deleteProductImgByProductId(productId);
        System.out.println(effectedNum);
    }

    @Test
    @Ignore
    public void testDUpdateProduct() throws Exception {
        Product product = new Product();
        ProductCategory pc = new ProductCategory();
        Shop shop = new Shop();
        shop.setShopId(1L);
        pc.setProductCategoryId(4L);
        product.setProductId(1L);
        product.setShop(shop);
        product.setProductName("第二个产品");
        product.setProductCategory(pc);
        //修改productId为1的商品名称
        //以及商品类别并校验影响的行数是否为1
        int effectedNum = productDao.updateProduct(product);
        System.out.println(effectedNum);
    }

    @Test
    @Ignore
    public void testEDeleteShopAuthMap() throws Exception {
        //将productCategoryId为2的商品类别下面的商品的商品类别设置为空
        int effectedNum = productDao.updateProductCategoryToNull(9L);
        Assert.assertEquals(1, effectedNum);
    }
}