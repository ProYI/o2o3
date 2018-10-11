/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: SessionFactoryConfiguration
 * Author: Administrator
 * Date: 2018-10-11 10:22
 * Description: 操作持久层的sessionFactoryBean
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.config.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;


/**

 * 〈功能简述〉<br>

 * 〈操作持久层的sessionFactoryBean〉

 *

 * @author Administrator

 * @create 2018-10-11

 * @since 1.0.0

 */
@Configuration
public class SessionFactoryConfiguration {
    //mybatis-config.xml配置文件的路径
    private static String mybatisConfigFile;
    //mybatis mapper文件所在路径
    private static String mapperPath;

    //因为mybatisConfigFile等静态变量不能直接注入
    @Value("${mybatis_config_file}")
    public void setMybatisConfigFile(String mybatisConfigFile) {
        SessionFactoryConfiguration.mybatisConfigFile = mybatisConfigFile;
    }

    @Value("${mapper_path}")
    public void setMapperPath(String mapperPath) {
        SessionFactoryConfiguration.mapperPath = mapperPath;
    }

    //实体类所在的package

    @Value("${type_alias_package}")
    private String typeAliasPackage;

    @Autowired
    private ComboPooledDataSource dataSource;

    /**
    *
    * 创建sqlSessionFactoryBean实例，并设置configurtion设置mapper映射路径
     * 设置datasource数据源
    * @return:
    */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置mybatis configuration扫描路径
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFile));

        //添加mapper扫描路径
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        //CLASSPATH_ALL_URL_PREFIX = ClassPath:
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));

        //设置dataSource
        sqlSessionFactoryBean.setDataSource(dataSource);

        //设置typeAlica包扫描
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasPackage);

        return sqlSessionFactoryBean;
    }
}