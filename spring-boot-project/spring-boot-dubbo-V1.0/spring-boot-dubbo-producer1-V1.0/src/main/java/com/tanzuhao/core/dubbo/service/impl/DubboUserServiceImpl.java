/**
 * 
 */
package com.tanzuhao.core.dubbo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.tanzuhao.core.dubbo.service.DubboUserService;
import com.tanzuhao.core.system.dto.User;

/**
 * @author tanzuhao
 * @date: 2019年9月15日 下午3:00:24
 */
@Service // 属于Dubbo的@Service注解，非Spring作用：暴露服务
//@Component
public class DubboUserServiceImpl implements DubboUserService {
	
	@Override
	public List<User> getUserList(Long userId) {
		User u = new User();
		u.setUserId(100l);
		u.setUsername("tanzuhao");
		List<User> list = new ArrayList<User>();
		list.add(u);
		return list;
	}

}
