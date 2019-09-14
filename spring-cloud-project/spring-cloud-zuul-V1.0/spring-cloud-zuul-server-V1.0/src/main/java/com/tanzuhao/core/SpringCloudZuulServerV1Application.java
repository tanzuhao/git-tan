package com.tanzuhao.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 网关服务启动类
 *  网关是系统的唯一对外的入口，介于客户端和服务器端之间的中间层，
 *  处理非业务功能 提供路由请求、鉴权、监控、缓存、限流等功能。
 * @EnableZuulProxy 网关注解
 * @author admin
 *
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class SpringCloudZuulServerV1Application {
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudZuulServerV1Application.class, args);
	}

}
