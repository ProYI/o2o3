/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: WechatAuthOperationException
 * Author: Administrator
 * Date: 2018-09-26 14:45
 * Description:
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.exceptions;


/**
 *〈微信操作的异常信息〉
 */

public class WechatAuthOperationException extends RuntimeException {
    public WechatAuthOperationException(String msg) {
        super(msg);
    }
}