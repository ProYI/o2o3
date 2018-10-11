package org.test.o2o.service;

import org.test.o2o.dto.WechatAuthExecution;
import org.test.o2o.entity.WechatAuth;
import org.test.o2o.exceptions.WechatAuthOperationException;

public interface WechatAuthService {
    /**
    * 通过openId查找平台对应的微信账号
    * @param: openId
    * @return:
    */
    WechatAuth getWechatAuthByOpenId(String openId);

    /**
    * 注册本平台的微信账号
    * @param: wechatAuth,profiileImg
    * @return:
    */
    WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException;

}
