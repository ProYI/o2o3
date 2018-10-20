/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ShopAdminController
 * Author: Administrator
 * Date: 2018-09-11 15:53
 * Description:
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.web.shopadmin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**

 * 〈功能简述〉<br>

 * 〈视图解析器：解析路由并转发到相应的html中〉

 *

 * @author Administrator

 * @create 2018-09-11

 * @since 1.0.0

 */
@Controller
@RequestMapping(value = "/shopadmin", method = {RequestMethod.GET})
public class ShopAdminController {
    @RequestMapping(value = "/shopoperation")
    public String shopOperation() {
        //转发至店铺注册/编辑页面
        return "shop/shopoperation";
    }

    @RequestMapping(value = "/shoplist")
    public String shopList() {
        //转发至店铺列表页面
        return "shop/shoplist";
    }

    @RequestMapping(value = "/shopmanagement")
    public String shopManagement() {
        //转发至店铺管理页面
        return "shop/shopmanagement";
    }

    @RequestMapping(value = "/productcategorymanagement")
    public String productCategoryManagement() {
        //转发至商品类别管理页面
        return "shop/productcategorymanagement";
    }

    @RequestMapping(value = "/productoperation")
    public String productOperation() {
        //转发至商品添加/编辑页面
        return "shop/productoperation";
    }

    @RequestMapping(value = "/productmanagement")
    public String productManagement() {
        //转发至商品管理页面
        return "shop/productmanagement";
    }

    @RequestMapping(value = "/shopauthmanagement")
    public String shopAuthManagement() {
        //转发至店铺授权页面
        return "shop/shopauthmanage";
    }

    @RequestMapping(value = "/shopauthedit")
    public String shopAuthEdit() {
        //转发至授权信息修改页面
        return "shop/shopauthedit";
    }

    @RequestMapping(value = "/operationsuccess")
    public String operationSuccess() {
        //转发至操作成功的页面
        return "shop/operationsuccess";
    }

    @RequestMapping(value = "/operationfail")
    public String operationFail() {
        //转发至操作失败的页面
        return "shop/operationfail";
    }

    @RequestMapping(value = "/productbuycheck")
    public String productBuyCheck() {
        //转发至店铺的消费记录的页面
        return "shop/productbuycheck";
    }
}
