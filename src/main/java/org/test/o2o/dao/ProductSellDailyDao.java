package org.test.o2o.dao;

import org.apache.ibatis.annotations.Param;
import org.test.o2o.entity.ProductSellDaily;

import java.util.Date;
import java.util.List;

public interface ProductSellDailyDao {
    /**
    * 根据查询条件返回商品日销售的统计列表
    * @param: productSellDailyCondition
    * @param: beginTime
    * @param: endTime
    * @return:
    */
    List<ProductSellDaily> queryProductSellDailyList(@Param("productSellDailyCondition") ProductSellDaily productSellDailyConditionCondition, @Param("beginTime")Date beginTime, @Param("endTime") Date endTime);

    /**
    * 统计平台所有商品的日销售量
    * 对平台所有商铺的日销量进行统计，根据user_product_map表获取信息，对数据进行汇总，计算每个店铺的每个商品的日销量
    * @param:
    * @return:
    */
    int insertProductSellDaily();
}
