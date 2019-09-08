package com.tanzuhao.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * 
 * @author tanzuhao
 *
 */
@SpringBootApplication
public class SpringBootBaseApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBaseApplication.class, args);
	}

}
