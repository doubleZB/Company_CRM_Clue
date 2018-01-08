package com.sunll.common.redis;


import org.redisson.api.*;

import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * Description: Redisson 模板类 
 * All Rights Reserved.
 * @param <V>
 */
public class RedissonTemplete<V>{
	
	private RedissonConfig redissonConfig;
	private RedissonClient redisson;
	
	public RedissonTemplete<V> setRedissonConfig(RedissonConfig config){
		this.redissonConfig = config;
		this.redisson = redissonConfig.getRedissonInstance();
		return this;
	}
	
	/**
	 * Description: String
	 * @param objectName
	 * @return
	 */
	private RBucket<V> getRBucket(String objectName) {
		return redisson.getBucket(objectName);
	}
	
	/**
	 * Description: Hash
	 * @param objectName
	 * @return
	 */
	@SuppressWarnings("hiding")
	private <K, V> RMap<K, V> getRMap(String objectName) {
		return redisson.getMap(objectName);
	}
	
	/**
	 * Description: SortSet
	 * @param objectName
	 * @return
	 */
	private RScoredSortedSet<V> getRScoredSortedSet(String objectName){
		return redisson.getScoredSortedSet(objectName);
	}
	
	/**
	 * Description: List
	 * @param objectName
	 * @return
	 */
	private RList<V> getRList(String objectName) {
		return redisson.getList(objectName);
	}
	
	/**
	 * Description: pipelining
	 * @return
	 */
	private RBatch getRBatch(){
		return redisson.createBatch();
	}
	
	/**
	 * Description: RBuckets
	 * @return
	 */
	private RBuckets getRBuckets(){
		return redisson.getBuckets();
	}
	
	/**
	 * Description: RAtomicLong
	 * @return
	 */
	private RAtomicLong getRAtomicLong(String objectName){
		return redisson.getAtomicLong(objectName);
	}
	
	/**
	 * Description: RScript
	 * @return
	 */
	private RScript getRScript(){
		return redisson.getScript();
	}
	
	//-------------------------String Start---------------------------//
	public void set(String key, V value){
		RBucket<V> bucket = this.getRBucket(key);
		bucket.set(value);
	}

	public void set(String key, V value, long timeToLive, TimeUnit timeUnit){
		RBucket<V> bucket = this.getRBucket(key);
		bucket.set(value, timeToLive, timeUnit);
	}
	
	public V get(String key){
		RBucket<V> bucket = this.getRBucket(key);
		return bucket.get();
	}
	
	public boolean del(String key){
		RBucket<V> bucket = this.getRBucket(key);
		return bucket.delete();
	}
	
	public boolean expire(String key, long timeToLive, TimeUnit timeUnit){
		RBucket<V> bucket = this.getRBucket(key);
		return bucket.expire(timeToLive, timeUnit);
	}
	
	public boolean isExists(String key){
		RBucket<V> bucket = this.getRBucket(key);
		return bucket.isExists();
	}
	
	public long ttl(String key){
		RBucket<V> bucket = this.getRBucket(key);
		return bucket.remainTimeToLive();
	}
	
	public boolean setnx(String key, V value){
		RBucket<V> bucket = this.getRBucket(key);
		return bucket.trySet(value);
	}
	
	public boolean setnx(String key, V value, long timeToLive, TimeUnit timeUnit){
		RBucket<V> bucket = this.getRBucket(key);
		return bucket.trySet(value, timeToLive, timeUnit);
	}
	
	public void mset(Map<String, V> maps){
		if(null != maps && !maps.isEmpty()){
			RBatch batch = this.getRBatch();
			for(Map.Entry<String, V> entry:maps.entrySet()){
				batch.getBucket(entry.getKey()).setAsync(entry.getValue());
			}
			batch.execute();
		}
	}
	
	public void mset(Map<String, V> maps, long timeToLive, TimeUnit timeUnit){
		if(null != maps && !maps.isEmpty()){
			RBatch batch = this.getRBatch();
			for(Map.Entry<String, V> entry:maps.entrySet()){
				batch.getBucket(entry.getKey()).setAsync(entry.getValue(), timeToLive, timeUnit);
			}
			batch.execute();
		}
	}
	
	public Map<String, V> mget(Collection<String> keys){
		if(null != keys && !keys.isEmpty()){
			RBuckets buckets = getRBuckets();
			return buckets.get(keys.toArray(new String[]{}));
		}
		return null;
	}
	
	public long incr(String key){
		RAtomicLong rAtomicLong = this.getRAtomicLong(key);
		return rAtomicLong.incrementAndGet();
	}
	
	public long incrby(String key, long delta){
		RAtomicLong rAtomicLong = this.getRAtomicLong(key);
		rAtomicLong.getAndAdd(delta);
		return rAtomicLong.get();
	}
	
