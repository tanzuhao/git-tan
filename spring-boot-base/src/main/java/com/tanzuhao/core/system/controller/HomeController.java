package com.tanzuhao.core.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.WebContext;

import com.tanzuhao.core.system.bean.SysUser;
import com.tanzuhao.core.system.service.SysUserService;

/**
 * localhost:8085/index
 * 
 * 控制器使用的应该是@Controller，而不是@RestController,因为这里需要返回页面。
 * 
 * @RestController注解，相当于@Controller+@ResponseBody两个注解的结合, @Responsebody后，
 *                                                        返回结果直接写入HTTP response
 *                                                        body中，不会被解析为跳转路径
 * @author tanzuhao
 *
 */
@RequestMapping("/student")
@Controller
public class HomeController {
	@Autowired
	private SysUserService sysUserService;

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "student/studentIndex";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/showStudent")
	public String showStudent() {
		return "student/showStudent";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/findOneUser")
	public String findOneUser(Model model) {
		SysUser u = sysUserService.findOneUser();
		model.addAttribute("user", u);
		return "student/showOneUser";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/findAllUsers")
	public String findAllUsers(Model model) {
		List<SysUser> list = sysUserService.findAllUsers();
		model.addAttribute("users", list);
		return "student/showAllUsers";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/findAllUser")
	public String findAllUser(Model model) {
		List<SysUser> list = sysUserService.findAllUsers();
		model.addAttribute("users", list);
		return "student/showAllUser";
	}

	/**
	 * 访问带参数的消息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/welcome")
	public String welcome(Model model) {
		model.addAttribute("name", "谭好好");
		return "student/welcome";
	}

	/**
	 * 
	 * @param model
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/test")
	public String test(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		WebContext ctx = new WebContext(request, response, request.getServletContext());
		ctx.setVariable("book", "<智能时代>");
		session.setAttribute("city", "最美广州");
		return "student/testThymeleafObjects";

	}
}
