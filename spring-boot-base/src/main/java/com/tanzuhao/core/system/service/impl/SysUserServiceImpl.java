package com.tanzuhao.core.system.service.impl;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tanzuhao.core.system.bean.SysUser;
import com.tanzuhao.core.system.service.SysUserService;
/**
 * 
 * @author tanzuhao
 *
 */
@Service
public class SysUserServiceImpl implements SysUserService{
	/**
	 * 
	 */
	public SysUser findOneUser() {
		SysUser user = new SysUser();
		user.setId(1);
		user.setUsername("李文强");
		user.setPassword("12345");
		user.setTelephone("15890904567");
		user.setRegisterTime(new Date());
		user.setPopedom(0);
		return user;
	}
    /**
     * 
     */
	public List<SysUser> findAllUsers() {
		List<SysUser> users = new ArrayList<SysUser>();

		SysUser user = new SysUser();
		user.setId(1);
		user.setUsername("李文强");
		user.setPassword("12345");
		user.setTelephone("15890904567");
		user.setRegisterTime(new Date());
		user.setPopedom(0);
		users.add(user);

		user = new SysUser();
		user.setId(2);
		user.setUsername("张海洋");
		user.setPassword("11111");
		user.setTelephone("13990904567");
		user.setRegisterTime(new Date());
		user.setPopedom(1);
		users.add(user);

		user = new SysUser();
		user.setId(3);
		user.setUsername("吴文燕");
		user.setPassword("22222");
		user.setTelephone("15890978905");
		user.setRegisterTime(new Date());
		user.setPopedom(1);
		users.add(user);

		user = new SysUser();
		user.setId(4);
		user.setUsername("郑智化");
		user.setPassword("33333");
		user.setTelephone("15990956905");
		user.setRegisterTime(new Date());
		user.setPopedom(1);
		users.add(user);

		return users;
	}


}
