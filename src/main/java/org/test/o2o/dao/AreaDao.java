package org.test.o2o.dao;

import org.test.o2o.entity.Area;

import java.util.List;

public interface AreaDao {
    /**
    * 功能描述:
    *〈列出区域列表〉
    * * @param null
    * @return: areaList
    * @since: 1.0.0
    * @Author:Administrator
    * @Date: 2018-09-07 23:05
    */
    List<Area> queryArea();
}
