/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: MyX509TrustManager
 * Author: Administrator
 * Date: 2018-09-22 19:26
 * Description: 证书信任管理器（用于https请求）
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.util.wechat;


import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 〈证书信任管理器（用于https请求）〉
 */

public class MyX509TrustManager implements X509TrustManager {
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}