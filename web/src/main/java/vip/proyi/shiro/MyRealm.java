/**
 * Copyright (C), 2018-2019, 兔讯科技有限公司
 * FileName: MyRealm
 * Author: 彭陈
 * Date: 2019/1/16 11:41
 */


package vip.proyi.shiro;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**

 * 〈自定义Realm〉
 *
 * @author 彭陈
 * @create 2019/1/16
 * @since 1.0.0
 */

public class MyRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}