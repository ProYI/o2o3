/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: AwardManagementController
 * Author: Administrator
 * Date: 2018-10-22 20:11
 * Description: 奖品管理Controller
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.web.shopadmin;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.test.o2o.dto.AwardExecution;
import org.test.o2o.dto.ImageHolder;
import org.test.o2o.dto.UserAwardMapExecution;
import org.test.o2o.entity.Award;
import org.test.o2o.entity.Shop;
import org.test.o2o.entity.UserAwardMap;
import org.test.o2o.enums.AwardStateEnum;
import org.test.o2o.service.AwardService;
import org.test.o2o.util.CodeUtil;
import org.test.o2o.util.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈奖品管理Controller〉
 */
@Controller
@RequestMapping("/shopadmin")
public class AwardManagementController {
    @Autowired
    private AwardService awardService;

    @RequestMapping(value = "/listawardsbyshop", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listAwardsByShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //获取分页信息
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        //从session中获取shopId
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        //空值校验
        if ((pageIndex>-1) && (pageSize>-1) && (currentShop!=null) && (currentShop.getShopId()!=null)) {
            //判断查询条件是否传入奖品名，有则模糊查询
            String awardName = HttpServletRequestUtil.getString(request, "awardName");
            //拼接查询条件
            Award awardCondition = compactAwardCondition4Search(currentShop.getShopId(), awardName);
            //根据查询条件分页获取奖品列表即总数
            AwardExecution ae = awardService.getAwardList(awardCondition, pageIndex, pageSize);
            modelMap.put("awardList", ae.getAwardList());
            modelMap.put("count", ae.getCount());
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopId or pageIndex or pageSize");
        }
        return modelMap;
    }

    @RequestMapping(value = "/modifyaward", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyAward(HttpServletRequest request) {
        Boolean statusChange = HttpServletRequestUtil.getBoolean(request, "statusChange");
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //根据传入的状态值确定是否跳过验证码校验
        if (!statusChange && !CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        //接收前端参数的变量的初始化，包括奖品信息，缩略图
        ObjectMapper mapper = new ObjectMapper();
        Award award;
        ImageHolder thumbnail = null;
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //请求中都带有multi字样，因此没法过滤，只能用来拦截外部非图片流的处理
        //里面有缩略图的空值判断，请放心使用
        try {
            if (multipartResolver.isMultipart(request)) {
                thumbnail = handleImage(request);
            }
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        try {
            String awardStr = HttpServletRequestUtil.getString(request, "awardStr");
            //尝试获取前端传过来的表单String流并将其换成Award实体类
            award = mapper.readValue(awardStr, Award.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        //空值判断
        if (award != null) {
            try {
                //从session中获取当前店铺的Id并赋值给award，减少对前端数据的依赖
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                award.setShopId(currentShop.getShopId());
                AwardExecution ae = awardService.modifyward(award, thumbnail);
                if (ae.getState() == AwardStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", ae.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入商品信息");
        }
        return modelMap;
    }

    /**
    * 处理奖品图片
    * @param: request
    * @return:
    */
    private ImageHolder handleImage(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        ImageHolder thumbnail;
        //取出缩略图并构建ImageHolder对象
        CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
        thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(), thumbnailFile.getInputStream());

        return thumbnail;
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