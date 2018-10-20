/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserProductMapExecution
 * Author: Administrator
 * Date: 2018-10-20 10:05
 * Description:
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dto;

import org.test.o2o.entity.UserProductMap;
import org.test.o2o.enums.UserProductMapStateEnum;

import java.util.List;

/**
 * 〈店铺商品映射包装〉
 */

public class UserProductMapExecution {
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;
    //授权数
    private int count;
    // 操作的shopAuthMap
    private UserProductMap userProductMap;
    // 授权列表
    private List<UserProductMap> userProductMapList;

    public UserProductMapExecution() {
    }

    //操作失败的构造器
    public UserProductMapExecution(UserProductMapStateEnum stateEnum){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
    }

    //操作单个授权成功的构造器
    public UserProductMapExecution(UserProductMapStateEnum stateEnum, UserProductMap userProductMap){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.userProductMap=userProductMap;
    }

    //操作多个授权成功的构造器
    public UserProductMapExecution(UserProductMapStateEnum stateEnum, List<UserProductMap> userProductMapList){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.userProductMapList=userProductMapList;
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

    public UserProductMap getUserProductMap() {
        return userProductMap;
    }

    public void setUserProductMap(UserProductMap userProductMap) {
        this.userProductMap = userProductMap;
    }

    public List<UserProductMap> getUserProductMapList() {
        return userProductMapList;
    }

    public void setUserProductMapList(List<UserProductMap> userProductMapList) {
        this.userProductMapList = userProductMapList;
    }
}