/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductSellDailyDaoTest
 * Author: Administrator
 * Date: 2018-10-15 0:35
 * Description: 店铺中商品的销量统计
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dao;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.test.o2o.entity.ProductSellDaily;
import org.test.o2o.entity.Shop;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * 〈店铺中商品的销量统计〉
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductSellDailyDaoTest {
    @Autowired
    private ProductSellDailyDao productSellDailyDao;

    /**
    * 测试添加功能
    */
    @Test
    public void testAInsertProductSellDaily() {
        //创建商品日销量统计
        int effectedNum = productSellDailyDao.insertProductSellDaily();
        assertEquals(1, effectedNum);
    }

    /**
    * 测试查询功能
    */
    @Test
    public void testBQueryProductSellDaily() {
        ProductSellDaily productSellDaily = new ProductSellDaily();
        //叠加店铺去查询
        Shop shop = new Shop();
        shop.setShopId(1L);
        productSellDaily.setShop(shop);
        List<ProductSellDaily> productSellDailyList = productSellDailyDao.queryProductSellDailyList(productSellDaily, null, null);
        assertEquals(1, productSellDailyList.size());
    }
}