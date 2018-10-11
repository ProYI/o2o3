/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: LocalAuthDaoTest
 * Author: Administrator
 * Date: 2018-09-28 11:22
 * Description: 本地账号测试类
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
import org.test.o2o.entity.LocalAuth;
import org.test.o2o.entity.PersonInfo;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * 〈本地账号测试类〉
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthDaoTest {
    @Autowired
    private LocalAuthDao localAuthDao;
    private static final String username = "testusername";
    private static final String password = "testpassword";

    @Test
    @Ignore
    public void testAInsertLocalAuth() throws Exception {
        //新增一条平台账号信息
        LocalAuth localAuth = new LocalAuth();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);
        //给平台账户绑定上用户信息
        localAuth.setPersonInfo(personInfo);
        //设置上用户名和密码
        localAuth.setUsername(username);
        localAuth.setPassword(password);
        localAuth.setCreateTime(new Date());
        int effectedNum = localAuthDao.insertLocalAuth(localAuth);
        assertEquals(1, effectedNum);
    }

    @Test
    @Ignore
    public void testBQueryLocalByUserNameAndPwd() {
        //按照账号和密码查询用户信息
        LocalAuth localAuth = localAuthDao.queryLocalByUserNameAndPwd(username, password);
        assertEquals("测试", localAuth.getPersonInfo().getName());
    }

    @Test
    @Ignore
    public void testCQueryLocalByUserId() {
        //按照用户Id查询平台账号，进而获取用户信息
        LocalAuth localAuth = localAuthDao.queryLocalByUserId(1L);
        assertEquals("测试", localAuth.getPersonInfo().getName());
    }

    @Test
    @Ignore
    public void testDUpdateLocalAuth() {
        //依据用户Id，平台账号，以及旧密码修改平台帐号密码
        Date now = new Date();
        int effectedNum = localAuthDao.updateLocalAuth(1L, username, password, password + "new", now);
        assertEquals(1, effectedNum);
        //查询出该条平台账号的最新信息
        LocalAuth localAuth = localAuthDao.queryLocalByUserId(1L);
        //输出新密码
        System.out.println(localAuth.getPassword());
    }
}