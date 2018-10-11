package org.test.o2o.service;

import org.test.o2o.dto.ProductCategoryExecution;
import org.test.o2o.entity.ProductCategory;
import org.test.o2o.exceptions.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {
    //查询指定某个店铺下的所有商品类别信息
    List<ProductCategory> getProductCategoryList(long shopId);

    /**
     * 批量插入商品信息
     * @param productCategoryList
     * @return ProductCategoryExecution
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException;

    /**
     * 将此类别下的商品里的类别id设置为空,再删除掉该商品类别
     * @param productCategoryId
     * @param shopId
     * @return ProductCategoryExecution
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException;
}
