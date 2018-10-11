/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DynamicDataSourceInterceptor
 * Author: Administrator
 * Date: 2018-09-13 11:56
 * Description: mybatis级的拦截器
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dao.split;


import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Locale;
import java.util.Properties;


/**

 * 〈功能简述〉<br>

 * 〈mybatis级的拦截器
 *  使用路由获取不同的数据源后，拦截器根据不同的sql，在不同的数据源中进行crud操作
 * 〉

 *

 * @author Administrator

 * @create 2018-09-13

 * @since 1.0.0

 */
//定义query和update就可以实现数据库curd，插入和删除都会封装在update方法中
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class DynamicDataSourceInterceptor implements Interceptor{
    //日志对象
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceInterceptor.class);
    //匹配是cud哪种sql的正则
    private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //检查是否是事务管理
        boolean synchronizationActive = TransactionSynchronizationManager.isSynchronizationActive();

        //接收对象参数名
        Object[] objects = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) objects[0];
        String lookupKey = DynamicDataSourceHolder.DB_MASTER;

        if (synchronizationActive != true) {
            //读方法
            if (mappedStatement.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                //selectKey为自增Id查询主键（select last_insert_id()）方法，使用主库
                if (mappedStatement.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                    lookupKey = DynamicDataSourceHolder.DB_MASTER;
                } else {
                    //objects[1]就是参数值，即sql
                    BoundSql boundSql = mappedStatement.getSqlSource().getBoundSql(objects[1]);
                    String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]", " ");
                    //如果是REGEX中的某种方法就使用主库
                    if (sql.matches(REGEX)) {
                        lookupKey = DynamicDataSourceHolder.DB_MASTER;
                    } else {
                        //保证写操作用主库，读操作使用从库
                        lookupKey = DynamicDataSourceHolder.DB_SLAVE;
                    }
                }
            }
        } else {
            //非事务使用主库
            lookupKey = DynamicDataSourceHolder.DB_MASTER;
        }
        logger.debug("设置方法[{}] use [{}] Strategy, SqlCommanType [{}]..", mappedStatement.getId(), lookupKey, mappedStatement.getSqlCommandType().name());
        DynamicDataSourceHolder.setDbType(lookupKey);
        return invocation.proceed();
    }

    /*
    如果target对象是Executor类型的对象，就包装到wrap进行拦截,否则不进行拦截直接返回
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}