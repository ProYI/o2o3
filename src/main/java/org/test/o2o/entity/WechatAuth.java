/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: WechatAuth
 * Author: Administrator
 * Date: 2018-09-07 15:30
 * Description: 微信账号登录
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.entity;


import java.util.Date;

/**

 * 〈功能简述〉<br>

 * 〈微信账号登录〉

 *

 * @author Administrator

 * @create 2018-09-07

 * @since 1.0.0

 */

public class WechatAuth {
    private Long wechatAuthId;
    private String openId;
    private Date createTime;
    private PersonInfo personInfo;

    public Long getWechatAuthId() {
        return wechatAuthId;
    }

    public void setWechatAuthId(Long wechatAuthId) {
        this.wechatAuthId = wechatAuthId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }
}