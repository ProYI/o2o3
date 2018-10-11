/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: JedisPoolWriper
 * Author: Administrator
 * Date: 2018-09-27 13:54
 * Description: 指定redis的JedisPool接口构造函数，这样才能在centos成功创建jedispool
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.cache;


import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 〈指定redis的JedisPool接口构造函数，这样才能在centos成功创建jedispool〉
 */

public class JedisPoolWriper {
    /*Redis连接对象*/
    private JedisPool jedisPool;

    public JedisPoolWriper(final JedisPoolConfig poolConfig, final String host, final int port) {
        try {
            jedisPool = new JedisPool(poolConfig, host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //public JedisPoolWriper(final JedisPoolConfig poolConfig, final String host,final int port,final int timeout,final String auth) {
    //    try {
    //        jedisPool = new JedisPool(poolConfig, host, port,timeout,auth);
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //}

    /**
     * 获取Redis连接池对象
     * @return
     */
    public JedisPool getJedisPool() {
        return jedisPool;
    }

    /**
     * 注入Redis连接池对象
     * @param jedisPool
     */
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}