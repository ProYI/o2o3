/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductCategoryStateEnum
 * Author: Administrator
 * Date: 2018-09-16 12:58
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

 * @create 2018-09-16

 * @since 1.0.0

 */

public enum ProductCategoryStateEnum {
    SUCCESS(1, "创建成功"), INNER_ERROR(-1001, "操作失败"), EMPTY_LIST(-1002, "添加数少于1");

    private int state;
    private String stateInfo;

    ProductCategoryStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static ProductCategoryStateEnum stateOf(int index) {
        for (ProductCategoryStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}