package com.utils.redis.conf;

import jdk.nashorn.internal.objects.annotations.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Description:
 * <p>
 * Author: wsy
 * <p>
 * Date: 2019/1/24 16:11
 */
@Configuration
@PropertySource("classpath:conf/redis.properties")
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    /**
     * 节点名称
     */
    private String nodes;

    /**
     * Redis服务名称
     */
    private String masterName;

    /**
     * 密码
     */
    private String password;

    /**
     * 最大连接数
     */
    private int maxTotal;

    /**
     * 最大空闲数
     */
    private int maxIdle;

    /**
     * 最小空闲数
     */
    private int minIdle;

    /**
     * 连接超时时间
     */
    private int timeout;


    /**
     * Description: 连接池配置
     * Author: wsy
     * Date: 2019/1/24 17:25
     * Param: []
     * Return: redis.clients.jedis.JedisPoolConfig
     */
    @Bean(value = "jedisPoolConfig")
    public JedisPoolConfig initJedisPoolConfig() {

        log.info("JedisPool initialize start ...");
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(maxTotal);

        config.setMaxIdle(maxIdle);

        config.setMinIdle(minIdle);
        //常规配置
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        log.info("JedisPool initialize end ...");
        return config;
    }


    /**
     * Description: 产生一个bean交个springIoc管理
     * Author: wsy
     * Date: 2019/1/24 17:21
     * Param: [jedisPoolConfig]
     * Return: redis.clients.jedis.JedisSentinelPool
     */
    @Bean(value = "jedisSentinelPool")
    public JedisSentinelPool initJedisPool(@Qualifier(value = "jedisPoolConfig") JedisPoolConfig jedisPoolConfig) {

        Set<String> nodeSet = new HashSet<>();
        //获取到节点信息
        String nodeString = nodes;
        //判断字符串是否为空
        if (nodeString == null || "".equals(nodeString)) {
            log.error("RedisSentinelConfiguration initialize error nodeString is null");
            throw new RuntimeException("RedisSentinelConfiguration initialize error nodeString is null");
        }
        String[] nodeArray = nodeString.split(",");
        //判断是否为空
        if (nodeArray == null || nodeArray.length == 0) {
            log.error("RedisSentinelConfiguration initialize error nodeArray is null");
            throw new RuntimeException("RedisSentinelConfiguration initialize error nodeArray is null");
        }
        //循环注入至Set中
        for (String node : nodeArray) {
            log.info("Read node : {}。", node);
            nodeSet.add(node);
        }
        //创建连接池对象
        JedisSentinelPool jedisPool = new JedisSentinelPool(masterName, nodeSet, jedisPoolConfig, timeout, password);

        return jedisPool;
    }

    public Logger getLog() {
        return log;
    }

    public void setLog(Logger log) {
        this.log = log;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}



