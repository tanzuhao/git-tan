package com.tanzuhao.core.dubbo.service;

import java.util.List;

import com.tanzuhao.core.system.dto.User;

public interface DubboUserService {
	public List<User> getUserList(Long userId);
}
