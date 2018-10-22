package org.test.o2o.service;

import org.test.o2o.dto.UserAwardMapExecution;
import org.test.o2o.entity.UserAwardMap;

public interface UserAwardMapService {
    /**
    * 根据传入的查询条件分页获取射射列表及总数
    * @param: userAwardCondition
    * @param: pageIndex
    * @param: pageSize
    * @return:
    */
    UserAwardMapExecution listUserAwardMap(UserAwardMap userAwardCondition, Integer pageIndex, Integer pageSize);
    
    /**
    * 根据传入的Id获取映射信息
    * @param: AwardMapId
    * @return:
    */
    UserAwardMap getUserAwardMapById(Long AwardMapId);
}
