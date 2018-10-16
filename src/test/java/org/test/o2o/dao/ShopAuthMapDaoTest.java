/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ShopAuthMapDaoTest
 * Author: Administrator
 * Date: 2018-10-16 14:36
 * Description: 店铺授权映射测试
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
import org.test.o2o.entity.ShopAuthMap;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**

 * 〈功能简述〉<br>

 * 〈店铺授权映射测试〉

 *

 * @author Administrator

 * @create 2018-10-16

 * @since 1.0.0

 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShopAuthMapDaoTest {
    @Autowired
    private ShopAuthMapDao shopAuthMapDao;

    /**
    * 测试添加功能
    */
    @Test
    public void testAInsertShopAuthMap() {
        //创建店铺授权信息1
         ShopAuthMap shopAuthMap1 = new ShopAuthMap();
        PersonInfo employee = new PersonInfo();
        employee.setUserId(1L);
        shopAuthMap1.setEmployee(employee);
        Shop shop = new Shop();
        shop.setShopId(1L);
        shopAuthMap1.setShop(shop);
        shopAuthMap1.setTitle("CEO");
        shopAuthMap1.setTitleFlag(1);
        shopAuthMap1.setCreateTime(new Date());
        shopAuthMap1.setLastEditTime(new Date());
        shopAuthMap1.setEnableStatus(1);
        int effectecNum = shopAuthMapDao.insertShopAuthMap(shopAuthMap1);
        assertEquals(1, effectecNum);
    }

    /**
     * 测试查询功能
     */
    @Test
    public void testBQueryShopAuth() {
        //测试queryShopAuthMapListByShopId
        List<ShopAuthMap> shopAuthMapList = shopAuthMapDao.queryShopAuthMapListByShopId(1, 0,2);
        assertEquals(1, shopAuthMapList.size());

        //测试queryShopAuthMapById
        ShopAuthMap shopAuthMap = shopAuthMapDao.queryShopAuthMapById(27);
        assertEquals("CEO", shopAuthMap.getTitle());

        //测试queryShopAuthCountByShopId
        int count = shopAuthMapDao.queryShopAuthCountByShopId(1);
        assertEquals(1, count);
    }

    /**
     * 测试更新功能
     */
    @Test
    public void testCUpdateShopAuthMap() {
        List<ShopAuthMap> shopAuthMapList = shopAuthMapDao.queryShopAuthMapListByShopId(1, 0,2);
        shopAuthMapList.get(0).setTitle("CCO");
        shopAuthMapList.get(0).setTitleFlag(2);
        int effectedNum = shopAuthMapDao.updateShopAuthMap(shopAuthMapList.get(0));
        assertEquals(1, effectedNum);
    }

    /**
     * 测试删除功能
     */
    @Test
    public void testDDeleteShopAuthMap() {
        List<ShopAuthMap> shopAuthMapList = shopAuthMapDao.queryShopAuthMapListByShopId(1, 0,2);
        int effectedNum = shopAuthMapDao.deleteShopAuthMap(shopAuthMapList.get(0).getShopAuthId());
        assertEquals(1, effectedNum);
    }
}