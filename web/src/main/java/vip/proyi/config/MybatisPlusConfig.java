/**
 * Copyright (C), 2018-2019, 兔讯科技有限公司
 * FileName: MybatisPlusConfig
 * Author: 彭陈
 * Date: 2019/1/15 15:20
 */


package vip.proyi.config;


import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**

 * 〈MybatisPlus分页配置〉
 *
 * @author 彭陈
 * @create 2019/1/15
 * @since 1.0.0
 */
@EnableTransactionManagement
@Configuration
@MapperScan("vip.proyi.mapper.*.mapper*")
public class MybatisPlusConfig {
    /**
     *   mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }
}