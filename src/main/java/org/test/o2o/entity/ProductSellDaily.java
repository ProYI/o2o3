/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductSellDaily
 * Author: Administrator
 * Date: 2018-10-14 18:46
 * Description: 顾客消费的商品映射
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.entity;

import java.util.Date;

/**
 * 〈顾客消费的商品映射〉
 */

public class ProductSellDaily {
    //主键Id
    private Long productSellDailyId;
    //哪天的销量，精确到天
    private Date createTime;
    //销量
    private Integer total;
    //商品信息实体类
    private Product product;
    //店铺信息实体类
    private Shop shop;

    public Long getProductSellDailyId() {
        return productSellDailyId;
    }

    public void setProductSellDailyId(Long productSellDailyId) {
        this.productSellDailyId = productSellDailyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}