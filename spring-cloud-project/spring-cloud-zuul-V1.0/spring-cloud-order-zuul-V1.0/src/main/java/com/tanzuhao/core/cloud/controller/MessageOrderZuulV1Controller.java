
package com.tanzuhao.core.cloud.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanzuhao.core.base.Result;
import com.tanzuhao.core.constants.BaseEnums;
import com.tanzuhao.core.system.dto.User;
import com.tanzuhao.core.util.Results;

/**
 * 对外提供数据接口
 * 
 * @author tanzuhao
 *
 */
@RequestMapping("/cloud")
@RestController
public class MessageOrderZuulV1Controller{
	private static final Logger log = LoggerFactory.getLogger(MessageOrderZuulV1Controller.class);
	/**
	 * 生产者提供接口服务demo
	 * 
	 * @return
	 */
	@RequestMapping(value = "/msg", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Result msgFeignCloud(String id,String name) {
		log.info(">>>>>>>>>>>>>>>生产者很高兴为您提供接口服务,祝你生活愉快，工作顺利!");
		log.info(">>>>>>>>>>>>>>>order 喜获 consumer 传来参数:id="+id+",name="+name);
		String msg = "spring cloud  message from order,id:"+id+",name:"+name;
		return Results.successWithData(msg, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}
	/**
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/msg2", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Result msgFeignCloud(@RequestParam Map<String, Object> map) {
		log.info(">>>>>>>>>>>>>>>生产者很高兴为您提供接口服务,祝你生活愉快，工作顺利!");		
		log.info(">>>>>>>>>>>>>>>order 喜获 consumer 传来map参数:id="+map.get("id")+",name="+map.get("name"));
		String msg = "spring cloud  message from order,id:"+map.get("id")+",name:"+map.get("name");
		return Results.successWithData(msg, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}
	/**
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/msg3", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Result msgFeignCloud(@RequestBody User user) {
		log.info(">>>>>>>>>>>>>>>生产者很高兴为您提供接口服务,祝你生活愉快，工作顺利!");
		log.info(">>>>>>>>>>>>>>>order 喜获 consumer 传来参数:user.id="+user.getUserId()+",user.name="+user.getUsername());
		String msg = "spring cloud  message from order,userId="+user.getUserId()+",username="+user.getUsername();
		return Results.successWithData(msg, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}
     /**
      * 登录过滤器数据提供服务
      * @param token
      * @return
      */
	@RequestMapping(value = "/findZuulMsg", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Result findZuulMsg(String token) {
		log.info(">>>>>>>>>>>>>>>生产者很高兴为您提供接口服务,祝你生活愉快，工作顺利!");
		log.info(">>>>>>>>>>>>>>>order 喜获 consumer 传来参数:token="+token);
		String msg = "spring cloud  message from order,token:"+token;
		return Results.successWithData(msg, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}
	/**
	 *  限流过滤器数据提供服务
	 * @return
	 */
	@RequestMapping(value = "/getZuulMsg", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Result getZuulMsg() {
		String msg = "spring cloud  message from order,get zuul msg";
		return Results.successWithData(msg, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}
}