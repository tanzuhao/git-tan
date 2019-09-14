package com.tanzuhao.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * 注册中心
 * @author tanzuhao
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableAdminServer
public class SpringCloudAdminMonitorServerV1Application {
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudAdminMonitorServerV1Application.class, args);
	}

}
