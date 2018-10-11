/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: LocalAuthStateEnum
 * Author: Administrator
 * Date: 2018-09-28 12:39
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

 * @create 2018-09-28

 * @since 1.0.0

 */

public enum  LocalAuthStateEnum {
    LOGINFAIL(-1, "密码或帐号输入有误"),
    SUCCESS(0, "操作成功"),
    NULL_AUTH_INFO(-1006, "注册信息为空"),
    ONLY_ONE_ACCOUNT(-1007,"最多只能绑定一个本地帐号");

    private int state;
    private String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    LocalAuthStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /*
    依据传入的state返回相应的enum值
     */
    public static LocalAuthStateEnum stateOf(int state) {
        for (LocalAuthStateEnum stateEnum : values()) {
            if (stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }
}