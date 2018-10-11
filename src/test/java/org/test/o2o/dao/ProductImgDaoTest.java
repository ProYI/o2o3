/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductImgDaoTest
 * Author: Administrator
 * Date: 2018-09-17 21:02
 * Description: 商品详情图片测试
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dao;


import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.test.o2o.entity.ProductImg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**

 * 〈功能简述〉<br>

 * 〈商品详情图片测试〉

 *

 * @author Administrator

 * @create 2018-09-17

 * @since 1.0.0

 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgDaoTest {

    @Autowired
    private ProductImgDao productImgDao;

    @Test
    @Ignore
    public void testABatchImsertProductImg() throws Exception{
        //productId为1的商品里添加两个详情图片记录
        ProductImg productImg1=new ProductImg();
        productImg1.setImgAddr("图片1");
        productImg1.setImgDesc("测试图片1");
        productImg1.setPriority(1);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(1L);
        ProductImg productImg2=new ProductImg();
        productImg2.setImgAddr("图片2");
        productImg2.setImgDesc("测试图片2");
        productImg2.setPriority(1);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(1L);
        List<ProductImg> productImgList=new ArrayList<>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        int effectedNum=productImgDao.batchInsertProductImg(productImgList);
        System.out.println(effectedNum);
    }

    @Test
    @Ignore
    public void testBDeleteProductImgByProductId() throws Exception{
        //删除新增的两条商品详情图片记录
        long productId=1;
        int effectedNum=productImgDao.deleteProductImgByProductId(productId);
        System.out.println(effectedNum);
    }
}