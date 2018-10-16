/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ShopOperationException
 * Author: Administrator
 * Date: 2018-10-16 15:31
 * Description: 店铺授权关系映射异常
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.exceptions;


/**
 * 〈店铺授权关系映射异常〉
 */

public class ShopAuthMapOperationException extends RuntimeException {
    public ShopAuthMapOperationException(String msg) {
        super(msg);
    }
}