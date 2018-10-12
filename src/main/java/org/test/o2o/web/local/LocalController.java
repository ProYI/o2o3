/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: LocalController
 * Author: Administrator
 * Date: 2018-09-28 21:56
 * Description: 平台账号系统页面路由
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.web.local;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 〈平台账号系统页面路由〉
 */
@Controller
@RequestMapping("/local")
public class LocalController {
    /**
    * 绑定账号页路由
    * @return:
    */
    @RequestMapping(value = "/accountbind", method = RequestMethod.GET)
    private String accountbind() {
        return "local/accountbind";
    }

    /**
    * 修改密码页路由
    * @return:
    */
    @RequestMapping(value = "/changepsw", method = RequestMethod.GET)
    private String changepsw() {
        return "local/changepsw";
    }

    /**
    * 登录页路由
    */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    private String login() {
        return "local/login";
    }

}