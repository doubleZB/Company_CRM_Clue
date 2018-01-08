package com.sunll.common.redis;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.Map;


/**
 * @autor jipengkun
 * Redis模板工具类
 */
@Service
public class RedisTemplate {

    private static final Logger log = LoggerFactory.getLogger(RedisTemplate.class);

    @Autowired
    private ShardedJedisPool shardedJedisPool1;

    public ShardedJedis getRedisClient() {
        try {
            ShardedJedis shardJedis = shardedJedisPool1.getResource();
            return shardJedis;
        } catch (Exception e) {
            log.error("getRedisClent error", e);
        }
        return null;
    }

    public void returnResource(ShardedJedis shardedJedis, boolean broken) {
        if (broken) {
            shardedJedis.close();
        } else {
            shardedJedis.close();
        }
    }

    /**
     * 设置单个值
     *
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        String result = null;

        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 设置单个值
     *
     * @param key
     * @param value
     * @return
     */
    public long setAdnExpire(String key, String value, int second) {
        Long result = null;

        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            shardedJedis.set(key, value);
            result = shardedJedis.expire(key,second);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 获取单个值
     *
     * @param key
     * @return
     */
    public String get(String key) {
        String result = null;
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.get(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 自增长
     *
     * @param  key
     * @return
     */
    public long incr(String key){
        Long result = null;
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.incr(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 设置key有效期
     *
     * @param key
     * @param second
     * @return
     */
    public long expire(String key, int second){
        Long result = null;
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.expire(key,second);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 查看key有效期
     *
     * @param key
     * @return
     */
    public long ttl(String key){
        Long result = null;
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.ttl(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 删除 单个值
     *
     * @param key
     * @return
     */
    public long del(String key){
        Long result = null;
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.del(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 设置单个值
     *
     * @param hkey
     * @param key
     * @param value
     * @return
     */
    public long hset(String hkey,String key, String value) {
        Long result = null;

        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.hset(hkey, key, value);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 获取单个值
     *
     * @param hkey
     * @param key
     * @return
     */
    public String hget(String hkey, String key) {
        String result = null;
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.hget(hkey, key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 设置Map<String, String>对象
     *
     * @param hkey
     * @param value
     * @return
     */
    public String hmset(String hkey,Map<String, String> value) {
        String result = null;

        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.hmset(hkey, value);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 获取Map<String, String>对象
     *
     * @param hkey
     * @return
     */
    public Map<String, String> hgetAll(String hkey) {
        Map<String, String> result = null;
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.hgetAll(hkey);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 删除 单个值
     *
     * @param hkey
     * @param key
     * @return
     */
    public long hdel(String hkey, String key){
        Long result = null;
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.hdel(hkey, key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

}
