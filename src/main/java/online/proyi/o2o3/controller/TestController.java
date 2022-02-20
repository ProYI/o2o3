package online.proyi.o2o3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Tag(name = "测试接口")
public class TestController {

    @GetMapping("/testMethod1")
    @Operation(summary = "测试接口1", description = "接口使用graalvm打包Spring native")
    public String testMethod1() {
        return "Hello, Spring native with graalvm";
    }
}
