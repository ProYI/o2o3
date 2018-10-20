package org.test.o2o.service;

import org.test.o2o.dto.UserProductMapExecution;
import org.test.o2o.entity.UserProductMap;

public interface UserProductMapService {
    /**
    * 通过传入的查询条件分页列出用户消费信息列表
    * @param: shopId
    * @param: pageIndex
    * @param: pageSize
    * @return:
    */
    UserProductMapExecution listUserProductMap(UserProductMap userProductCondition, Integer pageIndex, Integer pageSize);
}
