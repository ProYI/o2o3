/**
 * Copyright (C), 2018-2019, 兔讯科技有限公司
 * FileName: SpringContextBeanService
 * Author: 彭陈
 * Date: 2019/1/16 16:45
 */


package vip.proyi.service;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**

 * 〈获取spring上下文〉
 *  Bean需要实现某个功能，但该功能必须借助于Spring容器才能实现
 *  此时就必须让该Bean先获取Spring容器，然后借助于Spring容器实现该功能。
 *  为了让Bean获取它所在的Spring容器，可以让该Bean实现ApplicationContextAware接口
 *
 * @author 彭陈
 * @create 2019/1/16
 * @since 1.0.0
 */

public class SpringContextBeanService implements ApplicationContextAware {
    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(String name)
    {
        return (T)context.getBean(name);
    }

    public static <T> T getBean(Class<T> beanClass){
        return context.getBean(beanClass);
    }
}