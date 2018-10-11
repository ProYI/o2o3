/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Hello
 * Author: Administrator
 * Date: 2018-10-11 8:35
 * Description: springboot测试
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**

 * 〈功能简述〉<br>

 * 〈springboot测试〉

 *

 * @author Administrator

 * @create 2018-10-11

 * @since 1.0.0

 */
@RestController
public class Hello {
    @GetMapping("/hello")
    public String hello() {
        return "Hello SpringBoot";
    }
}