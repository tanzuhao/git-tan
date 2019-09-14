package com.tanzuhao.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * @author tanzuhao
 * @date: 2019年9月10日 下午6:21:35
 */
@Controller
public class SecurityController {
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}

	@RequestMapping("/admin")
	public String admin(Model model, String tt) {
		return "admin";
	}

	@RequestMapping("/hello")
	public String hello(Model model, String tt) {
		return "hello";
	}
}
