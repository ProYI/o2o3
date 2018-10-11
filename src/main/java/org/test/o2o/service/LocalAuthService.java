package org.test.o2o.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.test.o2o.dto.LocalAuthExecution;
import org.test.o2o.entity.LocalAuth;
import org.test.o2o.exceptions.LocalAuthOperationException;

public interface LocalAuthService {
    /**
     * 通过账号和密码获取平台账号信息
     * @param userName
     * @return
     */
    LocalAuth getLocalAuthByUsernameAndPwd(String userName, String password);

    /**
     * 通过userId获取平台账号信息
     * @param userId
     * @return
     */
    LocalAuth getLocalAuthByUserId(long userId);

    /**
     * 绑定微信,生成平台专属账号
     * @param localAuth
     * @return
     * @throws RuntimeException
     */
    LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;

    /**
    * 修改平台账号的登录密码
    * @param: localAuthId
    * @param: userName
    * @param: password
    * @param: newPassword
    * @param: lastEditTime
    * @return:
    */
    LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword) throws LocalAuthOperationException;
}
