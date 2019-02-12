/**
 * Copyright (C), 2018-2019, 兔讯科技有限公司
 * FileName: WebMvcConfigurer
 * Author: 彭陈
 * Date: 2019/1/15 15:25
 */


package vip.proyi.shiro;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**

 * 〈spring-mvc配置〉
 * 开启Mvc自动注入spring容器
 * WebMvcConfigurerAdapter:配置视图解析器
 *
 *
 * @author 彭陈
 * @create 2019/1/15
 * @since 1.0.0
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }

    /*
        注册自定义参数解析器
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

    /*
        自定义参数解析器
     */
    @Bean
    public CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver() {
        return new CurrentUserMethodArgumentResolver();
    }
}