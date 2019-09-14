package com.tanzuhao.core.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tanzuhao.core.base.Result;
import com.tanzuhao.core.cloud.service.impl.ZuulFallbackService;
/**
 * 1.客户端通过网关zuul调用生产者
 * 2.zuul带有有权限认证的功能（ZuulFilter过滤器）。
 * @author admin
 *
 */
@FeignClient(value = "${eureka.zuul.application.name}", fallback =ZuulFallbackService.class)
public interface ZuulService {
	/**
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "${eureka.zuul.routes.order.server}"+"/cloud/msg", method = RequestMethod.GET)
	public Result getZuulOrderMsg(@RequestParam("id") String id, @RequestParam("name") String name);

	/**
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "${eureka.zuul.routes.producer.server}"+"/cloud/msg", method = RequestMethod.GET)
	public Result getZuulProducerMsg(@RequestParam("id") String id, @RequestParam("name") String name);
	/**
	 * 经过zuul登录过滤器
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "${eureka.zuul.routes.order.server}"+"/cloud/findZuulMsg", method = RequestMethod.GET)
	public Result findByToken(@RequestParam("token") String token);
	/**
	 * 经过zuul限流过滤器
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "${eureka.zuul.routes.order.server}"+"/cloud/getZuulMsg", method = RequestMethod.GET)
	public Result get();
}
