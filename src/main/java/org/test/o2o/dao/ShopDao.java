package org.test.o2o.dao;

import org.apache.ibatis.annotations.Param;
import org.test.o2o.entity.Shop;

import java.util.List;

public interface ShopDao {
    /**
    * 功能描述:
    *〈新增店铺〉
    * * @param shop
    * @return:
    * @since: 1.0.0
    * @Author:Administrator
    * @Date: 2018-09-08 12:53
    */
    int insertShop(Shop shop);

    /**
    * 功能描述:
    *〈更新店铺信息〉
    * @param: shop
    * @return:
    * @since: 1.0.0
    * @Date: 2018-09-10 10:54
    */
    int updateShop(Shop shop);

    /**
    * 功能描述:
    *〈通过shop id查询店铺〉
    * @param:shopId
    * @return:Shop
    * @since: 1.0.0
    * @Date: 2018-09-14 12:40
    */
    Shop queryByShopId(long shopId);

    /*
    分页查询店铺列表，可输入的条件有：店铺名（模糊），店铺状态，店铺类别，区域Id，owner
    @param rowIndex 从第几行开始取数据
    @param pageSize 返回的条数
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /*
    获取店铺列表总数
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);
}
