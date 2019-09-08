package com.tanzuhao.core.system.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tanzuhao.core.constants.Constants;
import com.tanzuhao.core.system.dto.User;
import com.tanzuhao.core.system.service.UserService;

/**
 * spring boot 集成angularJS相关demo
 * 
 * 一.流程:
 * 
 * 1.访问(模块)首页
 * 
 * 2.请求数据进行双向绑定
 * 
 * 二.demo:增删改查
 * 
 * @author tanzuhao
 *
 */
@RestController
@RequestMapping(value = "/angularUser")
public class AngularJsController {
	@Autowired
	private UserService userService;

	/**
	 * 
	 * @return
	 */
	@GetMapping("/sayHello")
	public User sayHello() {
		System.out.println(">>>>>>>>>>>>>>>>>>augular");
		User user = new User();
		user.setUsername("tanhaohao");
		user.setPassword("123456");
		return user;
	}

	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/findById", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public User findById(@RequestParam("userId") long userId) {
		User user = userService.get(userId);
		return user;
	}

	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<User> list(HttpServletRequest request, Model model) {
		List<User> list = userService.listUser();
		return list;
	}

	/**
	 * 
	 * @param username
	 * @param nickname
	 * @return
	 */
	@RequestMapping(value = "/insert", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public User insert(@RequestParam(value = "username") String username,
			@RequestParam(value = "nickname") String nickname) {
		User user = new User();
		user.setUsername(username);
		user.setNickname(nickname);
		user.setBirthday(new Date());
		user.setSex(Constants.Sex.MALE);
		user.setEnabled(Constants.Flag.YES);
		user.setPassword("a123456");
		userService.insert(user);
		return user;

	}

	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delete", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public int delete(HttpServletRequest request, Model model) {
		Long userId = Long.parseLong(request.getParameter("userId"));
		int count = userService.deleteById(userId);
		return count;

	}

	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public User update(HttpServletRequest request, Model model) {
		Long userId= Long.parseLong(request.getParameter("userId"));
		String username = request.getParameter("username");
		String nickname = request.getParameter("nickname");
		User user = userService.get(userId);
		user.setUsername(username);
		user.setNickname(nickname);
		userService.update(user);
		return user;
	}

}
