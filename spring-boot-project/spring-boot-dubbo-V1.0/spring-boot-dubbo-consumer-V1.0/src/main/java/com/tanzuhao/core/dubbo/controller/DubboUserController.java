package com.tanzuhao.core.dubbo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tanzuhao.core.base.Result;
import com.tanzuhao.core.dubbo.service.DubboUserService;
import com.tanzuhao.core.system.dto.User;
import com.tanzuhao.core.util.Results;

/**
 * 
 * 测试url: localhost:8086/dubbo/user/getUserList
 * 
 * @author tanzuhao
 * @date: 2019年9月15日 下午3:19:04
 */
@RequestMapping("/dubbo/user")
@RestController
public class DubboUserController {
	// dubbo提供了@Reference注解，可替换@Autowired注解，用于引入远程服务
	@Reference
	private DubboUserService dubboUserService;

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getUserList", method = { RequestMethod.GET, RequestMethod.POST })
	public Result getUserList() {
		List<User> list = dubboUserService.getUserList(100l);
		return Results.successWithData(list);
	}
}
