package vip.proyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
* @Description 指定项目为springboot，由此类当作程序入口，自动装配 web 依赖的环境
* @author: pengchen
* @Date: 2019/1/15
*/
@SpringBootApplication
@MapperScan("vip.proyi.mapper")
@EnableCaching
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

}

