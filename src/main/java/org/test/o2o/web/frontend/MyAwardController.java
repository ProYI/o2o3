/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: MyAwardController
 * Author: Administrator
 * Date: 2018-10-23 16:56
 * Description:
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
import org.test.o2o.dto.UserAwardMapExecution;
import org.test.o2o.entity.Award;
import org.test.o2o.entity.PersonInfo;
import org.test.o2o.entity.UserAwardMap;
import org.test.o2o.enums.UserAwardMapStateEnum;
import org.test.o2o.service.AwardService;
import org.test.o2o.service.PersonInfoService;
import org.test.o2o.service.UserAwardMapService;
import org.test.o2o.util.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
*
*/
@Controller
@RequestMapping("/frontend")
public class MyAwardController {
    @Autowired
    private UserAwardMapService userAwardMapService;
    @Autowired
    private AwardService awardService;
    @Autowired
    private PersonInfoService personInfoService;

    @RequestMapping(value = "/adduserawardmap", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> addUserAwardMap(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //从session中获取用户信息
        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
        //从前端请求中获取奖品Id
        Long awardId = HttpServletRequestUtil.getLong(request, "awardId");
        //封装成用户奖品映射对象
        UserAwardMap userAwardMap = compactUserAwardMap4Add(user, awardId);
        //空值判断
        if (userAwardMap != null) {
            try {
                //添加兑换信息
                UserAwardMapExecution se = userAwardMapService.addUserAwardMap(userAwardMap);
                if (se.getState() == UserAwardMapStateEnum.SUCCESS.getState()) {
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
            modelMap.put("errMsg", "请选择领取的奖品");
        }
        return modelMap;
    }

    /**
     * 通过奖品名查询时拼接查询条件
     */
    private UserAwardMap compactUserAwardMap4Add(PersonInfo user, Long awardId) {
        UserAwardMap userAwardMap = null;
        if (user != null && user.getUserId() != null && awardId!=-1) {
            userAwardMap = new UserAwardMap();
            PersonInfo personInfo = personInfoService.getPersonInfoById(user.getUserId());
            Award award = awardService.getAwardById(awardId);
            userAwardMap.setUser(personInfo);
            userAwardMap.setAward(award);
            userAwardMap.setPoint(award.getPoint());
            userAwardMap.setCreateTime(new Date());
            userAwardMap.setUsedStatus(1);
        }
        return userAwardMap;
    }
}