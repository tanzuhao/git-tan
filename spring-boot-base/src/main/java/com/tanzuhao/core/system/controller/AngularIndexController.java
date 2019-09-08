package com.tanzuhao.core.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * localhost:8085/angularIndex/index
 * 
 * @author tanzuhao
 *
 */
@RequestMapping("/angularIndex")
@Controller
public class AngularIndexController {
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index", produces = "application/json; charset=UTF-8")
	public String index(HttpServletRequest request) {
		return "/angular/angularIndex";
	}
}
