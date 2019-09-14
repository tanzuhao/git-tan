package com.tanzuhao.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 生产者启动类(提供数据,对外提供接口)
 * 
 * @EnableEurekaClient 服务发现注解
 * @author tanzuhao
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class SpringCloudProducer2HighV1Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudProducer2HighV1Application.class, args);
	}
}
