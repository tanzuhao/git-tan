package com.tanzuhao.core.cache.redis;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
/**
 * 
 * @author tanzuhao
 * @date: 2019年9月10日 下午2:12:33  
 * @param <HK>
 * @param <T>
 */
@Service
public class RedisHelperImpl<HK, T> implements RedisHelper<HK, T> {
    // 在构造器中获取redisTemplate实例, key(not hashKey) 默认使用String类型
    private RedisTemplate<String, T> redisTemplate;
    // 在构造器中通过redisTemplate的工厂方法实例化操作对象
    private HashOperations<String, HK, T> hashOperations;
    private ListOperations<String, T> listOperations;
    private ValueOperations<String, T> valueOperations;

    // IDEA虽然报错,但是依然可以注入成功, 实例化操作对象后就可以直接调用方法操作Redis数据库
    @Autowired
    public RedisHelperImpl(RedisTemplate<String, T> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
        this.listOperations = redisTemplate.opsForList();
        this.valueOperations = redisTemplate.opsForValue();
    }
    /**
     * 添加对象
     * @param key 关键字
     * @param domain 对象
     */
    @Override
    public void set(String key, T domain) {
        valueOperations.set(key, domain);
    }
	/**
	 * 添加对象
	 * @param key key
	 * @param domain
	 * @param timeout 时间 
	 * @param timeUnit 时间单位（小时:TimeUnit.HOURS，分钟:TimeUnit.MINUTES，秒:TimeUnit.SECONDS）
	 */
	public void set(String key, T domain, long timeout, TimeUnit unit){
		valueOperations.set(key, domain, timeout,unit);
	};	
    /**
     * 根据关键字获取值
     * @param key 
     * @return
     */
    @Override
    public T get(String key) {
        return valueOperations.get(key);
    }
    /**
     * 根据key删除
     * @param key 
     */
    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }
    /**
     * 给指定的key设定生存时间
     * @param key 需要设置的key
     * @param timeout  key的生存时间
     * @param timeUnit  timeuint：时间单位（小时:TimeUnit.HOURS，分钟:TimeUnit.MINUTES，秒:TimeUnit.SECONDS）
     * @return
     */
    public boolean expirse(String key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }
    /**
	 * Hash结构 添加元素 
	 * @param key key 
	 * @param hashKey hashKey 
	 * @param domain 元素
	 */
    @Override
    public void hashPut(String key, HK hashKey, T domain) {
        hashOperations.put(key, hashKey, domain);
    }
    /**
	 * Hash结构 获取指定key所有键值对 
	 * @param key 
	 * @return
	 */
    @Override
    public Map<HK, T> hashFindAll(String key) {
        return hashOperations.entries(key);
    }
    /**
	 * Hash结构 获取单个元素 
	 * @param key 
	 * @param hashKey 
	 * @return
	 */
    @Override
    public T hashGet(String key, HK hashKey) {
        return hashOperations.get(key, hashKey);
    }

    @Override
    public void hashRemove(String key, HK hashKey) {
        hashOperations.delete(key, hashKey);
    }
    /**
	 * List结构 向尾部(Right)添加元素
	 * 
	 * @param key
	 * @param domain
	 * @return
	 */
    @Override
    public Long listPush(String key, T domain) {
        return listOperations.rightPush(key, domain);
    }
    /**
	 * List结构 向头部(Left)添加元素 
	 *  @param key 
	 *  @param domain 
	 *  @return
	 */
    @Override
    public Long listUnshift(String key, T domain) {
        return listOperations.leftPush(key, domain);
    }
    /**
	 * List结构 获取所有元素
	 * 
	 * @param key
	 * @return
	 */
    @Override
    public List<T> listFindAll(String key) {
        if (!redisTemplate.hasKey(key)) {
            return null;
        }
        return listOperations.range(key, 0, listOperations.size(key));
    }
    /**
	 * List结构移除并获取数组第一个元素
	 * 
	 * @param key
	 * @return
	 */
    @Override
    public T listLPop(String key) {
        return listOperations.leftPop(key);
    }
 }
