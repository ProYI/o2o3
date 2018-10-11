package org.test.o2o.dao;

import org.apache.ibatis.annotations.Param;
import org.test.o2o.entity.LocalAuth;

import java.util.Date;

public interface LocalAuthDao {
    /**
     *  通过账号和密码查询对应信息,登录时使用
     * @param userName
     * @param password
     * @return
     */
    LocalAuth queryLocalByUserNameAndPwd(@Param("userName") String userName, @Param("password") String password);

    /**
     *通过用户id查询对应localauth
     * @param userId
     * @return
     */
    LocalAuth queryLocalByUserId(@Param("userId") long userId);

    /**
     *添加平台账号
     * @param localAuth
     * @return
     */
    int insertLocalAuth(LocalAuth localAuth);

    /**
     * 通过userId,username,password修改密码
     * @return
     */
    int updateLocalAuth(@Param("userId") Long userId, @Param("username") String userName, @Param("password") String password, @Param("newPassword") String newPassword, @Param("lastEditTime") Date lastEditTime);
}
