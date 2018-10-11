/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CodeUtil
 * Author: Administrator
 * Date: 2018-09-12 0:12
 * Description: 验证码验证
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.util;


import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

/**

 * 〈功能简述〉<br>

 * 〈验证码验证〉

 *

 * @author Administrator

 * @create 2018-09-12

 * @since 1.0.0

 */

public class CodeUtil {
    public static boolean checkVerifyCode(HttpServletRequest request) {
        //验证码内容
        String verifyCodeExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //用户输入内容
        String verifyCodeActual = HttpServletRequestUtil.getString(request,"verifyCodeActual");
        if (verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExpected)) {
            return false;
        }
        return true;
    }
}