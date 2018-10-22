/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserAwardMapServiceImpl
 * Author: Administrator
 * Date: 2018-10-22 11:00
 * Description: 用户奖品映射业务层
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.o2o.dao.UserAwardMapDao;
import org.test.o2o.dto.UserAwardMapExecution;
import org.test.o2o.entity.UserAwardMap;
import org.test.o2o.service.UserAwardMapService;
import org.test.o2o.util.PageCalculator;

import java.util.List;

/**
 * 〈用户奖品映射业务层〉
 */
@Service
public class UserAwardMapServiceImpl implements UserAwardMapService{
    @Autowired
    private UserAwardMapDao userAwardMapDao;

    @Override
    public UserAwardMapExecution listUserAwardMap(UserAwardMap userAwardCondition, Integer pageIndex, Integer pageSize) {
        //空值判断
        if (userAwardCondition!=null && pageIndex!=-1 && pageSize!=-1) {
            //页码转行数
            int beginIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
            //根据传入的条件查询分页返回用户与奖品的映射信息列表（用户领取奖品的信息列表）
            List<UserAwardMap> userAwardMapList = userAwardMapDao.queryUserAwardMapList(userAwardCondition, beginIndex, pageSize);
            //返回总数
            int count = userAwardMapDao.queryUserAwardMapCount(userAwardCondition);
            UserAwardMapExecution ue = new UserAwardMapExecution();
            ue.setUserAwardMapList(userAwardMapList);
            ue.setCount(count);
            return ue;
        } else {
            return null;
        }
    }

    @Override
    public UserAwardMap getUserAwardMapById(Long AwardMapId) {
        return null;
    }
}