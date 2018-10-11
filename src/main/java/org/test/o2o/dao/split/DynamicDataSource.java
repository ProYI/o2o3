/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DynamicDataSource
 * Author: Administrator
 * Date: 2018-09-13 0:03
 * Description: 实现mysql读写主从分离
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dao.split;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**

 * 〈功能简述〉<br>

 * 〈实现mysql读写主从分离〉

 *

 * @author Administrator

 * @create 2018-09-13

 * @since 1.0.0

 */
//继承AbstractRoutingDataSource后可以从不同的数据源获取数据，实现主从分离
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        //返回不同的key,调用不同的数据源
        return DynamicDataSourceHolder.getDbType();
    }
}