package com.utils.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import static com.beans.GlobalConstants.*;

/**
 * Description:
 * <p>
 * Author: wsy
 * <p>
 * Date: 2019/1/24 16:18
 */
@Service
public class RedisService {

    //@Autowired 没有集群不能初始化
    private JedisSentinelPool jedisSentinelPool;

    /**
     * 简单介绍redis [详情见redis.md]
     * 一类型
     *  string       计数,参数
     *  hash(散列)    结构对象(单点登录user对象)
     *  list(队列)    可重复,消息队列
     *  set (集合)
     *  sortset(有序集合)
     *
     * 二特点
     *  单线程
     *  主从集群,哨兵集群
     *
     * 三项目应用
     *   红包计数
     *   脚本缓存
     *   结果落地
     *
     * 四难点
     *  双写
     *  雪崩
     *  穿透
     *  并发竞争(分布式锁)
     *
     * */


    private <T> T execute(Function<T, Jedis> fun) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            return fun.callback(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return null;
    }


    public void set(String key, String valueStr, Long timeout, String storeType) {
        if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(valueStr)) {
            switch (storeType) {
                case redis_hash_type:
                    setHash(key, valueStr, timeout);
                    break;
                case redis_set_type:
                    setSet(key, valueStr, timeout);
                    break;
                case redis_sortset_type:
                    setSortset(key, valueStr, timeout);
                    break;
                case redis_string_type:
                    setString(key, valueStr, timeout);
                    break;
                default:
                    return;
            }
        }
    }

    private void setString(String key, String valueStr, Long timeout) {
        this.execute(new Function<String, Jedis>() {
            @Override
            public String callback(Jedis jedis) {
                jedis.set(key, valueStr);
                if (0 != timeout) {
                    jedis.pexpire(key, timeout);
                }
                return "";

            }
        });

        Thread t = new Thread();
        t.start();

    }


    private void setSortset(String key, String valueStr, Long timeout) {
        this.execute(new Function<Object, Jedis>() {
            @Override
            public Object callback(Jedis jedis) {
                jedis.sadd(key, valueStr);
                return null;
            }
        });

    }

    private void setSet(String key, String valueStr, Long timeout) {
        this.execute(new Function<Object, Jedis>() {
            @Override
            public Object callback(Jedis jedis) {
                jedis.sadd(key, valueStr);
                return null;
            }
        });
    }

    private void setHash(String key, String valueStr, Long timeout) {
        this.execute(new Function<Object, Jedis>() {
            @Override
            public Object callback(Jedis jedis) {
                jedis.hset(key, valueStr, valueStr);
                if (0 != timeout) {
                    jedis.pexpire(key, timeout);
                }
                return null;
            }
        });
    }


}
