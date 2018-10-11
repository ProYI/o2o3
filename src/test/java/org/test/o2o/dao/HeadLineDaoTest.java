/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: HeadLineDaoTest
 * Author: Administrator
 * Date: 2018-09-21 14:29
 * Description: 头条测试类
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dao;


import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.test.o2o.entity.HeadLine;

import java.util.List;

/**

 * 〈功能简述〉<br>

 * 〈头条测试类〉

 *

 * @author Administrator

 * @create 2018-09-21

 * @since 1.0.0

 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HeadLineDaoTest{
    @Autowired
    private HeadLineDao headLineDao;

    @Test
    @Ignore
    public void testQueryHeadLine() {
        List<HeadLine> headLineList = headLineDao.queryHeadLine(new HeadLine());
        Assert.assertEquals(1,headLineList.size());
    }
}