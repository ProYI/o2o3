/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: AreaServiceTest
 * Author: Administrator
 * Date: 2018-09-07 23:43
 * Description: AreaService单元测试类
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.service;


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

 * 〈AreaService单元测试类〉

 *

 * @author Administrator

 * @create 2018-09-07

 * @since 1.0.0

 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaServiceTest extends {
    @Autowired
    private AreaService areaService;
    @Test
    @Ignore
    public void testGetAreaList() {
        List<Area> areaList = areaService.getAreaList();
        assertEquals("西院", areaList.get(0).getAreaName());
    }
}