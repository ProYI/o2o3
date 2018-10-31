package org.test.o2o.dto;


import org.test.o2o.entity.LocalAuth;
import org.test.o2o.entity.Shop;
import org.test.o2o.enums.LocalAuthStateEnum;
import org.test.o2o.enums.ShopStateEnum;

import java.util.List;

/**
 * 〈本地账户的扩展类〉
 */

public class LocalAuthExecution {
    //结果状态
    private int state;

    //状态标识
    private String stateInfo;

    private int count;

    //操作本地账户(增删改查的时候用到)
    private LocalAuth localAuth;

    //本地账户列表(查询本地账户列表的时候使用)
    private List<LocalAuth> localAuthList;

    public LocalAuthExecution() {
    }
    //本地账户操作失败的时候生成LocalAuthExecution对象构造器
    public LocalAuthExecution(LocalAuthStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }
    //本地账户操作成功的构造器
    public LocalAuthExecution(LocalAuthStateEnum stateEnum, LocalAuth localAuth) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.localAuth = localAuth;
    }
    //本地账户操作成功的构造器
    public LocalAuthExecution(LocalAuthStateEnum stateEnum, List<LocalAuth> localAuthList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.localAuthList = localAuthList;
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

    public LocalAuth getLocalAuth() {
        return localAuth;
    }

    public void setLocalAuth(LocalAuth localAuth) {
        this.localAuth = localAuth;
    }

    public List<LocalAuth> getLocalAuthList() {
        return localAuthList;
    }

    public void setLocalAuthList(List<LocalAuth> localAuthList) {
        this.localAuthList = localAuthList;
    }
}