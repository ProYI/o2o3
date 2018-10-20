/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: EchartSeries
 * Author: Administrator
 * Date: 2018-10-20 14:30
 * Description: 迎合echart里的series项
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dto;


import java.util.List;

/**

 * 〈功能简述〉<br>

 * 〈迎合echart里的series项〉

 *

 * @author Administrator

 * @create 2018-10-20

 * @since 1.0.0

 */

public class EchartSeries {
    private String name;
    private String type = "bar";
    private List<Integer> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}