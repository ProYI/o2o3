/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserShopMapExecution
 * Author: Administrator
 * Date: 2018-10-21 10:26
 * Description: 用户店铺映射包装类
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dto;


import org.test.o2o.entity.UserShopMap;
import org.test.o2o.enums.UserShopMapStateEnum;

import java.util.List;

/**
 * 〈用户店铺映射包装类〉
 */

public class UserShopMapExecution {
    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 授权数
    private Integer count;

    // 操作的UserShopMap
    private UserShopMap userShopMap;

    // 授权列表（查询专用）
    private List<UserShopMap> userShopMapList;

    public UserShopMapExecution() {
    }

    // 失败的构造器
    public UserShopMapExecution(UserShopMapStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    // 成功的构造器
    public UserShopMapExecution(UserShopMapStateEnum stateEnum,
                                UserShopMap userShopMap) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.userShopMap = userShopMap;
    }

    // 成功的构造器
    public UserShopMapExecution(UserShopMapStateEnum stateEnum, List<UserShopMap> userShopMapList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.userShopMapList = userShopMapList;
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

    public UserShopMap getUserShopMap() {
        return userShopMap;
    }

    public void setUserShopMap(UserShopMap userShopMap) {
        this.userShopMap = userShopMap;
    }

    public List<UserShopMap> getUserShopMapList() {
        return userShopMapList;
    }

    public void setUserShopMapList(List<UserShopMap> userShopMapList) {
        this.userShopMapList = userShopMapList;
    }
}