/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: WechatAuthServiceTest
 * Author: Administrator
 * Date: 2018-09-26 15:31
 * Description: 微信账号测试
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.test.o2o.dto.WechatAuthExecution;
import org.test.o2o.entity.PersonInfo;
import org.test.o2o.entity.WechatAuth;
import org.test.o2o.enums.WechatAuthStateEnum;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * 〈微信账号测试〉
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatAuthServiceTest {
    @Autowired
    private WechatAuthService wechatAuthService;

    @Test
    public void testRegister() {
        //新增一条微信账号
        WechatAuth wechatAuth = new WechatAuth();
        PersonInfo personInfo = new PersonInfo();
        String openId = "ddddddd";
        //给微信号设置上用户信息，但不设置上用户Id
        //希望创建微信账号的时候自动创建用户信息
        personInfo.setCreateTime(new Date());
        personInfo.setName("测试微信的账户");
        personInfo.setUserType(1);
        wechatAuth.setPersonInfo(personInfo);
        wechatAuth.setOpenId(openId);
        wechatAuth.setCreateTime(new Date());
        WechatAuthExecution wae = wechatAuthService.register(wechatAuth);
        assertEquals(WechatAuthStateEnum.SUCCESS.getState(), wae.getState());
        //通过openId找到新增的wechatAuth
        wechatAuth = wechatAuthService.getWechatAuthByOpenId(openId);
        //打印用户名，比较跟预期是否相符
        System.out.println(wechatAuth.getPersonInfo().getName());
    }

}