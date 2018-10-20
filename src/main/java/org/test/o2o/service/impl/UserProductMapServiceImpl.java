/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserProductMapServiceImpl
 * Author: Administrator
 * Date: 2018-10-20 10:19
 * Description: 店铺销量统计实现
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.o2o.dao.UserProductMapDao;
import org.test.o2o.dto.UserProductMapExecution;
import org.test.o2o.entity.UserProductMap;
import org.test.o2o.service.UserProductMapService;
import org.test.o2o.util.PageCalculator;

import java.util.List;

/**
 * 〈店铺销量统计实现〉
 */
@Service
public class UserProductMapServiceImpl implements UserProductMapService {
    @Autowired
    private UserProductMapDao userProductMapDao;

    @Override
    public UserProductMapExecution listUserProductMap(UserProductMap userProductCondition, Integer pageIndex, Integer pageSize) {
        //空值判断
        if (userProductCondition!=null && pageIndex!=null && pageSize!=null) {
            //页码转成行数
            int beginIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
            //依据查询条件分页取出列表
            List<UserProductMap> userProductMapList = userProductMapDao.queryUserProductMapList(userProductCondition, beginIndex, pageSize);
            //按照同等的查询提奥健获取总数
            int count = userProductMapDao.queryUserProductMapCount(userProductCondition);
            UserProductMapExecution se = new UserProductMapExecution();
            se.setUserProductMapList(userProductMapList);
            se.setCount(count);
            return se;
        } else {
            return null;
        }
    }
}