/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DynamicDataSourceHolder
 * Author: Administrator
 * Date: 2018-09-13 0:08
 * Description:
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dao.split;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**

 * 〈功能简述〉<br>

 * 〈〉

 *

 * @author Administrator

 * @create 2018-09-13

 * @since 1.0.0

 */

public class DynamicDataSourceHolder {

    //日志对象
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceHolder.class);
    //线程安全
    private static ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    //key
    public static final String DB_MASTER = "master";
    public static final String DB_SLAVE = "slave";

    //获取线程的DbType
    public static String getDbType() {
        String db = contextHolder.get();
        if (db == null) {
            db = DB_MASTER;
        }
        return db;
    }

    //设置线程的DbType
    public static void setDbType(String str) {
        logger.debug("所使用的数据源为：" + str);
        contextHolder.set(str);
    }

    //清理连接类型
    public static void clearDbType() {
        contextHolder.remove();
    }
}