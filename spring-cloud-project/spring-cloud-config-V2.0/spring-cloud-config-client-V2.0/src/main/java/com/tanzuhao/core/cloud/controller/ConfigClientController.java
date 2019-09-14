package com.tanzuhao.core.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 当有请求/fresh节点的时候，会重新请求一次ConfigServer去拉取最新的配置文件 请求/fresh需要有几点要求：1.加actuator的依赖
 * 2.SpringCloud1.5以上需要设置 management.security.enabled=false
 * 这个Controller的作用是查看profile这个key的值
 * 
 * @author tanzuhao
 *
 */
@RefreshScope // 开启更新功能
@RequestMapping("/cloud")
@RestController
public class ConfigClientController {

	@Value("${profile}")
	private String profile;

	@RequestMapping(value = "/profile", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String profile() {
		return this.profile;
	}

}
