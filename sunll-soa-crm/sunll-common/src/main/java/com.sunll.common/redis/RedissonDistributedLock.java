package com.sunll.common.redis;


import org.redisson.api.RedissonClient;

/**
 * Description: redisson分布式锁, 支持全局与局部锁
 */
public class RedissonDistributedLock {
	
	private RedissonConfig redissonConfig;
	private RedissonClient redisson;
	
	public RedissonDistributedLock setRedissonConfig(RedissonConfig config){
		this.redissonConfig = config;
		this.redisson = redissonConfig.getRedissonInstance();
		return this;
	}
	
	public RedissonClient getRedisson() {
		return redisson;
	}
	
}
