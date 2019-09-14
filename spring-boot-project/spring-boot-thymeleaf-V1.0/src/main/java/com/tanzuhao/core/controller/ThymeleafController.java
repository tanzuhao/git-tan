package com.tanzuhao.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * localhost:8086/index
 * 
 * 控制器使用的应该是@Controller，而不是@RestController,因为这里需要返回页面。
 * 
 * @RestController注解，相当于@Controller+@ResponseBody两个注解的结合,@Responsebody后，
 * 返回结果直接写入HTTP response body中，不会被解析为跳转路径
 * @author tanzuhao
 *
 */
@Controller
public class ThymeleafController {
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
}
