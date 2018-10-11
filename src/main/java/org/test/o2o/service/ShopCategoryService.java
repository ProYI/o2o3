/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ShopCategoryService
 * Author: Administrator
 * Date: 2018-09-11 22:36
 * Description: 店铺类别service
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.service;


import org.test.o2o.entity.ShopCategory;

import java.util.List;

/**

 * 〈功能简述〉<br>

 * 〈店铺类别service〉

 *

 * @author Administrator

 * @create 2018-09-11

 * @since 1.0.0

 */

public interface ShopCategoryService {
    /**
    * 功能描述:
    *〈根据查询条件获取ShopCategory列表〉
    * @param: ShopCategory
    * @return:
    */
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}