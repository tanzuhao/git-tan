package com.tanzuhao.core.system.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanzuhao.core.base.BaseService;
import com.tanzuhao.core.system.dto.User;
import com.tanzuhao.core.system.mapper.UserMapper;
import com.tanzuhao.core.system.service.UserService;

/**
 * 用户Service实现类
 * @author tanzuhao
 * @date: 2019年9月9日 下午5:29:48
 */
@Service
public class UserServiceImpl extends BaseService<User> implements UserService {
	public static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper userMapper;
	
	public int deleteById(Long id){
		log.info(">>>>>>>>>>>>>>>>>>根据ID进行删除");
		int count=userMapper.deleteById(id);
		return count;
	}
	
	public List<User> listUser(){
		log.info(">>>>>>>>>>>>>>>>>>查询所有记录");
		return userMapper.listUser();
	}
	
	 public int insertUser(User user){
		 log.info(">>>>>>>>>>>>>>>>>>新增记录");
		 return userMapper.insertUser(user);
	 };
	 
	 public int updateUser(User user){
		 log.info(">>>>>>>>>>>>>>>>>>更新记录");
		 return userMapper.updateUser(user);
	 };

}