/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: HeadLine
 * Author: Administrator
 * Date: 2018-09-07 15:56
 * Description: 头条
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.entity;


import java.util.Date;

/**

 * 〈功能简述〉<br>

 * 〈头条〉

 *

 * @author Administrator

 * @create 2018-09-07

 * @since 1.0.0

 */

public class HeadLine {
    private Long lineId;
    private String lineName; //头条名称
    private String lineLink; //头条链接
    private String lineImg; //头条图片
    private Integer priority; //权重
    private Integer enableStatus; //可用状态 0不可用。1可用
    private Date createTime;
    private Date lastEditTime;

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineLink() {
        return lineLink;
    }

    public void setLineLink(String lineLink) {
        this.lineLink = lineLink;
    }

    public String getLineImg() {
        return lineImg;
    }

    public void setLineImg(String lineImg) {
        this.lineImg = lineImg;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}