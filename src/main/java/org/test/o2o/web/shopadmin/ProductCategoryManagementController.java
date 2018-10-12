/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductCategoryManagementController
 * Author: Administrator
 * Date: 2018-09-16 12:18
 * Description: 商品类别管理controller
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.web.shopadmin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.test.o2o.dto.ProductCategoryExecution;
import org.test.o2o.dto.Result;
import org.test.o2o.entity.ProductCategory;
import org.test.o2o.entity.Shop;
import org.test.o2o.enums.ProductCategoryStateEnum;
import org.test.o2o.exceptions.ProductCategoryOperationException;
import org.test.o2o.service.ProductCategoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**

 * 〈功能简述〉<br>

 * 〈商品类别管理controller〉

 *

 * @author Administrator

 * @create 2018-09-16

 * @since 1.0.0

 */
@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {
    @Autowired
    private ProductCategoryService productCategoryService;

    //删除商品类别
    @RequestMapping(value = "/removeproductcategory", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> removeProductCategory(Long productCategoryId,HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<String, Object>();
        if(productCategoryId!=null && productCategoryId>0){
            try {
                Shop currentShop = (Shop)request.getSession().getAttribute("currentShop");
                ProductCategoryExecution pe = productCategoryService.deleteProductCategory(productCategoryId,currentShop.getShopId());
                if(pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put("success",true);
                } else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",pe.getStateInfo());
                }
            }catch (ProductCategoryOperationException e) {
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
                return modelMap;
            }
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","请至少选择一个商品类别");
        }
        return modelMap;
    }

    /**
     * 批量添加商品信息
     *
     * @param productCategoryList
     * @param request
     * @return
     */
    @RequestMapping(value = "/addproductcategorys", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> addProductCategorys(@RequestBody List<ProductCategory> productCategoryList, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        for (ProductCategory pc : productCategoryList) {
            pc.setShopId(currentShop.getShopId());
        }
        if (productCategoryList != null && productCategoryList.size()>0) {
            try {
                ProductCategoryExecution pe = productCategoryService.batchAddProductCategory(productCategoryList);
                if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            } catch (ProductCategoryOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请至少输入一个商品类别");
        }
        return modelMap;
    }

    @RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
    @ResponseBody
    private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request) {

        //Shop shop = new Shop();
        //shop.setShopId(1L);
        //request.getSession().setAttribute("currentShop", shop);
        //因为ShopManagementController的getShopManagementInfo会将shopId设置到session中，因此实际使用中直接从session中可以取到shopId的值

        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        List<ProductCategory> productCategoryList = null;
        if (currentShop!=null && currentShop.getShopId()>0) {
            productCategoryList = productCategoryService.getProductCategoryList(currentShop.getShopId());
            return new Result<List<ProductCategory>>(true, productCategoryList);
        } else {
            ProductCategoryStateEnum productcategoryStateEnum = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<List<ProductCategory>>(false, productcategoryStateEnum.getState(), productcategoryStateEnum.getStateInfo());
        }

    }
}