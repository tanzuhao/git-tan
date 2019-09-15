package com.tanzuhao.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

/**
 * 开启基于注解的dubbo功能（主要是包扫描@DubboComponentScan）
 * 
 * @author tanzuhao
 * @date: 2019年9月9日 下午5:22:15
 */
@SpringBootApplication
@EnableDubbo
public class SpringBootDubboProducer1V1Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDubboProducer1V1Application.class, args);
	}
}
