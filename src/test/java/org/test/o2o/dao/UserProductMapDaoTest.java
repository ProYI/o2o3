/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserProductMapDaoTest
 * Author: Administrator
 * Date: 2018-10-14 18:00
 * Description: 用户购买商品记录
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
import org.test.o2o.entity.PersonInfo;
import org.test.o2o.entity.Product;
import org.test.o2o.entity.Shop;
import org.test.o2o.entity.UserProductMap;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * 〈用户购买商品记录〉
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserProductMapDaoTest {
    @Autowired
    private UserProductMapDao userProductMapDao;

    /**
    * 测试添加功能
    */
    @Test
    public void testAInsertUserProductMap() {
        //创建用户商品映射信息1
        UserProductMap userProductMap = new UserProductMap();
        PersonInfo customer = new PersonInfo();
        customer.setUserId(1L);
        userProductMap.setUser(customer);
        userProductMap.setOperator(customer);
        Product product = new Product();
        product.setProductId(1L);
        userProductMap.setProduct(product);
        Shop shop = new Shop();
        shop.setShopId(1L);
        userProductMap.setShop(shop);
        userProductMap.setCreateTime(new Date());
        int effectedNum = userProductMapDao.insertUserProductMap(userProductMap);

    }

    /**
     * 测试查询功能
     */
    @Test
    public void testBQueryUserProductMap() {
        UserProductMap userProductMap = new UserProductMap();
        PersonInfo customer = new PersonInfo();
        //按照顾客名字模糊查询
        customer.setName("测试");
        userProductMap.setUser(customer);
        List<UserProductMap> userProductMapList = userProductMapDao.queryUserProductMapList(userProductMap, 0, 1);
        assertEquals(1, userProductMapList.size());

        int count = userProductMapDao.queryUserProductMapCount(userProductMap);
        assertEquals(1, count);

        //叠加店铺去查询
        Shop shop = new Shop();
        shop.setShopId(1L);
        userProductMap.setShop(shop);
        userProductMapList = userProductMapDao.queryUserProductMapList(userProductMap, 0,3);
        assertEquals(1, userProductMapList.size());
        count = userProductMapDao.queryUserProductMapCount(userProductMap);
        assertEquals(1, count);
    }
}