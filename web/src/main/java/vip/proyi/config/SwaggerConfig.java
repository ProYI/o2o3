/**
 * Copyright (C), 2018-2019, 兔讯科技有限公司
 * FileName: SwaggerConfig
 * Author: 彭陈
 * Date: 2019/1/15 14:25
 */


package vip.proyi.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**

 * 〈Swagger API〉
 *
 * @author 彭陈
 * @create 2019/1/15
 * @since 1.0.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 可以注入多个doket，也就是多个版本的api
     * groupName不能是重复的
     *
     * @return:
     * @author: pengchen
     * @Date: 2019/1/15
     */
    @Bean
    public Docket api() {
        // 添加多个header或参数
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder
                .parameterType("header")
                .name("Authorization")
                .description("header中Authorization字段用于认证")
                .modelRef(new ModelRef("string"))
                // 非必需，这里是全局配置，然而在登陆的时候是不用验证的
                .required(false).build();

        List<Parameter> aParameters = new ArrayList<Parameter>();
        aParameters.add(aParameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("V1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("vip.proyi.controller"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(apiInfo1())
                .globalOperationParameters(aParameters);
    }

    private ApiInfo apiInfo1() {
        return new ApiInfoBuilder()
                .title("demo Server端 APIs")
                .contact("pengchen")
                .version("v0.01")
                .build();
    }
}