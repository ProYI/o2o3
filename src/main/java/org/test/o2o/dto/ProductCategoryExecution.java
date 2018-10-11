/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductCategoryExecution
 * Author: Administrator
 * Date: 2018-09-17 10:53
 * Description: 商品类别包装类
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dto;


import org.test.o2o.entity.ProductCategory;
import org.test.o2o.enums.ProductCategoryStateEnum;

import java.util.List;

/**

 * 〈功能简述〉<br>

 * 〈商品类别包装类〉

 *

 * @author Administrator

 * @create 2018-09-17

 * @since 1.0.0

 */

public class ProductCategoryExecution {
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;

    private List<ProductCategory> productCategoryList;

    //操作失败的时候使用的构造器
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
    }
    //操作成功的时候使用的构造器
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum, List<ProductCategory> productCategoryList){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.productCategoryList=productCategoryList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
}