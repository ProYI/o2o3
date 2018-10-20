/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductSellDailyServiceImpl
 * Author: Administrator
 * Date: 2018-10-19 10:21
 * Description: 每日销量统计实现类
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.o2o.dao.ProductSellDailyDao;
import org.test.o2o.entity.ProductSellDaily;
import org.test.o2o.service.ProductSellDailyService;

import java.util.Date;
import java.util.List;

/**
 * 〈每日销量统计实现类〉
 */
@Service
public class ProductSellDailyServiceImpl implements ProductSellDailyService{
    private static final Logger log = LoggerFactory.getLogger(ProductSellDailyServiceImpl.class);
    @Autowired
    private ProductSellDailyDao productSellDailyDao;

    @Override
    public void dailyCalculate() {
        log.info("Quartz Running!");
        //统计在tb_user_product_map里面产生销量的每个店铺的各件商品的日销量
        productSellDailyDao.insertProductSellDaily();
        //统计余下的商品的日销量，全部置为0（为了迎合echarts的数据请求）
        productSellDailyDao.insertDefaultProductSellDaily();
    }

    @Override
    public List<ProductSellDaily> listProductSellDaily(ProductSellDaily productSellDailyCondition, Date beginTime, Date endTime) {
        return productSellDailyDao.queryProductSellDailyList(productSellDailyCondition, beginTime, endTime);
    }

}