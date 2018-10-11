/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: PersonInfoDaoTest
 * Author: Administrator
 * Date: 2018-09-26 13:35
 * Description: 用户信息测试
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

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * 〈用户信息测试〉
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonInfoDaoTest {
    @Autowired
    private PersonInfoDao personInfoDao;

    @Test
    @Ignore
    public void testAInsertPersonInfo() throws Exception{
        //设置新增用户
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName("张三");
        personInfo.setGender("女");
        personInfo.setUserType(1);
        personInfo.setCreateTime(new Date());
        personInfo.setLastEditTime(new Date());
        personInfo.setEnableStatus(1);
        int effectedNum = personInfoDao.insertPersonInfo(personInfo);
        assertEquals(1, effectedNum);
    }

    @Test
    @Ignore
    public void testBQueryPersonInfoById() {
        long userId = 1;
        //查询Id为1的用户信息
        PersonInfo personInfo = personInfoDao.queryPersonInfoById(userId);
        System.out.println(personInfo.getName());
    }
}