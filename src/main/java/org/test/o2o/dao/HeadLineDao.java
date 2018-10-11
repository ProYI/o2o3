/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: HeadLineDao
 * Author: Administrator
 * Date: 2018-09-21 14:09
 * Description: 头条展示
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dao;


import org.apache.ibatis.annotations.Param;
import org.test.o2o.entity.HeadLine;

import java.util.List;

/**

 * 〈功能简述〉<br>

 * 〈头条展示〉

 *

 * @author Administrator

 * @create 2018-09-21

 * @since 1.0.0

 */

public interface HeadLineDao {
        /**
         * 根据传入的查询条件(头条名查询头条)
         * @param headLineCondition
         * @return
         */
        List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);

}