package com.tanzuhao.core.cloud.controller;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tanzuhao.core.base.Result;
import com.tanzuhao.core.cloud.service.HystrixService;
import com.tanzuhao.core.constants.BaseEnums;
import com.tanzuhao.core.util.Results;
/**
 * 熔断处理
 * http://localhost:8888/cloud/hystrix/hello
 * @author tanzuhao
 *
 */
@RequestMapping("/cloud/hystrix")
@RestController
public class HystrixController {
	private static final Logger log = LoggerFactory.getLogger(HystrixController.class);
	@Autowired
	private HystrixService hystrixService;
	/**
	 * 熔断处理
	 * 
	 * @return
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@RequestMapping(value = "/hello", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Result getMsgHystrixCloud() throws ExecutionException, InterruptedException {
		String id="400";
		String name="tan hahao hystrix";
		Result msgResult = hystrixService.getHystrixMsg(id,name);
		if (msgResult == null) {
			return Results.successWithData(BaseEnums.FAILURE.code(), BaseEnums.FAILURE.desc());
		}
		log.info(">>>>>>>>调用服务获得数据 hystrix msg:" + msgResult.getData());
		return Results.successWithData(msgResult.getData(), BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}

}
