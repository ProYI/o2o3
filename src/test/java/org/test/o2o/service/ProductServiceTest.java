/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductServiceTest
 * Author: Administrator
 * Date: 2018-09-18 13:02
 * Description: 商品业务单元测试
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.service;


import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.test.o2o.dto.ImageHolder;
import org.test.o2o.dto.ProductExecution;
import org.test.o2o.entity.Product;
import org.test.o2o.entity.ProductCategory;
import org.test.o2o.entity.Shop;
import org.test.o2o.enums.ProductStateEnum;
import org.test.o2o.exceptions.ProductOperationException;
import org.test.o2o.exceptions.ShopOperationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**

 * 〈功能简述〉<br>

 * 〈商品业务单元测试〉

 *

 * @author Administrator

 * @create 2018-09-18

 * @since 1.0.0

 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    @Ignore
    public void testAAddProduct() throws ShopOperationException, FileNotFoundException, ProductOperationException {
        //创建shopId为1且productCategoryId为1的商品实例并给其成员变量赋值
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(1L);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("测试商品1");
        product.setProductDesc("测试商品1");
        product.setPriority(20);
        product.setCreateTime(new Date());
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());

        //创建缩略图文件流
        File thumbnailFile=new File("D:/1.jpg");
        InputStream is=new FileInputStream(thumbnailFile);
        ImageHolder thumbnail=new ImageHolder(thumbnailFile.getName(),is);

        //创建两个商品详情图文件流并将他们添加到详情列表中
        File productImg1 = new File("D:/testnormal1.jpg");
        InputStream is1 = new FileInputStream(productImg1);
        File productImg2 = new File("D:/testnormal2.jpg");
        InputStream is2 = new FileInputStream(productImg2);
        List<ImageHolder> productImgList=new ArrayList<>();
        productImgList.add(new ImageHolder(productImg1.getName(),is1));
        productImgList.add(new ImageHolder(productImg2.getName(),is2));

        //添加商品并验证
        ProductExecution pe=productService.addProduct(product,thumbnail,productImgList);
        System.out.println(pe.getState());
    }

    @Test
    @Ignore
    public void testModifyProduct() throws ShopOperationException,FileNotFoundException{
        //创建shopId为1且productCategoryId为1的商品实例并给其成员变量赋值
        Product product=new Product();
        Shop shop=new Shop();
        shop.setShopId(1L);
        ProductCategory pc=new ProductCategory();
        pc.setProductCategoryId(1L);
        product.setProductId(1L);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("正式的商品");
        product.setProductDesc("正式的商品");
        //创建缩略文件流
        File thumbnailFile=new File("D:/1.jpg");
        InputStream is=new FileInputStream(thumbnailFile);
        ImageHolder thumbnail=new ImageHolder(thumbnailFile.getName(),is);
        //创建两个商品详情图文件流并将它们添加到详情图列表中
        File productImg1=new File("D:/testnormal1.jpg");
        InputStream is1=new FileInputStream(productImg1);
        File productImg2=new File("D:/testnormal2.jpg");
        InputStream is2=new FileInputStream(productImg2);
        List<ImageHolder> productImgList=new ArrayList<>();
        productImgList.add(new ImageHolder(productImg1.getName(),is1));
        productImgList.add(new ImageHolder(productImg2.getName(),is2));
        //添加商品并验证
        ProductExecution pe=productService.modifyProduct(product,thumbnail,productImgList);
        System.out.println(pe.getState());
    }
}