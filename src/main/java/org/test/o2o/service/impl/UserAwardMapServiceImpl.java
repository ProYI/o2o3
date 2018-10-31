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
import org.test.o2o.dao.UserShopMapDao;
import org.test.o2o.dto.UserAwardMapExecution;
import org.test.o2o.entity.UserAwardMap;
import org.test.o2o.entity.UserShopMap;
import org.test.o2o.enums.UserAwardMapStateEnum;
import org.test.o2o.exceptions.UserAwardMapOperationException;
import org.test.o2o.service.UserAwardMapService;
import org.test.o2o.util.PageCalculator;

import java.util.Date;
import java.util.List;

/**
 * 〈用户奖品映射业务层〉
 */
@Service
public class UserAwardMapServiceImpl implements UserAwardMapService{
    @Autowired
    private UserAwardMapDao userAwardMapDao;
    @Autowired
    private UserShopMapDao userShopMapDao;

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

    @Override
    public UserAwardMapExecution addUserAwardMap(UserAwardMap userAwardMap) {
        //空值判断，主要是确定userId和shopId不为空
        if (userAwardMap!=null && userAwardMap.getUser()!=null && userAwardMap.getUser().getUserId()!=null && userAwardMap.getShop()!=null && userAwardMap.getShop().getShopId()!=null) {
            //设置默认值
            userAwardMap.setCreateTime(new Date());
            userAwardMap.setUsedStatus(0);
            try {
                int effectedNum = 0;
                //若该奖品需要消耗积分，则将tb_user_shop_map对应的用户积分抵扣
                if (userAwardMap.getPoint()!=null && userAwardMap.getPoint()>0) {
                    //根据用户Id和店铺Id获取该用户在店铺的积分
                    UserShopMap userShopMap = userShopMapDao.queryUserShopMap(userAwardMap.getUser().getUserId(), userAwardMap.getShop().getShopId());
                    //判断该用户在店铺里是否有积分
                    if (userShopMap != null) {
                        //若有积分，必须确保店铺积分大于本次要兑换奖品需要的积分
                        if (userShopMap.getPoint() >= userAwardMap.getPoint()) {
                            //积分抵扣
                            userShopMap.setPoint(userShopMap.getPoint() - userAwardMap.getPoint());
                            //更新积分信息
                            effectedNum = userShopMapDao.updateUserShopMapPoint(userShopMap);
                            if (effectedNum <= 0) {
                                throw new UserAwardMapOperationException("更新积分信息失败");
                            }
                        } else {
                            throw new UserAwardMapOperationException("积分不足，无法领取");
                        }
                    }
                    else {
                        //在本店铺没有积分，则抛出异常
                        throw new UserAwardMapOperationException("在本店铺没有积分，无法兑换");
                    }
                }
                //插入礼品兑换信息
                effectedNum = userAwardMapDao.insertUserAwardMap(userAwardMap);
                if (effectedNum <= 0) {
                    throw new UserAwardMapOperationException("领取奖品失败");
                }
                return new UserAwardMapExecution(UserAwardMapStateEnum.SUCCESS, userAwardMap);
            } catch (Exception e) {
                throw new UserAwardMapOperationException("领取奖励失败：" + e.toString());
            }
        } else {
            return new UserAwardMapExecution(UserAwardMapStateEnum.NULL_USERAWARD_INFO);
        }
    }
}