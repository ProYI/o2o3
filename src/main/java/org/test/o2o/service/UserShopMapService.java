package org.test.o2o.service;

import org.test.o2o.dto.UserShopMapExecution;
import org.test.o2o.entity.UserShopMap;

public interface UserShopMapService {
    /**根据用户传入的查询信息分页查询用户积分列表
     * @param: userShopMapCondition
     * @param: pageIndex
     * @param: pageSize
     */
    UserShopMapExecution listUserShopMap(UserShopMap userShopMapCondition, int pageIndex, int pageSize);

    /**
    * 根据用户Id和店铺Id返回该用户在某个店铺的积分情况
    * @param: userId
    * @param: shopId
    * @return:
    */
    UserShopMap getUserShopMap(Long userId, Long shopId);
}
