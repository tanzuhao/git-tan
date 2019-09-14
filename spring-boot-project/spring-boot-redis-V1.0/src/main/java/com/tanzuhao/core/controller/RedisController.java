package com.tanzuhao.core.controller;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanzuhao.core.base.Result;
import com.tanzuhao.core.cache.redis.RedisHelper;
import com.tanzuhao.core.constants.BaseEnums;
import com.tanzuhao.core.system.dto.User;
import com.tanzuhao.core.util.Results;

/**
 * Redis用户信息管理
 * 添加缓存对象： http://localhost:8086/redis/add
 * 根据key查询缓存信息: http://localhost:8086/redis/get?key=0
 * 删除缓存信息: http://localhost:8086/redis/delete?key=0
 * 设置过期时间:http://localhost:8086/redis/expirse?key=0
 * 更新缓存信息:http://localhost:8086/redis/update?key=0&username=赵哈哈
 * @author tanzuhao
 *
 */
@RequestMapping("/redis")
@RestController
public class RedisController{
	public static final Logger log = LoggerFactory.getLogger(RedisController.class);
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisHelper redisHelper;

	/**
	 * 缓存用户信息到redis
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/add")
	public Result add() {
		User user = new User();
		Random r=new Random();
		long userId=r.nextInt(100);
		user.setUserId(userId);
		user.setUsername("谭好好" + String.valueOf(userId));
		user.setNickname("哈哈哈" + String.valueOf(userId));
		user.setPassword("123456789");		
		//redisHelper.set(String.valueOf(userId), user,10,TimeUnit.SECONDS);//10秒后过期
		redisHelper.set(String.valueOf(userId), user);
		User userRedis = (User) redisHelper.get(String.valueOf(userId));
		log.info(">>>>>>>>>>>redis user username:" + userRedis.getUsername());
		return Results.successWithData(userRedis, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}
	/**
	 * 根据key查询缓存用户信息
	 * @param key
	 * @return
	 */
	@RequestMapping("/get")
	public Result get(@RequestParam("key") String key) {
		User userRedis = (User) redisHelper.get(String.valueOf(key));
		if(userRedis!=null){
			log.info(">>>>>>>>>>>redis user username:" + userRedis.getUsername());
		}
		return Results.successWithData(userRedis, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}
	/**
	 * 根据key将缓存信息删除
	 * @param key
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(@RequestParam("key") String key) {
		redisHelper.delete(key);
		return Results.successWithData(BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}
	/**
	 * 设置缓存信息过期时间
	 * @param key
	 * @return
	 */
	@RequestMapping("/expirse")
	public Result expirse(@RequestParam("key") String key){
		long timeout=10l;
		TimeUnit timeUnit=TimeUnit.SECONDS;
		redisHelper.expirse(key, timeout, timeUnit);
		return Results.successWithData(BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}
	/**
	 * 更新缓存信息
	 * @param key
	 * @param username
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/update")
	public Result update(@RequestParam("key")String key,@RequestParam("username")String username){
		User userRedis = (User) redisHelper.get(key);
		if(userRedis!=null){
			userRedis.setUsername(username);
			redisHelper.set(key, userRedis);
			userRedis= (User) redisHelper.get(key);
			log.info(">>>>>>>>>>>udpate later username:"+userRedis.getUsername());
		}
		return Results.successWithData(userRedis,BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}
}
