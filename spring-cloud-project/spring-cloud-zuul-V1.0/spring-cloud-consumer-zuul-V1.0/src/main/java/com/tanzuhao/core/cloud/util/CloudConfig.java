package com.tanzuhao.core.cloud.util;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 系统启动时，加载配置类
 * 
 * @author tanzuhao
 *
 */
@Configuration
public class CloudConfig {
	/**
	 * 根据服务名远程调用 可做负载均衡
	 * 
	 * @return
	 */
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
