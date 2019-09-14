package com.tanzuhao.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * 
 * @author tanzuhao
 * @date: 2019年9月9日 下午5:22:15
 */
@SpringBootApplication
public class SpringBootRedisV1Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisV1Application.class, args);
	}
}
