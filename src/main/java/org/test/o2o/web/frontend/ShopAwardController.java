/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ShopAwardController
 * Author: Administrator
 * Date: 2018-10-23 16:22
 * Description: 店铺奖品列表
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.web.frontend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.test.o2o.dto.AwardExecution;
import org.test.o2o.entity.Award;
import org.test.o2o.entity.PersonInfo;
import org.test.o2o.entity.UserShopMap;
import org.test.o2o.service.AwardService;
import org.test.o2o.service.UserShopMapService;
import org.test.o2o.util.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈店铺奖品列表〉
 */
@Controller
@RequestMapping("/frontend")
public class ShopAwardController {
    @Autowired
    private UserShopMapService userShopMapService;
    @Autowired
    private AwardService awardService;

    @RequestMapping(value = "/listawardsbyshop", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listAwardsByShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        //获取分页信息
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        //获取店铺Id
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");

        //空值判断
        if ((pageIndex>-1) && (pageSize>-1) && (shopId>-1)) {
            //获取前端可能输入的奖品名模糊查询
            String awardName = HttpServletRequestUtil.getString(request, "awardName");
            Award awardCondition = compactAwardCondition4Search(shopId, awardName);
            //传入查询条件分页获取奖品信息
            AwardExecution ae = awardService.getAwardList(awardCondition, pageIndex, pageSize);
            modelMap.put("awardList", ae.getAwardList());
            modelMap.put("count", ae.getCount());
            modelMap.put("success", true);

            //从session中获取用户信息，主要时为了显示该用户在本店铺的积分
            PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
            //空值判断
            if (user!=null && user.getUserId()!=null) {
                //获取该用户在本店铺的积分信息
                UserShopMap userShopMap = userShopMapService.getUserShopMap(user.getUserId(), shopId);
                if (userShopMap == null) {
                    modelMap.put("totalPoint", 0);
                } else {
                    modelMap.put("totalPoint", userShopMap.getPoint());
                }
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopId or pageIndex or pageSize");
        }
        return modelMap;
    }

    /**
     * 通过奖品名查询时拼接查询条件
     */
    private Award compactAwardCondition4Search(Long shopId, String awardName) {
        Award awardCondition = new Award();
        awardCondition.setShopId(shopId);
        if (awardName != null) {
            awardCondition.setAwardName(awardName);
        }
        return awardCondition;
    }
}