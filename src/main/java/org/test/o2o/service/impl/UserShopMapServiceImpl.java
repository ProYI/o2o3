/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserShopMapServiceImpl
 * Author: Administrator
 * Date: 2018-10-21 11:16
 * Description: 顾客积分
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.o2o.dao.UserShopMapDao;
import org.test.o2o.dto.UserShopMapExecution;
import org.test.o2o.entity.UserShopMap;
import org.test.o2o.service.UserShopMapService;
import org.test.o2o.util.PageCalculator;

import java.util.List;

/**
 * 〈顾客积分〉
 */
@Service
public class UserShopMapServiceImpl implements UserShopMapService{
    @Autowired
    private UserShopMapDao userShopMapDao;

    @Override
    public UserShopMapExecution listUserShopMap(UserShopMap userShopMapCondition, int pageIndex, int pageSize) {
        //空值判断
        if (userShopMapCondition!=null && pageIndex!=-1 && pageSize!=-1) {
            //页码转行数
            int beginIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
            //根据传入的条件查询分页返回用户积分列表信息
            List<UserShopMap> userShopMapList = userShopMapDao.queryUserShopMapList(userShopMapCondition, beginIndex, pageSize);
            //返回总数
            int count = userShopMapDao.queryUserShopMapCount(userShopMapCondition);
            UserShopMapExecution ue = new UserShopMapExecution();
            ue.setUserShopMapList(userShopMapList);
            ue.setCount(count);
            return ue;
        } else {
            return null;
        }
    }

    @Override
    public UserShopMap getUserShopMap(Long userId, Long shopId) {
        return userShopMapDao.queryUserShopMap(userId, shopId);
    }
}