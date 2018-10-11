/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: LocalAuthServiceTest
 * Author: Administrator
 * Date: 2018-09-28 14:20
 * Description: 本地平台账号功能测试
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.service;


import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.test.o2o.dto.LocalAuthExecution;
import org.test.o2o.entity.LocalAuth;
import org.test.o2o.entity.PersonInfo;
import org.test.o2o.enums.WechatAuthStateEnum;

import java.util.Date;

/**
 * 〈本地平台账号功能测试〉
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthServiceTest {
    @Autowired
    private LocalAuthService localAuthService;

    @Test
    @Ignore
    public void testABindLocalAuth() {
        //新增一条平台账号信息
        LocalAuth localAuth = new LocalAuth();
        PersonInfo personInfo = new PersonInfo();
        String username = "testusername";
        String password = "testpassword";
        personInfo.setUserId(1L);
        //给平台账户绑定上用户信息
        //给平台账号设置用户信息，标明是与哪个用户绑定
        localAuth.setPersonInfo(personInfo);
        //设置上用户名和密码
        localAuth.setUsername(username);
        localAuth.setPassword(password);
        localAuth.setCreateTime(new Date());
        LocalAuthExecution lae = localAuthService.bindLocalAuth(localAuth);
        assertEquals(WechatAuthStateEnum.SUCCESS.getState(), lae.getState());
        //通过userId找到新增的localAuth
        localAuth = localAuthService.getLocalAuthByUserId(personInfo.getUserId());
        //打印用户名字和帐号密码看跟预期是否相等
        System.out.println("用户昵称：" + localAuth.getPersonInfo().getName());
        System.out.println("平台账号密码：" + localAuth.getPassword());
    }

    @Test
    @Ignore
    public void testBModifyLocalAuth() {
        //设置账号信息
        long userId = 1;
        String username = "testusername";
        String password = "testpassword";
        String newPassword = "testnewpassword";
        //修改账号对应的密码
        LocalAuthExecution lae = localAuthService.modifyLocalAuth(userId, username, password, newPassword);
        assertEquals(WechatAuthStateEnum.SUCCESS.getState(), lae.getState());
        //通过账号密码找到修改后的localAuth
        LocalAuth localAuth = localAuthService.getLocalAuthByUsernameAndPwd(username, newPassword);
        //打印用户名字看跟预期是否相等
        System.out.println(localAuth.getPersonInfo().getName());

    }

}