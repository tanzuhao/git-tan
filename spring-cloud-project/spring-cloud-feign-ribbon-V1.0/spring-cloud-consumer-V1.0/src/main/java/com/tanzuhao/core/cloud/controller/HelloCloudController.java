package com.tanzuhao.core.cloud.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tanzuhao.core.base.Result;
import com.tanzuhao.core.cloud.service.HelloCloudFeignService;
import com.tanzuhao.core.cloud.service.HelloCloudRibbonService;
import com.tanzuhao.core.constants.BaseEnums;
import com.tanzuhao.core.system.dto.User;
import com.tanzuhao.core.util.Results;
/**
 * 消费者
 * http://localhost:8888/cloud/hello
 * @author tanzuhao
 *
 */
@RequestMapping("/cloud")
@RestController
public class HelloCloudController{
	private static final Logger log = LoggerFactory.getLogger(HelloCloudController.class);
	//@Autowired
	//private RestTemplate restTemplate;
	// 通过注解获取配置文件变量
	@Value("${eureka.producer.url}")
	private String producerUrl;
	@Autowired
	private HelloCloudRibbonService helloCloudRibbonService;
	@Autowired
	private HelloCloudFeignService helloCloudFeignService;
	/**
	 * spring cloud demo
	 * 根据IP+端口号访问生产者提供的接口
	 * 缺点:不能负载均衡
	 * 改善:引入ribbon，通过访问应用名的方式，实现接口调用(应用名自动会和具体的地址映射)
	 * @return
	 */
/*	@RequestMapping(value = "/hello", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Result getMsgCloud() {
		Result msgResult = restTemplate.getForObject(producerUrl+"/cloud/msg?id=12&name=tanzuhao", Result.class);
		if (msgResult == null) {
			return Results.successWithData(BaseEnums.FAILURE.code(), BaseEnums.FAILURE.desc());
		}
		log.info(">>>>>>>>调用服务获得数据msg:" + msgResult.getData());
		return Results.successWithData(msgResult.getData(), BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}
	*/
	/**
	 * 根据应用名访问生产者提供的接口，其中应用名会自动映射具体IP和端口号
	 * 缺点：参数多时，url拼接麻烦
	 * 改善:引入Feign,通过创建Feign接口,添加注解@FeignClient
	 * @return
	 */
	@RequestMapping(value = "/hello", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Result getMsgRobbinCloud() {
		String id="10";
		String name="tanzuhao";
		Result msgResult =helloCloudRibbonService.getMsgCloud(id,name);
		if (msgResult == null) {
			return Results.successWithData(BaseEnums.FAILURE.code(), BaseEnums.FAILURE.desc());
		}
		log.info(">>>>>>>>调用服务获得数据msg:" + msgResult.getData());
		return Results.successWithData(msgResult.getData(), BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}
	/**
	 * 使用@RequestParam注解指定请求的参数是什么
	 * @return
	 */
	@RequestMapping(value = "/helloFeign", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Result getMsgFeignCloud() {
		String id="100";
		String name="tanzuhao";
		Result msgResult =helloCloudFeignService.getMsgFeignCloud(id,name);
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
	@RequestMapping(value = "/helloFeign2", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Result getMsgFeignCloud2() {
		String id="200";
		String name="tanzuhao-map";
		Map<String,Object> ret=new HashMap<String,Object>();
		ret.put("id", id);
		ret.put("name", name);
		Result msgResult =helloCloudFeignService.getMsgFeignCloud(ret);
		if (msgResult == null) {
			return Results.successWithData(BaseEnums.FAILURE.code(), BaseEnums.FAILURE.desc());
		}
		log.info(">>>>>>>>调用服务获得数据msg:" + msgResult.getData());
		return Results.successWithData(msgResult.getData(), BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}
	/**
	 * 使用@RequestBody注解指定参数是对象
	 * @return
	 */
	@RequestMapping(value = "/helloFeign3", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Result getMsgFeignCloud3() {
		User user=new User();
		user.setUserId(300l);
		user.setUsername("tan haohao");
		Result msgResult =helloCloudFeignService.getMsgFeignCloud(user);
		if (msgResult == null) {
			return Results.successWithData(BaseEnums.FAILURE.code(), BaseEnums.FAILURE.desc());
		}
		log.info(">>>>>>>>调用服务获得数据msg:" + msgResult.getData());
		return Results.successWithData(msgResult.getData(), BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}
	
}
