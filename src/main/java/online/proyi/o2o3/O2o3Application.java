package online.proyi.o2o3;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("online.proyi.o2o3.dao.mapper")
public class O2o3Application {

	public static void main(String[] args) {
		SpringApplication.run(O2o3Application.class, args);
	}


	@Bean
	public OpenAPI o2oOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("o2o 3.0 API")
						.description("商城3.0")
						.version("v0.0.1"));
	}
}
