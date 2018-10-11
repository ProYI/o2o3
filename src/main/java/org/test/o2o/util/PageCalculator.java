/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: PageCalculator
 * Author: Administrator
 * Date: 2018-09-15 17:06
 * Description: 分页数据的前后端转换
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.util;


/**

 * 〈功能简述〉<br>

 * 〈分页数据的前后端转换〉

 *

 * @author Administrator

 * @create 2018-09-15

 * @since 1.0.0

 */

public class PageCalculator {
    public static int calculateRowIndex(int pageIndex,int pageSize){
        return (pageIndex>0)?(pageIndex-1)*pageSize:0;
    }
}