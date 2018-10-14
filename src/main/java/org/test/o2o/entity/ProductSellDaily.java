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


import javax.xml.crypto.Data;

/**
 * 〈顾客消费的商品映射〉
 */

public class ProductSellDaily {
    //哪天的销量，精确到天
    private Data createTime;
    //销量
    private Integer total;
    //商品信息实体类
    private Product product;
    //店铺信息实体类
    private Shop shop;

    public Data getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Data createTime) {
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