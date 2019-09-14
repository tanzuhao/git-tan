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
public class SpringCloudConfig2HighServerV1Application {
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfig2HighServerV1Application.class, args);
	}

}
