package com.tanzuhao.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 生产者启动类(提供数据,对外提供接口)
 * 
 * @EnableEurekaClient 服务发现注解
 * 
 * @EnableCircuitBreaker 允许断路器注解
 * @author tanzuhao
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SpringCloudConsumerHystrixV1Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConsumerHystrixV1Application.class, args);
	}
}
