/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: AwardExecution
 * Author: Administrator
 * Date: 2018-10-22 17:08
 * Description: 奖品包装类
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dto;


import org.test.o2o.entity.Award;
import org.test.o2o.enums.AwardStateEnum;

import java.util.List;

/**
 * 〈奖品包装类〉
 */

public class AwardExecution {
    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 店铺数量
    private int count;

    // 操作的award（增删改商品的时候用）
    private Award award;

    // 获取的award列表(查询商品列表的时候用)
    private List<Award> awardList;

    public AwardExecution() {
    }

    // 失败的构造器
    public AwardExecution(AwardStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    // 成功的构造器
    public AwardExecution(AwardStateEnum stateEnum, Award award) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.award = award;
    }

    // 成功的构造器
    public AwardExecution(AwardStateEnum stateEnum, List<Award> awardList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.awardList = awardList;
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

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }

    public List<Award> getAwardList() {
        return awardList;
    }

    public void setAwardList(List<Award> awardList) {
        this.awardList = awardList;
    }
}