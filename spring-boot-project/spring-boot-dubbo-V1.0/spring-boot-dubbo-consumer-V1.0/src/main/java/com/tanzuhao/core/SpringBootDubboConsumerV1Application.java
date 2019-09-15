package com.tanzuhao.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
/**
 * 
 * @author tanzuhao
 * @date: 2019年9月9日 下午5:22:15
 */
@SpringBootApplication
@EnableDubbo
public class SpringBootDubboConsumerV1Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDubboConsumerV1Application.class, args);
	}
}
