package com.tanzuhao.core.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
