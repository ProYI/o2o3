/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductDao
 * Author: Administrator
 * Date: 2018-09-17 20:13
 * Description: 商品详情
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dao;


import org.apache.ibatis.annotations.Param;
import org.test.o2o.entity.Product;

import java.util.List;

/**

 * 〈功能简述〉<br>

 * 〈商品详情〉

 *

 * @author Administrator

 * @create 2018-09-17

 * @since 1.0.0

 */

public interface ProductDao {
    //插入商品
    int insertProduct(Product product);

    //通过productId查询唯一的商品信息
    Product queryProductById(long productId);

    //更新商品信息
    int updateProduct(Product product);

    /**
     * 查询商品列表并分页,可输入的条件有:商品名(模糊),商品状态,店铺Id,商品类别
     * @param productCondition
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<Product> queryProductList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * 查询对应的商品总数
     * @param productCondition
     * @return
     */
    int queryProductCount(@Param("productCondition") Product productCondition);

    /**
     * 删除商品类别之前,将商品类别ID置为空
     * @param productCategoryId
     * @return
     */
    int updateProductCategoryToNull(long productCategoryId);
}