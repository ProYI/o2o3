/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserAwardMapExecution
 * Author: Administrator
 * Date: 2018-10-22 10:53
 * Description: 用户奖品映射包装类
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dto;


import org.test.o2o.entity.UserAwardMap;
import org.test.o2o.enums.UserAwardMapStateEnum;

import java.util.List;

/**
 * 〈用户奖品映射包装类〉
 */

public class UserAwardMapExecution {
    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 授权数
    private Integer count;

    // 操作的UserAwardMap
    private UserAwardMap userAwardMap;

    // 授权列表（查询专用）
    private List<UserAwardMap> userAwardMapList;

    public UserAwardMapExecution() {
    }

    // 失败的构造器
    public UserAwardMapExecution(UserAwardMapStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    // 成功的构造器
    public UserAwardMapExecution(UserAwardMapStateEnum stateEnum,
                                 UserAwardMap userAwardMap) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.userAwardMap = userAwardMap;
    }

    // 成功的构造器
    public UserAwardMapExecution(UserAwardMapStateEnum stateEnum,
                                 List<UserAwardMap> userAwardMapList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.userAwardMapList = userAwardMapList;
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

    public UserAwardMap getUserAwardMap() {
        return userAwardMap;
    }

    public void setUserAwardMap(UserAwardMap userAwardMap) {
        this.userAwardMap = userAwardMap;
    }

    public List<UserAwardMap> getUserAwardMapList() {
        return userAwardMapList;
    }

    public void setUserAwardMapList(List<UserAwardMap> userAwardMapList) {
        this.userAwardMapList = userAwardMapList;
    }
}