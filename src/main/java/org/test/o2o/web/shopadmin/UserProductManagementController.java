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
import org.test.o2o.dto.EchartSeries;
import org.test.o2o.dto.EchartXAxis;
import org.test.o2o.dto.UserProductMapExecution;
import org.test.o2o.dto.UserShopMapExecution;
import org.test.o2o.entity.*;
import org.test.o2o.service.ProductSellDailyService;
import org.test.o2o.service.UserProductMapService;
import org.test.o2o.service.UserShopMapService;
import org.test.o2o.util.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private UserShopMapService userShopMapService;

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

    @RequestMapping(value = "/listproductselldailyinfobyshop", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listProductSellDailyInfoByShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //获取当前店铺的信息
        Shop currentShop = (Shop) request.getSession().getAttribute( "currentShop");
        //空值校验 确保正shopId不为空
        if ((currentShop!=null) && (currentShop.getShopId()!=null)) {
            //添加查询条件
            ProductSellDaily productSellDailyCondition = new ProductSellDaily();
            productSellDailyCondition.setShop(currentShop);
            Calendar calendar = Calendar.getInstance();
            //获取昨天的日期
            calendar.add(Calendar.DATE, -1);
            Date endTime = calendar.getTime();
            //获取七天前的日期
            calendar.add(Calendar.DATE, -6);
            Date beginTime = calendar.getTime();
            //根据传入的查询条件获取该店铺的商品销售情况
            List<ProductSellDaily> productSellDailyList = productSellDailyService.listProductSellDaily(productSellDailyCondition, beginTime, endTime);
            //指定日期格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //商品名列表，保证唯一性
            HashSet<String> legendData = new HashSet<String>();
            //x轴数据
            HashSet<String> xData = new HashSet<String>();
            //定义series
            List<EchartSeries> series = new ArrayList<EchartSeries>();
            //日销量列表
            List<Integer> totalList = new ArrayList<Integer>();
            //当前商品名，默认为空
            String currentProductName = "";
            for (int i=0; i<productSellDailyList.size(); i++) {
                ProductSellDaily productSellDaily = productSellDailyList.get(i);
                //自动去重
                legendData.add(productSellDaily.getProduct().getProductName());
                xData.add(sdf.format(productSellDaily.getCreateTime()));
                if (!currentProductName.equals(productSellDaily.getProduct().getProductName()) && !currentProductName.isEmpty()) {
                    //如果currentProductName不等于获取的商品名。或者已遍历到列表的末尾，且currentProductName不为空
                    //则是遍历到下一个商品的日销量信息，将前一轮遍历的信息放入series当中
                    //包括了商品名以及与商品对应的统计日期以及当日销量
                    EchartSeries es = new EchartSeries();
                    es.setName(currentProductName);
                    es.setData(totalList.subList(0, totalList.size()));
                    series.add(es);
                    //重置totalList
                    totalList = new ArrayList<Integer>();
                    //变换下currentProductId为当前的productId
                    currentProductName = productSellDaily.getProduct().getProductName();
                    //继续添加新的值
                    totalList.add(productSellDaily.getTotal());
                } else {
                    //如果还是当前的productId则继续添加新值
                    totalList.add(productSellDaily.getTotal());
                    currentProductName = productSellDaily.getProduct().getProductName();
                }
                //队列之末，需要将最后的一个商品销量信息也添加上
                if (i == productSellDailyList.size() - 1) {
                    EchartSeries es = new EchartSeries();
                    es.setName(currentProductName);
                    es.setData(totalList.subList(0, totalList.size()));
                    series.add(es);
                }
            }
            modelMap.put("series", series);
            modelMap.put("legendData", legendData);
            //拼接出xAxis
            List<EchartXAxis> xAxis = new ArrayList<EchartXAxis>();
            EchartXAxis exa = new EchartXAxis();
            exa.setData(xData);
            xAxis.add(exa);
            modelMap.put("xAxis", xAxis);
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopId");
        }
        return modelMap;
    }

    @RequestMapping(value = "/listusershopmapsbyshop", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listUserShopMapsByShop(HttpServletRequest request) {
        Map<String, Object> modelmap = new HashMap<String, Object>();
        //获取分页信息
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        //从session中获取当前店铺的信息
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        //空值判断
        if ((pageIndex!=-1) && (pageSize!=-1) && (currentShop!=null) && (currentShop.getShopId()!=null)) {
            UserShopMap userShopMapCondition = new UserShopMap();
            //传入查询条件
            userShopMapCondition.setShop(currentShop);
            String userName = HttpServletRequestUtil.getString(request, "userName");
            if (userName != null) {
                //若传入顾客名，则按照顾客名模糊查询
                PersonInfo customer = new PersonInfo();
                customer.setName(userName);
                userShopMapCondition.setUser(customer);
            }
            //分页获取该店铺下的顾客积分列表
            UserShopMapExecution ue = userShopMapService.listUserShopMap(userShopMapCondition, pageIndex, pageSize);
            modelmap.put("userShopMapList", ue.getUserShopMapList());
            modelmap.put("count", ue.getCount());
            modelmap.put("success", true);
        } else {
            modelmap.put("success", false);
            modelmap.put("errMsg", "empty pageSize or pageIndex or shopId");
        }
        return modelmap;
    }
}