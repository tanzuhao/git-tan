package com.tanzuhao.core.cloud.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tanzuhao.core.base.Result;
import com.tanzuhao.core.cloud.service.FeignService;
import com.tanzuhao.core.constants.BaseEnums;
import com.tanzuhao.core.system.dto.User;
import com.tanzuhao.core.util.Results;
/**
 * feign调用，有回调处理
 * http://localhost:8888/cloud/feign/hello
 * @author tanzuhao
 *
 */
@RequestMapping("/cloud/feign")
@RestController
public class FeignController{
	private static final Logger log = LoggerFactory.getLogger(FeignController.class);
	@Autowired
	private FeignService feignService;

	/**
	 * 使用@RequestParam注解指定请求的参数是什么
	 * 
	 * @return
	 */
	@RequestMapping(value = "/hello", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Result getMsgFeignCloud() throws ExecutionException, InterruptedException{
		String id = "100";
		String name = "tanzuhao";
		Result msgResult = feignService.getMsgFeignCloud(id, name);
		if (msgResult == null) {
			return Results.successWithData(BaseEnums.FAILURE.code(), BaseEnums.FAILURE.desc());
		}
		log.info(">>>>>>>>调用服务获得数据msg:" + msgResult.getData());
		return Results.successWithData(msgResult.getData(), BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/hello2", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Result getMsgFeignCloud2() throws ExecutionException, InterruptedException{
		String id = "200";
		String name = "tanzuhao-map";
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("id", id);
		ret.put("name", name);
		Result msgResult = feignService.getMsgFeignCloud(ret);
		if (msgResult == null) {
			return Results.successWithData(BaseEnums.FAILURE.code(), BaseEnums.FAILURE.desc());
		}
		log.info(">>>>>>>>调用服务获得数据msg:" + msgResult.getData());
		return Results.successWithData(msgResult.getData(), BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}

	/**
	 * 使用@RequestBody注解指定参数是对象
	 * 
	 * @return
	 */
	@RequestMapping(value = "/hello3", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Result getMsgFeignCloud3() throws ExecutionException, InterruptedException{
		User user = new User();
		user.setUserId(300l);
		user.setUsername("tan haohao");
		Result msgResult = feignService.getMsgFeignCloud(user);
		if (msgResult == null) {
			return Results.successWithData(BaseEnums.FAILURE.code(), BaseEnums.FAILURE.desc());
		}
		log.info(">>>>>>>>调用服务获得数据msg:" + msgResult.getData());
		return Results.successWithData(msgResult.getData(), BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}

}