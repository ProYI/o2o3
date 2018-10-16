/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ShopAuthMapExecution
 * Author: Administrator
 * Date: 2018-10-16 15:17
 * Description: 店铺授权映射状态寄存
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dto;


import org.test.o2o.entity.ShopAuthMap;
import org.test.o2o.enums.ShopAuthMapStateEnum;

import java.util.List;

/**
 * 〈店铺授权映射状态寄存〉
 */

public class ShopAuthMapExecution {
    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 授权数
    private Integer count;

    // 操作的shopAuthMap
    private ShopAuthMap shopAuthMap;

    // 授权列表（查询专用）
    private List<ShopAuthMap> shopAuthMapList;

    public ShopAuthMapExecution() {
    }

    // 失败的构造器
    public ShopAuthMapExecution(ShopAuthMapStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    // 成功的构造器
    public ShopAuthMapExecution(ShopAuthMapStateEnum stateEnum,
                                ShopAuthMap shopAuthMap) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopAuthMap = shopAuthMap;
    }

    // 成功的构造器
    public ShopAuthMapExecution(ShopAuthMapStateEnum stateEnum,
                                List<ShopAuthMap> shopAuthMapList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopAuthMapList = shopAuthMapList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ShopAuthMap getShopAuthMap() {
        return shopAuthMap;
    }

    public void setShopAuthMap(ShopAuthMap shopAuthMap) {
        this.shopAuthMap = shopAuthMap;
    }

    public List<ShopAuthMap> getShopAuthMapList() {
        return shopAuthMapList;
    }

    public void setShopAuthMapList(List<ShopAuthMap> shopAuthMapList) {
        this.shopAuthMapList = shopAuthMapList;
    }
}