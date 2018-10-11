/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: EncryptPropertyPlaceholderConfigurer
 * Author: Administrator
 * Date: 2018-09-27 12:38
 * Description: 解密程序
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.util;


import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 〈解密程序〉
 */

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    //需要加密的字段数组
    private String[] encryptPropNames = { "jdbc.slave.username", "jdbc.slave.password" , "jdbc.master.username", "jdbc.master.password" };


    /**
     * 对关键的属性进行转换
     * @param propertyName
     * @param propertyValue
     * @return
     */
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncryptProp(propertyName)) {
            //对已加密的字段进行解密工作
            String decryptValue = DESUtils.getDecryptString(propertyValue);
            return decryptValue;
        } else {
            return propertyValue;
        }
    }

    /**
     * 该属性是否已加密
     * @param propertyName
     * @return
     * 遍历数组，是否有和传入字段名相同的
     */
    private boolean isEncryptProp(String propertyName) {
        for (String encryptpropertyName : encryptPropNames) {
            if (encryptpropertyName.equals(propertyName))
                return true;
        }
        return false;
    }
}