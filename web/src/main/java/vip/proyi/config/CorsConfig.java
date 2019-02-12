/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: CorsConfig
 * Author: ProYI
 * Date: 2019-01-16 22:05
 * Description: 解决跨域问题
 */


package vip.proyi.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 〈处理跨域问题〉
 * @author ProYI
 * @create 2019-01-16
 */
@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 1允许服务端访问
        corsConfiguration.addAllowedOrigin("*");
        // 1.1允许本地访问
        corsConfiguration.addAllowedOrigin("http://localhost");
        // 2允许任何头
        corsConfiguration.addAllowedHeader("*");
        // 3允许任何方法（post、get等）
        corsConfiguration.addAllowedMethod("*");
        // 4 允许withCredentials报文头
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}