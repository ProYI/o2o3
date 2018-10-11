/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TransactionManagementConfiguration
 * Author: Administrator
 * Date: 2018-10-11 22:26
 * Description: spring-service迁移里面的transactionManager
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.config.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * 〈对标spring-service里面的transactionManager〉
 *  继承TransactionManagementConfigurer里因为开启annotation-driven
 */
@Configuration
/*
首先使用注解@EnableTransactionManagement开启事务支持后
在service方法上添加注解@Transaction便可
*/
@EnableTransactionManagement
public class TransactionManagementConfiguration implements TransactionManagementConfigurer {
    @Autowired
    //注入DataSourceConfiguration里边的dataSource，通过createDataSource()获取
    private DataSource dataSource;

    @Override
    /*
     * 关于事务管理，需要返回PlatformTransactionManagement的实现
     */
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}