package org.test.o2o.service;

import org.test.o2o.dto.ImageHolder;
import org.test.o2o.dto.ProductExecution;
import org.test.o2o.entity.Product;
import org.test.o2o.exceptions.ProductOperationException;

import java.awt.*;
import java.util.List;

public interface ProductService {

    /**
     * 添加商品信息以及图片处理
     * @param product
     * @param imageHolder //缩略图
     * @param imageHolderList //详情图列表
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder imageHolder, List<ImageHolder> imageHolderList) throws ProductOperationException;


    /**
    * 功能描述:
    *〈通过商品Id查询唯一的商品信息〉
    * @param:Product
    * @return:
    */
    Product getProductById(long productId);


    /**
    * 功能描述:
    *〈修改商品〉
    */
    ProductExecution modifyProduct(Product product, ImageHolder imageHolder, List<ImageHolder> productImgHolderList);
    /**
     * 查询商品列表并分页,可输入的条件有:商品名(模糊),商品状态,店铺Id,商品类别
     * @param productCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);
}
