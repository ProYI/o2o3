/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ShopExecution
 * Author: Administrator
 * Date: 2018-09-10 21:06
 * Description: 店铺的扩展类
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dto;


import org.test.o2o.entity.Shop;
import org.test.o2o.enums.ShopStateEnum;

import java.util.List;

/**

 * 〈功能简述〉<br>

 * 〈店铺的扩展类 添加店铺的返回值类型〉

 *

 * @author Administrator

 * @create 2018-09-10

 * @since 1.0.0

 */

public class ShopExecution {
    //结果状态
    private int state;

    //状态标识
    private String stateInfo;

    //店铺数量
    private int count;

    //操作shop(增删改查的时候用到)
    private Shop shop;

    //shop列表(查询店铺列表的时候使用)
    private List<Shop> shopList;

    public ShopExecution() {
    }
    //店铺操作失败的时候生成ShopExecution对象构造器
    public ShopExecution(ShopStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }
    //店铺操作成功的构造器
    public ShopExecution(ShopStateEnum stateEnum, Shop shop) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop = shop;
    }
    //店铺操作成功的构造器
    public ShopExecution(ShopStateEnum stateEnum, List<Shop> shopList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}