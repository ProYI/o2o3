/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: WechatAuthDaoTest
 * Author: Administrator
 * Date: 2018-09-26 13:34
 * Description: 微信账号测试
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
import org.test.o2o.entity.PersonInfo;
import org.test.o2o.entity.WechatAuth;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * 〈微信账号测试〉
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WechatAuthDaoTest {
    @Autowired
    private WechatAuthDao wechatAuthDao;

    @Test
    @Ignore
    public void testAInsertWechatAuth() throws Exception {
        //新增一条微信账号
        WechatAuth wechatAuth = new WechatAuth();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);
        //给微信账号绑定上用户信息
        wechatAuth.setPersonInfo(personInfo);
        //随意设置上openId
        wechatAuth.setOpenId("ddddddd");
        wechatAuth.setCreateTime(new Date());
        int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
        assertEquals(1, effectedNum);
    }

    @Test
    @Ignore
    public void testBQueryWechatAuthByOpenId() throws Exception {
        WechatAuth wechatAuth = wechatAuthDao.queryWechatInfoByOpenId("ddddddd");
        assertEquals("测试", wechatAuth.getPersonInfo().getName());
    }
}