/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DataSourceConfiguration
 * Author: Administrator
 * Date: 2018-10-11 9:57
 * Description: 数据源
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.config.dao;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.test.o2o.util.DESUtils;

import java.beans.PropertyVetoException;

/**

 * 〈功能简述〉<br>

 * 〈数据源〉

 *  配置DataSource到ioc容器中

 * @author Administrator

 * @create 2018-10-11

 * @since 1.0.0

 */
@Configuration
//配置mybatis mapper的扫描路径
@MapperScan("org.test.o2o.dao")
public class DataSourceConfiguration {
    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.master.url}")
    private String jdbcUrl;
    @Value("${jdbc.master.username}")
    private String jdbcUsername;
    @Value("${jdbc.master.password}")
    private String jdbcPassword;

    /**
    * 
    * 生成与spring-dao.xml对应的bean dataSource
    * @return:
    */
    @Bean(name = "dataSource")
    public ComboPooledDataSource createDateSource() throws PropertyVetoException {
        //生产dataSource实例
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        //跟配置文件一样设置以下信息
        //驱动
        dataSource.setDriverClass(jdbcDriver);
        //数据库连接url
        dataSource.setJdbcUrl(jdbcUrl);
        //用户名
        dataSource.setUser(DESUtils.getDecryptString(jdbcUsername));
        //密码
        dataSource.setPassword(DESUtils.getDecryptString(jdbcPassword));

        //配置c3p0连接池的私有属性
        //连接池最大线程数
        dataSource.setMaxPoolSize(30);
        //连接池最小线程数
        dataSource.setMinPoolSize(10);
        //关闭连接后不自动commit
        dataSource.setAutoCommitOnClose(false);
        //连接超时时间
        dataSource.setCheckoutTimeout(10000);
        //连接失败重试次数
        dataSource.setAcquireRetryAttempts(2);

        return dataSource;
    }
}