/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: AreaDaoTest
 * Author: Administrator
 * Date: 2018-09-07 23:21
 * Description: AreaDao测试类
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dao;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.test.o2o.entity.Area;

import java.util.List;

import static org.junit.Assert.assertEquals;


/**

 * 〈功能简述〉<br>

 * 〈AreaDao测试类〉

 *

 * @author Administrator

 * @create 2018-09-07

 * @since 1.0.0

 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {
    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryArea() {
        List<Area> areaList = areaDao.queryArea();
        assertEquals(2, areaList.size());

    }

}