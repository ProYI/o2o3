/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: EchartXAxis
 * Author: Administrator
 * Date: 2018-10-20 14:27
 * Description: 迎合echart里的xAxis项
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dto;


import java.util.HashSet;

/**
 * 〈迎合echart里的xAxis项〉
 */

public class EchartXAxis {
    private String type = "category";
    //为了去重
    private HashSet<String> data;

    public HashSet<String> getData() {
        return data;
    }

    public void setData(HashSet<String> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }
}