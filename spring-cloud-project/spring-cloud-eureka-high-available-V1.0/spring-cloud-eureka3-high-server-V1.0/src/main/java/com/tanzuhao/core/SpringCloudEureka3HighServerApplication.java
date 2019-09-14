package com.tanzuhao.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心
 * @author tanzuhao
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class SpringCloudEureka3HighServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEureka3HighServerApplication.class, args);
	}

}
