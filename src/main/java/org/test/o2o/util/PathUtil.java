/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: PathUtil
 * Author: Administrator
 * Date: 2018-09-10 11:35
 * Description: 图片路径工具类
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**

 * 〈功能简述〉<br>

 * 〈图片路径工具类〉

 *

 * @author Administrator

 * @create 2018-09-10

 * @since 1.0.0

 */
@Configuration
public class PathUtil {
    //获取系统的文件分隔符"/"
    private static String separator = System.getProperty("file.separator");

    private static String winPath;
    private static String linuxPath;
    private static String shopPath;

    @Value("${win.base.path}")
    public void setWinPath(String winPath) {
        PathUtil.winPath = winPath;
    }

    @Value("${linux.base.path}")
    public  void setLinuxPath(String linuxPath) {
        PathUtil.linuxPath = linuxPath;
    }

    @Value("${shop.relevant.path}")
    public void setShopPath(String shopPath) {
        PathUtil.shopPath = shopPath;
    }

    //图片存储位置的绝对路径
    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = winPath;
        } else {
            basePath = linuxPath;
        }
        //替换不同系统的分隔符
        basePath = basePath.replace("/", separator);
        return basePath;
    }

    //图片子路径
    public static String getShopImagePath(long shopId) {
        String imagePath = shopPath + shopId + separator;
        return imagePath.replace("/", separator);
    }
}