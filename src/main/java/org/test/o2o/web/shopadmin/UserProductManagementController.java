/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserProductManagementController
 * Author: Administrator
 * Date: 2018-10-20 10:50
 * Description: 商铺销量管理
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.web.shopadmin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.test.o2o.dto.UserProductMapExecution;
import org.test.o2o.entity.Product;
import org.test.o2o.entity.ProductSellDaily;
import org.test.o2o.entity.Shop;
import org.test.o2o.entity.UserProductMap;
import org.test.o2o.service.ProductSellDailyService;
import org.test.o2o.service.UserProductMapService;
import org.test.o2o.util.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 〈商铺销量管理〉
 */
@Controller
@RequestMapping("/shopadmin")
public class UserProductManagementController {
    @Autowired
    private UserProductMapService userProductMapService;
    @Autowired
    private ProductSellDailyService productSellDailyService;

    @RequestMapping(value = "/listuserproductmapsbyshop", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listUserProductMapsByShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new ModelMap();
        //获取分页信息
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        //获取当前的店铺信息
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        //空值校验 主要确保shopId不为空
        if ((pageIndex>-1) && (pageSize>-1) && (currentShop!=null) && (currentShop.getShopId()!=null)) {
            //添加查询条件
            UserProductMap userProductMapCondition = new UserProductMap();
            userProductMapCondition.setShop(currentShop);
            String productName = HttpServletRequestUtil.getString(request, "productName");
            if (productName != null) {
                //若前端想按照商品名模糊查询，则传入productName
                Product product = new Product();
                product.setProductName(productName);
                userProductMapCondition.setProduct(product);
            }
            //根据传入的查询条件获取该店铺的商品销售情况
            UserProductMapExecution ue = userProductMapService.listUserProductMap(userProductMapCondition, pageIndex, pageSize);
            modelMap.put("userProductMapList", ue.getUserProductMapList());
            modelMap.put("count", ue.getCount());
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
        }
        return modelMap;
    }


}