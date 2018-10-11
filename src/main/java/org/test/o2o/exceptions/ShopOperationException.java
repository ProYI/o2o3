/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ShopOperationException
 * Author: Administrator
 * Date: 2018-09-11 0:59
 * Description: 店铺相关的业务异常
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.exceptions;


/**

 * 〈功能简述〉<br>

 * 〈店铺相关的业务异常〉

 *

 * @author Administrator

 * @create 2018-09-11

 * @since 1.0.0

 */

public class ShopOperationException extends RuntimeException {
    public ShopOperationException(String msg) {
        super(msg);
    }
}