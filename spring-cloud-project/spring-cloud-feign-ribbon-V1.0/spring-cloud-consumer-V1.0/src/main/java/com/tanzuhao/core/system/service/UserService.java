package com.tanzuhao.core.system.service;

import java.util.List;

import com.tanzuhao.core.base.Service;
import com.tanzuhao.core.system.dto.User;

/**
 * 用户Service接口
 * @author tanzuhao
 * @date: 2019年9月9日 下午5:30:04
 */
public interface UserService extends Service<User> {
	/**
	 * 
	 * @param id
	 * @return
	 */
	public int deleteById(Long id);
	/**
	 * 
	 * @return
	 */
	public List<User> listUser();
	/**
	 * 
	 * @param user
	 * @return
	 */
	public int insertUser(User user);
	 /**
	  * 
	  * @param user
	  * @return
	  */
	public int updateUser(User user);
}