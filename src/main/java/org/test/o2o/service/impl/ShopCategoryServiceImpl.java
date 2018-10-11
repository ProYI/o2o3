/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ShopCategoryServiceImpl
 * Author: Administrator
 * Date: 2018-09-11 22:37
 * Description: 店铺类别实现类
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.o2o.dao.ShopCategoryDao;
import org.test.o2o.entity.ShopCategory;
import org.test.o2o.service.ShopCategoryService;

import java.util.List;

/**

 * 〈功能简述〉<br>

 * 〈店铺类别实现类〉

 *

 * @author Administrator

 * @create 2018-09-11

 * @since 1.0.0

 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService{
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }
}