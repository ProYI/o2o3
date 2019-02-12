/**
 * Copyright (C), 2018-2019, 兔讯科技有限公司
 * FileName: JWTToken
 * Author: 彭陈
 * Date: 2019/1/16 16:39
 */


package vip.proyi.shiro;


import org.apache.shiro.authc.AuthenticationToken;

/**

 * 〈〉
 *
 * @author 彭陈
 * @create 2019/1/16
 * @since 1.0.0
 */

public class JWTToken implements AuthenticationToken {

    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}