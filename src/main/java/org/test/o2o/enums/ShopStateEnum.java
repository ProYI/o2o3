/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ShopStateEnum
 * Author: Administrator
 * Date: 2018-09-10 21:19
 * Description: 店铺状态枚举
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.enums;


/**

 * 〈功能简述〉<br>

 * 〈店铺状态枚举〉

 *

 * @author Administrator

 * @create 2018-09-10

 * @since 1.0.0

 */

public enum ShopStateEnum {
    CHECK(0, "审核中"),
    OFFLINE(-1, "非法商铺"),
    SUCCESS(1, "操作成功"),
    PASS(2, "通过认证"),
    INNER_ERROR(-1001, "操作失败"),
    NULL_SHOPID(-1002, "ShopId为空"),
    NULL_SHOP(-1003, "Shop信息为空");

    private int state;
    private String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /*
    依据传入的state返回相应的enum值
     */
    public static ShopStateEnum stateOf(int state) {
        for (ShopStateEnum stateEnum : values()) {
            if (stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }
}