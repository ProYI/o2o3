/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: WechatAuthServiceImpl
 * Author: Administrator
 * Date: 2018-09-26 14:48
 * Description: 微信账号操作实现类
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.o2o.dao.PersonInfoDao;
import org.test.o2o.dao.WechatAuthDao;
import org.test.o2o.dto.WechatAuthExecution;
import org.test.o2o.entity.PersonInfo;
import org.test.o2o.entity.WechatAuth;
import org.test.o2o.enums.WechatAuthStateEnum;
import org.test.o2o.exceptions.WechatAuthOperationException;
import org.test.o2o.service.WechatAuthService;

import java.util.Date;

/**

 * 〈功能简述〉<br>

 * 〈微信账号操作实现类〉

 *

 * @author Administrator

 * @create 2018-09-26

 * @since 1.0.0

 */
@Service
public class WechatAuthServiceImpl implements WechatAuthService {

    private static Logger log = LoggerFactory.getLogger(WechatAuthServiceImpl.class);

    @Autowired
    private WechatAuthDao wechatAuthDao;
    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public WechatAuth getWechatAuthByOpenId(String openId) {
        return wechatAuthDao.queryWechatInfoByOpenId(openId);
    }

    @Override
    @Transactional
    public WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException {
        //空值判断
        if (wechatAuth==null || wechatAuth.getOpenId()==null) {
            return new WechatAuthExecution(WechatAuthStateEnum.NULL_AUTH_INFO);
        }
        try {
            //设置创建时间
            wechatAuth.setCreateTime(new Date());
            //如果微信账号里夹带着用户信息并且用户Id为空，则认为该用户第一次使用平台(且通过微信登录)
            //则自动创建用户信息
            if (wechatAuth.getPersonInfo()!=null && wechatAuth.getPersonInfo().getUserId()==null) {
                try {
                    wechatAuth.getPersonInfo().setCreateTime(new Date());
                    wechatAuth.getPersonInfo().setEnableStatus(1);
                    PersonInfo personInfo = wechatAuth.getPersonInfo();
                    int effectedNum = personInfoDao.insertPersonInfo(personInfo);
                    wechatAuth.setPersonInfo(personInfo);
                    if (effectedNum <= 0) {
                        throw new WechatAuthOperationException("添加用户信息失败");
                    }
                } catch (Exception e) {
                    log.error("insertPersonInfo error:" + e.toString());
                    throw new WechatAuthOperationException("insertPersonInfo error:" + e.getMessage());
                }
            }
            //创建专属于本平台的微信账号
            int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
            if (effectedNum <= 0) {
                throw new WechatAuthOperationException("账号创建失败");
            } else {
                return new WechatAuthExecution(WechatAuthStateEnum.SUCCESS, wechatAuth);
            }
        } catch (Exception e) {
            log.error("insertWechatAuth error:" + e.toString());
            throw new WechatAuthOperationException("insertWechatAuth error:" + e.getMessage());
        }
    }
}