	public long getIncrbyValue(String key){
		RAtomicLong rAtomicLong = this.getRAtomicLong(key);
		return rAtomicLong.get();
	}
	//-------------------------String End---------------------------//
	
	
	//-------------------------Hash Start---------------------------//
	@SuppressWarnings("hiding")
	public <K, V> void hset(String key, K field, V value){
		RMap<K, V> rmap = this.getRMap(key);
		rmap.put(field, value);
	}

	@SuppressWarnings("hiding")
	public <K, V> void hmset(String key, Map<? extends K, ? extends V> map){
		RMap<K, V> rmap = this.getRMap(key);
		rmap.putAll(map);
	}

	@SuppressWarnings("hiding")
	public <K, V> V hget(String key, K field){
		RMap<K, V> rmap = this.getRMap(key);
		return rmap.get(field);
	}
	
	@SuppressWarnings({ "hiding", "unchecked"})
	public <K, V> void hdel(String key, K... field){
		RMap<K, V> rmap = this.getRMap(key);
		rmap.fastRemove(field);
	}
	
	@SuppressWarnings("hiding")
	public <K, V> Integer hincrBy(String key, K field, Number delta){
		RMap<K, V> rmap = this.getRMap(key);
		return (Integer) rmap.addAndGet(field, delta);
	}
	
	@SuppressWarnings("hiding")
	public <K, V> Map<K, V> hmget(String key, Set<K> keys){
		RMap<K, V> rmap = this.getRMap(key);
		return rmap.getAll(keys);
	}
	
	@SuppressWarnings({ "hiding"})
	public <K, V> Map<K, V> hmgetPipeline(String key, Set<K> fields){
		if(null != fields && fields.size() > 0){
			Map<K, V> map = new HashMap<K, V>();
			for(K item:fields){
				RMap<K, V> rmap = this.getRMap(key);
				V v = rmap.get(item);
				map.put(item, v);
			}
		}
		return null;
	}
	
	//-------------------------Hash End---------------------------//
	
	//-------------------------List Start-------------------------//
	public V lindex(String key, int index){
		RList<V> rList =  this.getRList(key);
		return rList.get(index);
	}
	
	public boolean rpush(String key, V value){
		RList<V> rList =  this.getRList(key);
		return rList.add(value);
	}
	
	public int llen(String key){
		RList<V> rList =  this.getRList(key);
		return rList.size();
	}
	
	public void lrem(String key, int index){
		RList<V> rList =  this.getRList(key);
		rList.fastRemove(index);
	}
	
	public List<V> lrange(String key, int fromIndex, int toIndex){
		RList<V> rList =  this.getRList(key);
		return rList.subList(fromIndex, toIndex);
	}

	public List<V> lrangeAll(String key){
		RList<V> rList =  this.getRList(key);
		return rList.readAll();
	}
	//-------------------------List End---------------------------//
	
	//-------------------------SortSet Start-------------------------//
	public boolean zadd(String key, double score, V value){
		RScoredSortedSet<V> sortSet = this.getRScoredSortedSet(key);
		return sortSet.add(score, value);
	}
	
	public Long zaddAll(String key, Map<V, Double> values){
		RScoredSortedSet<V> sortSet = this.getRScoredSortedSet(key);
		return sortSet.addAll(values);
	}
	
	public Double zincrby(String key, Number scoreStep, V value){
		RScoredSortedSet<V> sortSet = this.getRScoredSortedSet(key);
		return sortSet.addScore(value, scoreStep);
	}
	
	public int zcount(String key){
		RScoredSortedSet<V> sortSet = this.getRScoredSortedSet(key);
		return sortSet.size();
	}
	
	public Double zscore(String key, V value){
		RScoredSortedSet<V> sortSet = this.getRScoredSortedSet(key);
		return sortSet.getScore(value);
	}
	
	public Collection<V> zrange(String key,int startIndex,int endIndex){
		RScoredSortedSet<V> sortSet = this.getRScoredSortedSet(key);
		return sortSet.valueRange(startIndex, endIndex);
	}
	
	public boolean zrem(String key,Object obj){
		RScoredSortedSet<V> sortSet = this.getRScoredSortedSet(key);
		return sortSet.remove(obj);
	}
	//-------------------------SortSet End-------------------------//
	
	//-------------------------Scripting Start-----------------------//
	public <R> R eval(RScript.Mode mode, String luaScript, RScript.ReturnType returnType){
		RScript rScript = this.getRScript();
		return rScript.eval(mode, luaScript, returnType);
	}
	
	public <R> R eval(RScript.Mode mode, String luaScript, RScript.ReturnType returnType, List<Object> keys, Object... values){
		RScript rScript = this.getRScript();
		return rScript.eval(mode, luaScript, returnType, keys, values);
	}
	//-------------------------Scripting End-------------------------//
}
