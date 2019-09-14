package com.tanzuhao.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 注册中心
 * @author tanzuhao
 *
 */
@EnableEurekaClient
@EnableConfigServer
@SpringBootApplication
public class SpringCloudConfigServerV2Application {
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigServerV2Application.class, args);
	}

}
