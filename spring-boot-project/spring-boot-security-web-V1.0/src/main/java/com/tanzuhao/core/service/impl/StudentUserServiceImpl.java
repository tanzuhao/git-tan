package com.tanzuhao.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tanzuhao.core.bean.StudentUser;
import com.tanzuhao.core.service.StudentUserService;
/**
 * 
 * @author tanzuhao
 * @date: 2019年9月10日 下午4:42:47
 */
@Service
public class StudentUserServiceImpl implements StudentUserService {
	/**
	 * 
	 */
	public StudentUser findOneUser() {
		StudentUser user = new StudentUser();
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
	public List<StudentUser> findAllUsers() {
		List<StudentUser> users = new ArrayList<StudentUser>();

		StudentUser user = new StudentUser();
		user.setId(1);
		user.setUsername("李文强");
		user.setPassword("12345");
		user.setTelephone("15890904567");
		user.setRegisterTime(new Date());
		user.setPopedom(0);
		users.add(user);

		user = new StudentUser();
		user.setId(2);
		user.setUsername("张海洋");
		user.setPassword("11111");
		user.setTelephone("13990904567");
		user.setRegisterTime(new Date());
		user.setPopedom(1);
		users.add(user);

		user = new StudentUser();
		user.setId(3);
		user.setUsername("吴文燕");
		user.setPassword("22222");
		user.setTelephone("15890978905");
		user.setRegisterTime(new Date());
		user.setPopedom(1);
		users.add(user);

		user = new StudentUser();
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
