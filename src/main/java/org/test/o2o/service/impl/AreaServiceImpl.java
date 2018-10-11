/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: AreaServiceImpl
 * Author: Administrator
 * Date: 2018-09-07 23:38
 * Description: AreaService实现类
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */

package org.test.o2o.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.o2o.cache.JedisUtil;
import org.test.o2o.dao.AreaDao;
import org.test.o2o.entity.Area;
import org.test.o2o.exceptions.AreaOperationException;
import org.test.o2o.service.AreaService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈AreaService实现类〉
 */
@Service
public class AreaServiceImpl implements AreaService {
    private static String AREALISTKEY = "arealist";
    private static Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Autowired
    private AreaDao areaDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;


    @Override
    @Transactional
    public List<Area> getAreaList() {
        String key = AREALISTKEY;
        List<Area> areaList = null;
        ObjectMapper mapper = new ObjectMapper();

        if (!jedisKeys.exists(key)) {
            areaList = areaDao.queryArea();
            String jsonString = null;
            try {
                jsonString = mapper.writeValueAsString(areaList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new AreaOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);

        } else {
            //如果已存在，直接返回数据
            String jsonString = jedisStrings.get(key);
            //转换成对象类型
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
            try {
                areaList = mapper.readValue(jsonString, javaType);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new AreaOperationException(e.getMessage());
            }
        }
        return areaList;
    }
}