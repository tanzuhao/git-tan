package com.tanzuhao.core.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tanzuhao.core.base.Result;
import com.tanzuhao.core.cloud.service.impl.HystrixFallbackService;

@FeignClient(value = "${eureka.producer.application.name}",fallback =HystrixFallbackService.class)
public interface HystrixService {
	/**
	 * 请求熔断注解，当服务出现问题时候会执行fallbackMetho属性的名为helloFallBack的方法
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/cloud/msg", method = RequestMethod.GET)
	public Result getHystrixMsg(@RequestParam("id") String id, @RequestParam("name") String name);
}
