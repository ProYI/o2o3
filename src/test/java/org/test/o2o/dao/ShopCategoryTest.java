/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ShopCategoryTest
 * Author: Administrator
 * Date: 2018-09-11 22:22
 * Description: ShopCategory测试类
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dao;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.test.o2o.entity.ShopCategory;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**

 * 〈功能简述〉<br>

 * 〈ShopCategory测试类〉

 *

 * @author Administrator

 * @create 2018-09-11

 * @since 1.0.0

 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopCategoryTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    @Ignore
    public void testQueryShopCategory() {
        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
        assertEquals(1, shopCategoryList.size());

        //测试子类别
        ShopCategory testCategory = new ShopCategory();
        ShopCategory parentCategory = new ShopCategory();
        parentCategory.setShopCategoryId(1L);
        testCategory.setParent(parentCategory);
        shopCategoryList = shopCategoryDao.queryShopCategory(testCategory);
        assertEquals(1, shopCategoryList.size());
        System.out.println(shopCategoryList.get(0).getShopCategoryName());

        shopCategoryList = shopCategoryDao.queryShopCategory(null);
        assertEquals(1, shopCategoryList.size());
    }
}