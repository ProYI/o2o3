/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductExecution
 * Author: Administrator
 * Date: 2018-09-17 22:12
 * Description:
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dto;


import org.test.o2o.entity.Product;
import org.test.o2o.enums.ProductStateEnum;

import java.util.List;

/**

 * 〈功能简述〉<br>

 * 〈商品包装类〉

 *

 * @author Administrator

 * @create 2018-09-17

 * @since 1.0.0

 */

public class ProductExecution {

    //结果状态
    private int state;
    //状态标识
    private String stateInfo;
    //商品数量
    private int count;
    //操作的product(增删改商品的时候使用)
    private Product product;
    //获取的product列表(查询商品列表的时候使用)
    private List<Product> productList;

    public ProductExecution(){

    }

    //操作失败的构造器
    public ProductExecution(ProductStateEnum stateEnum){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
    }

    //操作单个商品成功的构造器
    public ProductExecution(ProductStateEnum stateEnum, Product product){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.product=product;
    }

    //操作多个商品成功的构造器
    public ProductExecution(ProductStateEnum stateEnum, List<Product> productList){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.productList=productList;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}