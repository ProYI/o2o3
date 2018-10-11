/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: HeadLineServiceImpl
 * Author: Administrator
 * Date: 2018-09-21 15:18
 * Description: 头条业务
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.o2o.dao.HeadLineDao;
import org.test.o2o.entity.HeadLine;
import org.test.o2o.service.HeadLineService;

import java.io.IOException;
import java.util.List;

/**

 * 〈功能简述〉<br>

 * 〈头条业务〉

 *

 * @author Administrator

 * @create 2018-09-21

 * @since 1.0.0

 */
@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    private HeadLineDao headLineDao;

    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
        return headLineDao.queryHeadLine(headLineCondition);
    }
}