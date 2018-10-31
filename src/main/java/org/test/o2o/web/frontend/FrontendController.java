/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: FrontendController
 * Author: Administrator
 * Date: 2018-09-21 15:50
 * Description: 前台页面转发
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.web.frontend;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**

 * 〈功能简述〉<br>

 * 〈前台页面转发〉

 *

 * @author Administrator

 * @create 2018-09-21

 * @since 1.0.0

 */
@Controller
@RequestMapping(value = "/frontend", method = {RequestMethod.GET})
public class FrontendController {

    /**
     * 首页路由
     *
     * @return:
     */
    @RequestMapping(value = "/index")
    private String index() {
        return "frontend/index";
    }

    /**
     * 商品列表路由
     *
     * @return:
     */
    @RequestMapping(value = "/shoplist")
    private String showShopList() {
        return "frontend/shoplist";
    }

    /**
     * 店铺详情页路由
     *
     * @return:
     */
    @RequestMapping(value = "/shopdetail")
    private String showShopDetail() {
        return "frontend/shopdetail";
    }

    /**
     * 奖品列表路由
     *
     * @return:
     */
    @RequestMapping(value = "/awardlist")
    private String showAwardList() {
        return "frontend/awardlist";
    }
}