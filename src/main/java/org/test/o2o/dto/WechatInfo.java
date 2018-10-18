/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: WechatInfo
 * Author: Administrator
 * Date: 2018-10-18 16:32
 * Description: 用来接收平台二维码信息
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dto;


/**
 * 〈用来接收平台二维码信息〉
 */

public class WechatInfo {
    private Long customerId;
    private Long productId;
    private Long userAwardId;
    private Long createTime;
    private Long shopId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserAwardId() {
        return userAwardId;
    }

    public void setUserAwardId(Long userAwardId) {
        this.userAwardId = userAwardId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}