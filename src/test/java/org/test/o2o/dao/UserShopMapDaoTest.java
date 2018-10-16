/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserShopMapDaoTest
 * Author: Administrator
 * Date: 2018-10-16 9:36
 * Description: 用户店铺积分映射测试
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
import org.test.o2o.entity.Shop;
import org.test.o2o.entity.UserShopMap;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**

 * 〈功能简述〉<br>

 * 〈用户店铺积分映射测试〉

 *

 * @author Administrator

 * @create 2018-10-16

 * @since 1.0.0

 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserShopMapDaoTest {
    @Autowired
    private UserShopMapDao userShopMapDao;

    /**
    * 测试添加功能
    */
    @Test
    public void testAInsertUserShopMap() {
        //创建用户店铺积分统计信息1
        UserShopMap userShopMap = new UserShopMap();
        PersonInfo customer = new PersonInfo();
        customer.setUserId(1L);
        userShopMap.setUser(customer);
        Shop shop = new Shop();
        shop.setShopId(1L);
        userShopMap.setShop(shop);
        userShopMap.setCreateTime(new Date());
        userShopMap.setPoint(1);
        int effectedNum = userShopMapDao.insertUserShopMap(userShopMap);
        assertEquals(1, effectedNum);
    }

    /**
    * 测试查询功能
    */
    @Test
    public void testBQueryUserShopMap() {
        UserShopMap userShopMap = new UserShopMap();

        //查全部
        List<UserShopMap> userShopMapList = userShopMapDao.queryUserShopMapList(userShopMap,0,3);
        assertEquals(1, userShopMapList.size());

        int count = userShopMapDao.queryUserShopMapCount(userShopMap);
        assertEquals(1, count);
        //按店铺去查询
        Shop shop = new Shop();
        shop.setShopId(1L);
        userShopMap.setShop(shop);
        userShopMapList = userShopMapDao.queryUserShopMapList(userShopMap, 0, 3);
        assertEquals(1, userShopMapList.size());
        count = userShopMapDao.queryUserShopMapCount(userShopMap);
        assertEquals(1, count);

        //按用户Id和店铺查询
        userShopMap = userShopMapDao.queryUserShopMap(1, 1);
        assertEquals("测试", userShopMap.getUser().getName());
    }

    /**
    * 测试更新功能
    */
    @Test
    public void testCUpdateUserShopMapPoint() {
        UserShopMap userShopMap = new UserShopMap();
        userShopMap = userShopMapDao.queryUserShopMap(1, 1);
        assertTrue("Error,积分不一致", 1==userShopMap.getPoint());
        userShopMap.setPoint(2);
        int effectedNum = userShopMapDao.updateUserShopMapPoint(userShopMap);
        assertEquals(1, effectedNum);
    }
}