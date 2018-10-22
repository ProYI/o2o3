/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserAwardManagementController
 * Author: Administrator
 * Date: 2018-10-22 11:53
 * Description: 奖品相关Controller
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.web.shopadmin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.test.o2o.dto.UserAwardMapExecution;
import org.test.o2o.entity.Award;
import org.test.o2o.entity.Shop;
import org.test.o2o.entity.UserAwardMap;
import org.test.o2o.service.UserAwardMapService;
import org.test.o2o.util.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈奖品相关Controller〉
 */
@Controller
@RequestMapping("/shopadmin")
public class UserAwardManagementController {
    @Autowired
    private UserAwardMapService userAwardMapService;

    @RequestMapping(value = "/listuserawardmapsbyshop", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listUserAwardMapsByShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //从session里获取店铺信息
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        //获取分页信息
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        //空值判断
        if ((pageIndex>-1) && (pageSize>-1) && (currentShop!=null) && (currentShop.getShopId()!=null)) {
            UserAwardMap userAwardMap = new UserAwardMap();
            userAwardMap.setShop(currentShop);
            //从请求中获取奖品名
            String awardName = HttpServletRequestUtil.getString(request, "awardName");
            if (awardName != null) {
                //如果需要按照奖品名称搜索，则添加搜索条件
                Award award = new Award();
                award.setAwardName(awardName);
                userAwardMap.setAward(award);
            }
            //分页返回结果
            UserAwardMapExecution ue = userAwardMapService.listUserAwardMap(userAwardMap, pageIndex, pageSize);
            modelMap.put("userAwardMapList", ue.getUserAwardMapList());
            modelMap.put("count", ue.getCount());
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopId or pageIndex or pageSize");
        }
        return modelMap;
    }
}