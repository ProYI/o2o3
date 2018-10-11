/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: WechatAuthStateEnum
 * Author: Administrator
 * Date: 2018-09-26 14:31
 * Description:
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.enums;


/**

 * 〈功能简述〉<br>

 * 〈〉

 *

 * @author Administrator

 * @create 2018-09-26

 * @since 1.0.0

 */

public enum  WechatAuthStateEnum {
    LOGINFAIL(-1, "openId输入有误"), SUCCESS(0, "操作成功"), NULL_AUTH_INFO(-1006, "注册信息为空");

    private int state;
    private String stateInfo;

    private WechatAuthStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static WechatAuthStateEnum stateOf(int index) {
        for (WechatAuthStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}