/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Area
 * Author: Administrator
 * Date: 2018-09-07 14:16
 * Description: 区域entity
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.entity;


import java.util.Date;

/**

 * 〈功能简述〉<br>

 * 〈区域entity〉

 *

 * @author Administrator

 * @create 2018-09-07

 * @since 1.0.0

 */

public class Area {
    //ID
    private Integer areaId;
    //名称
    private String areaName;
    //权重
    private Integer priority;
    //创建时间
    private Date lastEditTime;
    //更新时间
    private Date createTime;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}