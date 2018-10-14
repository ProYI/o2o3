/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserAwardMapDaoTest
 * Author: Administrator
 * Date: 2018-10-14 9:55
 * Description: 用户已领奖品测试
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
import org.test.o2o.entity.Award;
import org.test.o2o.entity.PersonInfo;
import org.test.o2o.entity.Shop;
import org.test.o2o.entity.UserAwardMap;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

/**
 * 〈用户已领奖品测试〉
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserAwardMapDaoTest {
    @Autowired
    private UserAwardMapDao userAwardMapDao;

    /**
    * 测试添加功能
    * @return:
    */
    @Test
    public void testAInsertUserAwardMap() {
        //创建用户奖品信息1
        UserAwardMap userAwardMap = new UserAwardMap();
        PersonInfo customer = new PersonInfo();
        customer.setUserId(1L);
        userAwardMap.setUser(customer);
        userAwardMap.setOperator(customer);
        Award award = new Award();
        award.setAwardId(5L);
        userAwardMap.setAward(award);
        Shop shop = new Shop();
        shop.setShopId(1L);
        userAwardMap.setShop(shop);
        userAwardMap.setCreateTime(new Date());
        userAwardMap.setUsedStatus(1);
        userAwardMap.setPoint(1);
        int effectedNum = userAwardMapDao.insertUserAwardMap(userAwardMap);
        assertEquals(1, effectedNum);

        //创建用户奖品信息2
        UserAwardMap userAwardMap2 = new UserAwardMap();
        PersonInfo customer2 = new PersonInfo();
        customer2.setUserId(1L);
        userAwardMap2.setUser(customer);
        userAwardMap2.setOperator(customer);
        Award award2 = new Award();
        award2.setAwardId(5L);
        userAwardMap2.setAward(award2);
        userAwardMap2.setShop(shop);
        userAwardMap2.setCreateTime(new Date());
        userAwardMap2.setUsedStatus(0);
        userAwardMap2.setPoint(2);
        effectedNum = userAwardMapDao.insertUserAwardMap(userAwardMap2);
        assertEquals(1, effectedNum);
    }

    /**
    * 测试查询功能
    */
    @Test
    public void testBQueryUserAwardMapList() {
        UserAwardMap userAwardMap = new UserAwardMap();
        //测试queryUserAwardMapList
        List<UserAwardMap> userAwardMapList = userAwardMapDao.queryUserAwardMapList(userAwardMap, 0,3);
        assertEquals(2, userAwardMapList.size());
        //测试queryUserAwardMapCount
        int count = userAwardMapDao.queryUserAwardMapCount(userAwardMap);
        assertEquals(2, count);

        PersonInfo customer = new PersonInfo();
        //按用户名模糊查找
        customer.setName("测试");
        userAwardMap.setUser(customer);
        userAwardMapList = userAwardMapDao.queryUserAwardMapList(userAwardMap,0,3);
        assertEquals(2, userAwardMapList.size());

        count = userAwardMapDao.queryUserAwardMapCount(userAwardMap);
        assertEquals(2, count);
        //测试queryUserAwardMapById 预期按优先级排列返回第二个奖品的信息
        userAwardMap = userAwardMapDao.queryUserAwardMapById(userAwardMapList.get(0).getUserAwardId());
        assertEquals("测试奖品1", userAwardMap.getAward().getAwardName());
    }

    /**
    * 测试更新功能
    * @return:
    */
    @Test
    public void testCUpdateUserAwardMap() {
        UserAwardMap userAwardMap = new UserAwardMap();
        PersonInfo customer = new PersonInfo();
        //按用户名模糊查询
        customer.setName("测试");
        userAwardMap.setUser(customer);
        List<UserAwardMap> userAwardMapList = userAwardMapDao.queryUserAwardMapList(userAwardMap, 0,1);
        assertTrue("Error, 积分不一致！", 0==userAwardMapList.get(0).getUsedStatus());
        userAwardMapList.get(0).setUsedStatus(1);
        int effectedNum = userAwardMapDao.updateUserAwardMap(userAwardMapList.get(0));
        assertEquals(1, effectedNum);

    }
}