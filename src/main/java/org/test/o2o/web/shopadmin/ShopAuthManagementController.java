/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ShopAuthManagementController
 * Author: Administrator
 * Date: 2018-10-16 21:39
 * Description: 店铺授权Controller
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.web.shopadmin;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.test.o2o.dto.ShopAuthMapExecution;
import org.test.o2o.entity.PersonInfo;
import org.test.o2o.entity.Shop;
import org.test.o2o.entity.ShopAuthMap;
import org.test.o2o.enums.ShopAuthMapStateEnum;
import org.test.o2o.service.ShopAuthMapService;
import org.test.o2o.util.CodeUtil;
import org.test.o2o.util.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈店铺授权Controller〉
 */
@Controller
@RequestMapping("/shopadmin")
public class ShopAuthManagementController {
    @Autowired
    private ShopAuthMapService shopAuthMapService;

    @RequestMapping(value = "/listshopauthmapsbyshop", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listShopAuthMapsByShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //取出分页信息
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        //从session中获取店铺信息
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        //空值判断
        if ((pageIndex>-1) && (pageSize>-1) && (currentShop!=null) && (currentShop.getShopId()!=null)) {
            //分页取出该店铺下面的授权信息列表
            ShopAuthMapExecution se = shopAuthMapService.listShopAuthMapByShopId(currentShop.getShopId(), pageIndex, pageSize);
            modelMap.put("shopAuthMapList", se.getShopAuthMapList());
            modelMap.put("count", se.getCount());
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
        }
        return modelMap;
    }

    @RequestMapping(value = "/getshopauthmapbyid", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopAuthMapById(@RequestParam Long shopAuthId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //非空判断
        if (shopAuthId != null && shopAuthId > -1) {
            //根据前台传入的shopAuthId查找对应的授权信息
            ShopAuthMap shopAuthMap = shopAuthMapService.getShopAuthMapById(shopAuthId);
            modelMap.put("shopAuthMap", shopAuthMap);
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopAuthId");
        }
        return modelMap;
    }

    @RequestMapping(value = "/modifyshopauthmap", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyShopAuthMap(String shopAuthMapStr, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //是授权边检时调用还是删除/恢复授权操作的时候调用
        //若为前者则进行验证码判断，后者则跳过验证码判断
        boolean statusChange = HttpServletRequestUtil.getBoolean(request, "statusChange");
        //验证码校验
        if (!statusChange && !CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        ShopAuthMap shopAuthMap = null;
        try {
            //将前台传入的字符串json转换成shopAuthMap实例
            shopAuthMap = mapper.readValue(shopAuthMapStr, ShopAuthMap.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        //空值判断
        if (shopAuthMap!=null && shopAuthMap.getShopAuthId()!=null) {
            try {
                //看看被操作的对方是否为店家本身，店家本身不支持修改
                if (!checkPermission(shopAuthMap.getShopAuthId())) {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", "无法对店家本身权限做操作（已是店铺的最高权限）");
                    return modelMap;
                }

                ShopAuthMapExecution se = shopAuthMapService.modifyShopAuthMap(shopAuthMap);
                if (se.getState() == ShopAuthMapStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入要修改的授权信息");
        }
        return modelMap;
    }

    /**
    * 检查操作的对象是否可修改
    * @param: shopAuthId
    * @return:
    */
    private boolean checkPermission(Long shopAuthId) {
        ShopAuthMap grantedPerson = shopAuthMapService.getShopAuthMapById(shopAuthId);
        if (grantedPerson.getTitleFlag() == 0) {
            //若是店家本身，不能操作
            return false;
        } else {
            return true;
        }
    }
}