/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: MvcConfiguration
 * Author: Administrator
 * Date: 2018-10-12 9:05
 * Description: spring-mvc配置迁移
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.config.web;


import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.ServletException;

/**
 * 〈spring-mvc配置迁移〉
 *  开启Mvc自动注入spring容器。WebMvcConfigurerAdapter:配置视图解析器
 *  当一个类实现了此接口（ApplicationContextAware）之后，该类就可以方便获得ApplicationContext中的所有bean
 */
@Configuration

//等价于xml配置文件中的<mvc:annotation-driven />
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware{
    //spring容器
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
    *
    * 静态资源配置
    * @return:
    */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
        //替代docBase配置以实现图片的加载
        registry.addResourceHandler("/upload/**").addResourceLocations("file:/home/o2o/image/upload/");
    }

    /**
    *
    * 定义默认的请求处理器
    * @return:
    */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
    *
    * 创建viewResolver 视图解析器
    * @return:
    */
    @Bean(name = "viewResolver")
    public ViewResolver createViewResoler() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        //设置spring容器
        viewResolver.setApplicationContext(this.applicationContext);
        //取消缓存
        viewResolver.setCache(false);
        //设置解析的前缀
        viewResolver.setPrefix("/WEB-INF/html/");
        //设置视图解析器的后缀
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    /**
    *
    * 文件上传解析器
    * @return:
    */
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver createCommonsMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        //文件大小 1024*1024*20
        multipartResolver.setMaxUploadSize(20971520);
        multipartResolver.setMaxInMemorySize(20971520);
        return multipartResolver;
    }


    @Value("${kaptcha.border}")
    private String border;
    @Value("${kaptcha.textproducer.font.color}")
    private String fColor;
    @Value("${kaptcha.image.width}")
    private String width;
    @Value("${kaptcha.textproducer.char.string}")
    private String cString;
    @Value("${kaptcha.image.height}")
    private String height;
    @Value("${kaptcha.textproducer.font.size}")
    private String fSize;
    @Value("${kaptcha.noise.color}")
    private String nColor;
    @Value("${kaptcha.textproducer.char.length}")
    private String cLength;
    @Value("${kaptcha.textproducer.font.names}")
    private String fNames;

    /**
    *
    * 由于web.xml不生效了，需要在这里配置Kaptcha验证码Servlet
    * @return:
    */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servlet = new ServletRegistrationBean(new KaptchaServlet(), "/Kaptcha");
        servlet.addInitParameter("kaptcha.border", border); //无边框
        servlet.addInitParameter("kaptcha.textproducer.font.color", fColor); //字体颜色
        servlet.addInitParameter("kaptcha.image.width", width); //图片宽度
        servlet.addInitParameter("kaptcha.textproducer.char.string", cString); //使用哪些字生成验证码
        servlet.addInitParameter("kaptcha.image.height", height); //图片高度
        servlet.addInitParameter("kaptcha.textproducer.font.size", fSize); //字体大小
        servlet.addInitParameter("kaptcha.noise.color", nColor); //干扰线颜色
        servlet.addInitParameter("kaptcha.textproducer.char.length", cLength); //字符个数
        servlet.addInitParameter("kaptcha.textproducer.font.names", fNames); //字体
        return servlet;
    }
}