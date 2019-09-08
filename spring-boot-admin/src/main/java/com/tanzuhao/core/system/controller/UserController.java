package com.tanzuhao.core.system.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanzuhao.core.base.BaseController;
import com.tanzuhao.core.base.Result;
import com.tanzuhao.core.constants.BaseEnums;
import com.tanzuhao.core.constants.Constants;
import com.tanzuhao.core.system.dto.User;
import com.tanzuhao.core.system.service.UserService;
import com.tanzuhao.core.util.Results;

/**
 * 用户Controller 访问地址:
 * 
 * http://localhost:8085/sys/user/listUser
 * http://localhost:8085/sys/user/findById/13
 * http://localhost:8085/sys/user/save?username=tanzxuhao
 * http://localhost:8085/sys/user/update?userId=5&username=小米
 * http://localhost:8085/sys/user/delete/1
 * 
 * @author tanzuhao
 *
 */
@RequestMapping("/sys/user")
@RestController
public class UserController extends BaseController {
	@Autowired
	private UserService userService;

	/**
	 * 查询用户列表
	 * 
	 * @return
	 */
	@RequestMapping("/listUser")
	public Result listUser() {
		// List<User> list = userService.selectAll();//调用通用查询方法
		List<User> list = userService.listUser();
		return Results.successWithData(list, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
	}

	/**
	 * @PathVariable是用来获得请求url中的动态参数
	 * @param userId
	 * @return
	 */
	@RequestMapping("/findById/{userId}")
	public Result findById(@PathVariable Long userId) {
		User user = userService.get(userId);
		return Results.successWithData(user);
	}

	/**
	 * 新增用户
	 * 
	 * @param username
	 * @return
	 */
	// @PostMapping("/save")
	@GetMapping("/save")
	public Result save(@RequestParam("username") String username) {
		User user1 = new User();
		user1.setUsername(username);
		user1.setNickname("谭好好");
		user1.setBirthday(new Date());
		user1.setSex(Constants.Sex.MALE);
		user1.setEnabled(Constants.Flag.YES);
		user1.setPassword("a123456");
		// user1 = userService.insertSelective(user1);//调用通用新增方法
		userService.insertUser(user1);
		return Results.successWithData(user1);
	}

	/**
	 * 更新用户
	 * 
	 * @param userId
	 * @param username
	 * @return
	 */
	// @PostMapping("/update")
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public Result update(@RequestParam("userId") long userId, @RequestParam("username") String username) {
		User user = userService.get(userId);
		user.setUsername(username);
		// userService.update(user);//调用通用更新方法
		userService.updateUser(user);
		return Results.successWithData(user);
	}

	/**
	 * 删除用户
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/delete/{userId}")
	public Result delete(@PathVariable Long userId) {
		// userService.delete(userId);//调用通用删除方法
		userService.deleteById(userId);// 调用业务删除方法
		return Results.success();
	}

}