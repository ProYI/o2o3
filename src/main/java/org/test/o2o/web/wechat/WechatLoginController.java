/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: WechatLoginController
 * Author: Administrator
 * Date: 2018-09-22 19:01
 * Description: 获取微信登录信息
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.web.wechat;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.test.o2o.dto.UserAccessToken;
import org.test.o2o.dto.WechatAuthExecution;
import org.test.o2o.dto.WechatUser;
import org.test.o2o.entity.PersonInfo;
import org.test.o2o.entity.WechatAuth;
import org.test.o2o.enums.WechatAuthStateEnum;
import org.test.o2o.service.PersonInfoService;
import org.test.o2o.service.WechatAuthService;
import org.test.o2o.util.wechat.WechatUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 〈获取微信登录信息〉
 * @author Administrator


 */
@Controller
@RequestMapping("wechatlogin")
/**
 * 获取关注公众号之后的微信用户信息的接口，如果在微信浏览器里访问
 * https://open.weixin.qq.com/connect/oauth2/authorize?appid=----------&redirect_uri=http://www.yuyingtaopc.top/o2o/wechatlogin/logincheck&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
 * 则这里将会获取到code,之后再可以通过code获取到access_token 进而获取到用户信息
 *
 *
 */
public class WechatLoginController {
    private static Logger log = LoggerFactory.getLogger(WechatLoginController.class);
    private static final String FRONTEND="1";
    private static final String SHOPEND="2";
    @Autowired
    private PersonInfoService personInfoService;
    @Autowired
    private WechatAuthService wechatAuthService;

    @RequestMapping(value = "/logincheck", method = { RequestMethod.GET })
    public String doGet(HttpServletRequest request, HttpServletResponse response) {
        log.debug("weixin login get...");
        // 获取微信公众号传输过来的code,通过code可获取access_token,进而获取用户信息
        String code = request.getParameter("code");
        // 这个state可以用来传我们自定义的信息，方便程序调用，通过state来判断roleType是什么，在公众号中点击前端展示页面，就回传1.点击店家管理系统，回传state=2
        String roleType = request.getParameter("state");

        log.debug("weixin login code:" + code);

        WechatUser user = null;
        String openId = null;
        WechatAuth auth = null;

        if (null != code) {
            UserAccessToken token;
            try {
                // 通过code获取access_token
                token = WechatUtil.getUserAccessToken(code);
                log.debug("weixin login token:" + token.toString());
                // 通过token获取accessToken
                String accessToken = token.getAccessToken();
                // 通过token获取openId
                openId = token.getOpenId();
                // 通过access_token和openId获取用户昵称等信息
                user = WechatUtil.getUserInfo(accessToken, openId);
                log.debug("weixin login user:" + user.toString());
                request.getSession().setAttribute("openId", openId);

                //查询数据库中是否已经存在该微信账号
                auth = wechatAuthService.getWechatAuthByOpenId(openId);
            } catch (IOException e) {
                log.error("error in getUserAccessToken or getUserInfo or findByOpenId: " + e.toString());
                e.printStackTrace();
            }
        }

        // 前面咱们获取到openId后，可以通过它去数据库判断该微信帐号是否在我们网站里有对应的帐号了，
        // 没有的话这里可以自动创建上，直接实现微信与咱们网站的无缝对接。
        if (auth == null) {
            PersonInfo personInfo = WechatUtil.getPersonInfoFromRequest(user);
            auth = new WechatAuth();
            auth.setOpenId(openId);

            if (FRONTEND.equals(roleType)) {
                personInfo.setUserType(1);
            } else {
                personInfo.setUserType(2);
            }
            auth.setPersonInfo(personInfo);
            WechatAuthExecution wae = wechatAuthService.register(auth);
            if (wae.getState() != WechatAuthStateEnum.SUCCESS.getState()) {
                return null;
            } else {
                //将用户信息存储在session中
                personInfo = personInfoService.getPersonInfoById(auth.getPersonInfo().getUserId());
                request.getSession().setAttribute("user", personInfo);
            }
        }
        //若用户点击的是前端展示系统按钮，则进入前端展示系统
        if (FRONTEND.equals(roleType)) {
            return "frontend/index";
        } else {
            return "shopadmin/shoplist";
        }
        //if (user != null) {
        //    // 获取到微信验证的信息后返回到指定的路由（需要自己设定）
        //    return "frontend/index";
        //} else {
        //    return null;
        //}
    }
}