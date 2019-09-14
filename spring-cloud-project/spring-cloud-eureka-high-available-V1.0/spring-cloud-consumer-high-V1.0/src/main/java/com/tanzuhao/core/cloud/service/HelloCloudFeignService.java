package com.tanzuhao.core.cloud.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tanzuhao.core.base.Result;
import com.tanzuhao.core.system.dto.User;
/**
 * 
 *@author tanzuhao
 *
 */
@FeignClient(value = "${eureka.producer.application.name}")
public interface HelloCloudFeignService {
	/**
	 * @RequestMapping(value="请求接口访问路径一致") 使用@RequestParam注解指定请求的参数是什么
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/cloud/msg", method = RequestMethod.GET)
	public Result getMsgFeignCloud(@RequestParam("id") String id, @RequestParam("name") String name);
    /**
     * 参数多时,可以用map
     * @param map
     * @return
     */
	@RequestMapping(value = "/cloud/msg2", method = RequestMethod.GET)
	public Result getMsgFeignCloud(@RequestParam Map<String, Object> map);

	/**
	 * 使用@RequestBody注解指定参数是对象
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/cloud/feign/msg", method = RequestMethod.POST)
	public Result getMsgFeignCloud(@RequestBody User user);

}
