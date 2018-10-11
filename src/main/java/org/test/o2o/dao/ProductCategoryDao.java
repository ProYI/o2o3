package org.test.o2o.dao;

import org.apache.ibatis.annotations.Param;
import org.test.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {
    /**
    * 功能描述:
    *〈通过shopId查询店铺商品类别〉
    * @param: long shopId
    * @return:List<ProductCategory>
    * @since: 1.0.0
    * @Date: 2018-09-16 11:40
    */
    List<ProductCategory> queryProductCategoryList(long shopId);

    //批量新增商品类别
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    //删除指定商品类别
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}
