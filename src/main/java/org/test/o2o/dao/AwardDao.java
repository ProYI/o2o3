/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: AwardDao
 * Author: Administrator
 * Date: 2018-10-13 10:56
 * Description: 奖品dao
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dao;


import org.apache.ibatis.annotations.Param;
import org.test.o2o.entity.Award;

import java.util.List;

/**
 * 〈奖品dao〉

 */

public interface AwardDao {
    /**
    * 依据传进来的查询条件分页显示奖品信息列表
    * @param: awardCondition
    * @return:
    */
    List<Award> queryAwardList(@Param("awardCondition") Award awardCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
    * 配合queryAwardList返回相同查询条件下的奖品数
    * @param: awardCondition
    * @return:
    */
    int queryAwardCount(@Param("awardCondition") Award awardCondition);

    /**
    * 通过awardId查询奖品信息
    * @param: awardId
    * @return:
    */
    Award queryAwardByAwardId(long awardId);

    /**
    * 添加奖品信息
    * @param: award
    * @return:
    */
    int insertAward(Award award);

    /**
    * 更新奖品信息
    * @param: award
    * @return:
    */
    int updateAward(Award award);

    /**
    * 删除奖品信息
    * @param: awardId
    * @param: shopId 传入shopId为了防止非法操作无权限店铺
    * @return:
    */
    int deleteAward(@Param("awardId") long awardId, @Param("shopId") long shopId);
}