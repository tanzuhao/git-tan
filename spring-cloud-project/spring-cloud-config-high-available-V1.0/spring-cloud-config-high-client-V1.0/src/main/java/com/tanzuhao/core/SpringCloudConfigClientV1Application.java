package com.tanzuhao.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * config client 启动类
 * 
 * @EnableEurekaClient 服务发现注解
 * @author tanzuhao
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class SpringCloudConfigClientV1Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigClientV1Application.class, args);
	}
}
