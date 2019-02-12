package vip.proyi.annotation;

import java.lang.annotation.*;

/**
* @Description 在Controller方法上加入该注解不会验证身份
* @author: pengchen
* @Date: 2019/1/16
*/
@Target( { ElementType.METHOD } )
@Retention( RetentionPolicy.RUNTIME )
@Documented
public @interface Pass {
}
