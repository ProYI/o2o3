/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ShopCategoryDao
 * Author: Administrator
 * Date: 2018-09-11 22:06
 * Description: 获取店铺分类
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dao;


import org.apache.ibatis.annotations.Param;
import org.test.o2o.entity.ShopCategory;

import java.util.List;

/**

 * 〈功能简述〉<br>

 * 〈获取店铺分类〉

 *

 * @author Administrator

 * @create 2018-09-11

 * @since 1.0.0

 */

public interface ShopCategoryDao {
    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
}