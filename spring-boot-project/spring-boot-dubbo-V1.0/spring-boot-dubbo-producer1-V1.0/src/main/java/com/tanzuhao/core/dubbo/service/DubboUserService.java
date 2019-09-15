package com.tanzuhao.core.dubbo.service;

import java.util.List;

import com.tanzuhao.core.system.dto.User;

/**
 * 
 * @author tanzuhao
 * @date: 2019年9月15日 下午3:01:57
 */
public interface DubboUserService {
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<User> getUserList(Long userId);
}
