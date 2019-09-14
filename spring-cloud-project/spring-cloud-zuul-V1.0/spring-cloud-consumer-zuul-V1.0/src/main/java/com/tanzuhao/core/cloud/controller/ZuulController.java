package com.tanzuhao.core.cloud.controller;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanzuhao.core.base.Result;
import com.tanzuhao.core.cloud.service.ZuulService;
import com.tanzuhao.core.constants.BaseEnums;
import com.tanzuhao.core.util.Results;

/**
 * http://localhost:8888/cloud/zuul/order
 * http://localhost:8888/cloud/zuul/producer
 * http://localhost:8888/cloud/zuul/order/find?token=123
 * 
 * @author admin
 *
 */
@RequestMapping("/cloud/zuul")
@RestController
public class ZuulController {
	private static final Logger log = LoggerFactory.getLogger(ZuulController.class);
	@Autowired
	private ZuulService zuulService;

	/**
	 * 
	 * @return
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@RequestMapping(value = "/order", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Result getOrderMsg() throws ExecutionException, InterruptedException {
		String id = "400";
		String name = "tanhahao-zuul-order";
		Result msgResult = zuulService.getZuulOrderMsg(id, name);
		if (msgResult == null) {
			return Results.successWithData(BaseEnums.FAILURE.code(), BaseEnums.FAILURE.desc());
		}
		log.info(">>>>>>>>调用服务获得数据 zuul order msg:" + msgResult.getData());
		return Results.successWithData(msgResult.getData(), BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}

	/**
	 * 
	 * @return
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@RequestMapping(value = "/producer", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Result getProducerMsg() throws ExecutionException, InterruptedException {
		String id = "400";
		String name = "tanhahao-zuul-producer";
		Result msgResult = zuulService.getZuulProducerMsg(id, name);
		if (msgResult == null) {
			return Results.successWithData(BaseEnums.FAILURE.code(), BaseEnums.FAILURE.desc());
		}
		log.info(">>>>>>>>调用服务获得数据 zuul producer msg:" + msgResult.getData());
		return Results.successWithData(msgResult.getData(), BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}
	/**
	 * 请求经过网关zuul登录过滤器
	 * @return
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@RequestMapping(value = "/order/find", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Result findByToken(@RequestParam(value="token", required = false) String token) throws ExecutionException, InterruptedException {
		Result msgResult = zuulService.findByToken(token);
		if (msgResult == null) {
			return Results.successWithData(BaseEnums.FAILURE.code(), BaseEnums.FAILURE.desc());
		}
		log.info(">>>>>>>>调用服务获得数据 zuul find by token msg:" + msgResult.getData());
		return Results.successWithData(msgResult.getData(), BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}
	/**
	 * 请求经过网关zuul限流过滤器
	 * @return
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@RequestMapping(value = "/order/get", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Result get() throws ExecutionException, InterruptedException {
		Result msgResult = zuulService.get();
		if (msgResult == null) {
			return Results.successWithData(BaseEnums.FAILURE.code(), BaseEnums.FAILURE.desc());
		}
		log.info(">>>>>>>>调用服务获得数据 zuul find by token msg:" + msgResult.getData());
		return Results.successWithData(msgResult.getData(), BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}


}
