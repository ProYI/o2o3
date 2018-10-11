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


/**

 * 〈功能简述〉<br>

 * 〈图片路径工具类〉

 *

 * @author Administrator

 * @create 2018-09-10

 * @since 1.0.0

 */

public class PathUtil {
    //获取系统的文件分隔符"/"
    private static String separator = System.getProperty("file.separator");

    //图片存储位置的绝对路径
    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "D:/o2o/image";
        } else {
            basePath = "/home/o2o/image";
        }
        //替换不同系统的分隔符
        basePath = basePath.replace("/", separator);
        return basePath;
    }

    //图片子路径
    public static String getShopImagePath(long shopId) {
        String imagePath = "/upload/images/item/shop/" + shopId + "/";
        return imagePath.replace("/", separator);
    }
}