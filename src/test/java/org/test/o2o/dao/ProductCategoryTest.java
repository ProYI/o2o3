/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductCategoryTest
 * Author: Administrator
 * Date: 2018-09-16 11:57
 * Description: 商品类别测试类
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dao;


import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.test.o2o.entity.ProductCategory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**

 * 〈功能简述〉<br>

 * 〈商品类别测试类〉

 *

 * @author Administrator

 * @create 2018-09-16

 * @since 1.0.0

 */
/*
FixMethodOrder控制junit测试的执行顺序
MethodSorters.NAME_ASCENDING是根据定义的方法顺序，避免删除操作先于查询执行等问题。命名testA->testB->testC的顺序
MethodSorters.JVM是根据JVM获取的顺序执行，不常用
形成了UT的回环
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryTest {
    @Autowired
    ProductCategoryDao productCategoryDao;

    @Test
    @Ignore
    public void testBQueryProductCategoryList() {
        long shopId = 1;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        System.out.println("该店铺自定义类别数为：" + productCategoryList.size());
    }

    @Test
    @Ignore
    public void testABatchInsertProductCategory() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryName("ut测试分类1");
        productCategory.setPriority(1);
        productCategory.setCreateTime(new Date());
        productCategory.setShopId(1L);
        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("ut测试分类2");
        productCategory2.setPriority(2);
        productCategory2.setCreateTime(new Date());
        productCategory2.setShopId(1L);
        List<ProductCategory> productCategoryList=new ArrayList<>();
        productCategoryList.add(productCategory);
        productCategoryList.add(productCategory2);
        int effectedNum=productCategoryDao.batchInsertProductCategory(productCategoryList);
        System.out.println(effectedNum);
    }

    @Test
    @Ignore
    public void testCDeleteProductCategory() {
        long shopId=1;
        List<ProductCategory> productCategoryList=productCategoryDao.queryProductCategoryList(shopId);
        for(ProductCategory pc:productCategoryList){
            if("ut测试分类1".equals(pc.getProductCategoryName())||"ut测试分类2".equals(pc.getProductCategoryName())){
                int effectedNum=productCategoryDao.deleteProductCategory(pc.getProductCategoryId(),shopId);
                System.out.println(effectedNum);
            }
        }
    }
}