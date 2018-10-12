/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ShopManagementController
 * Author: Administrator
 * Date: 2018-09-11 13:23
 * Description: 店铺Controller
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
import org.test.o2o.dto.ImageHolder;
import org.test.o2o.dto.ShopExecution;
import org.test.o2o.entity.Area;
import org.test.o2o.entity.PersonInfo;
import org.test.o2o.entity.Shop;
import org.test.o2o.entity.ShopCategory;
import org.test.o2o.enums.ShopStateEnum;
import org.test.o2o.exceptions.ShopOperationException;
import org.test.o2o.service.AreaService;
import org.test.o2o.service.ShopCategoryService;
import org.test.o2o.service.ShopService;
import org.test.o2o.util.CodeUtil;
import org.test.o2o.util.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**

 * 〈功能简述〉<br>

 * 〈店铺Controller〉

 *

 * @author Administrator

 * @create 2018-09-11

 * @since 1.0.0

 */
@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;

    //判断是否登录的操作，是否违规
    @RequestMapping(value = "/getshopmanagementinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopManagementInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        //前端没有传shopId，可以通过session获取
        if (shopId <= 0) {
            Object currentShopObj = request.getSession().getAttribute("currentShop");
            if (currentShopObj == null) {
                modelMap.put("redirect", true);
                modelMap.put("url", "/o2o/shopadmin/shoplist");
            } else {
                Shop currentShop = (Shop) currentShopObj;
                modelMap.put("redirect", false);
                modelMap.put("shopId", currentShop.getShopId());
            }
        } else {
            Shop currentShop = new Shop();
            currentShop.setShopId(shopId);
            request.getSession().setAttribute("currentShop", currentShop);
            modelMap.put("redirect", false);
        }
        return modelMap;
    }

    //获取店铺列表功能
    @RequestMapping(value = "/getshoplist", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopList(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
        try {
            Shop shopCondition = new Shop();
            shopCondition.setOwner(user);
            ShopExecution shopExecution = shopService.getShopList(shopCondition, 0, 100);
            modelMap.put("shopList", shopExecution.getShopList());
            //列出店铺成功后，将店铺放入session中作为权限验证依据，即该账号只能操作它自己的店铺
            request.getSession().setAttribute("shopList", shopExecution.getShopList());
            modelMap.put("user", user);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }
    //注册功能
    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }

        //1.接收并转化相应的参数，包括店铺信息及图片信息
        /*
        表单提交的json转化通过json-databind
         */
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        //处理图片
        CommonsMultipartFile shopImg = null;
        //文件上传解析器CommonsMultipartResolver通过session获取上传文件内容
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //是否有上传的流对象
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            //提取相对应的文件流
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }

        //2.注册店铺
        if (shop!=null && shopImg!=null) {
            PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
            shop.setOwner(owner);

            ////CommonsMultipartFile转换成File，路径随便传一个即可
            //File shopImgFile = new File(PathUtil.getImgBasePath() + ImageUtil.getRandomFileName());
            //try {
            //    shopImgFile.createNewFile();
            //} catch (IOException e) {
            //    modelMap.put("success", false);
            //    modelMap.put("errMsg", e.getMessage());
            //    return modelMap;
            //}
            //
            //try {
            //    inputStreamToFile(shopImg.getInputStream(), shopImgFile);
            //} catch (IOException e) {
            //    modelMap.put("success", false);
            //    modelMap.put("errMsg", e.getMessage());
            //    return modelMap;
            //}

            ShopExecution se;
            try {
                ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(), shopImg.getInputStream());
                se = shopService.addShop(shop, imageHolder);
                if (se.getState() == ShopStateEnum.CHECK.getState()) {
                    modelMap.put("success", true);
                    //该用户可以操作的店铺列表
                    List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
                    if (shopList==null || shopList.size()==0) {
                        shopList = new ArrayList<Shop>();
                    }

                    //不管有没有店铺列表，最终都要把此次建立的店铺添加到列表中，并添加到session中
                    shopList.add(se.getShop());
                    request.getSession().setAttribute("shopList", shopList);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (ShopOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
            return modelMap;
        }

        //3.返回结果
    }

    ///*
    //由于addShop接收图片使用的是file对象，所以需要将上传的MultipartFile转换成file对象，通过其getInputStream可以实现
    // */
    //private static void inputStreamToFile(InputStream inputStream, File file) {
    //    FileOutputStream outputStream = null;
    //    try {
    //        outputStream = new FileOutputStream(file);
    //        int len = 0;
    //        byte[] buffer = new byte[1024];
    //        while ((len = inputStream.read(buffer)) != -1) {
    //            outputStream.write(buffer, 0 , len);
    //        }
    //    } catch (Exception e) {
    //        throw new RuntimeException("调用InputStreamToFile产生异常" + e.getMessage());
    //    } finally {
    //        try {
    //            if (outputStream != null) {
    //                outputStream.close();
    //            }
    //            if (inputStream != null) {
    //                inputStream.close();
    //            }
    //        } catch (IOException e) {
    //            throw new RuntimeException("调用InputStreamToFile关闭io产生异常" + e.getMessage());
    //        }
    //    }
    //}

    //获取区域列表和店铺类别
    @RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopInitInfo() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
        List<Area> areaList = new ArrayList<Area>();
        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }

    //通过店铺id获取店铺信息
    @RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopById(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId > -1) {
            try {
                Shop shop = shopService.getByShopId(shopId);
                List<Area> areaList = areaService.getAreaList();
                modelMap.put("shop", shop);
                modelMap.put("areaList", areaList);
                modelMap.put("success", true);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopId");
        }
        return modelMap;
    }

    //修改店铺信息
    @RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyShop(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }

        //1.接收并转化相应的参数，包括店铺信息及图片信息
        /*
        表单提交的json转化通过json-databind
         */
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        //处理图片
        CommonsMultipartFile shopImg = null;
        //文件上传解析器CommonsMultipartResolver通过session获取上传文件内容
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //是否有上传的流对象
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            //提取相对应的文件流
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");

        }

        //2.修改店铺信息
        if (shop!=null && shop.getShopId()!=null) {
            PersonInfo owner = new PersonInfo();

            ShopExecution se;
            try {
                if (shopImg == null) {
                    se = shopService.modifyShop(shop, null);
                } else {
                    ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(), shopImg.getInputStream());
                    se = shopService.modifyShop(shop, imageHolder);
                }
                if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (ShopOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺Id");
            return modelMap;
        }

        //3.返回结果
    }
}