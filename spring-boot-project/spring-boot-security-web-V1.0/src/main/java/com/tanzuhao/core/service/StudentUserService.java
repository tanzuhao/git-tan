package com.tanzuhao.core.service;

import java.util.List;

import com.tanzuhao.core.bean.StudentUser;

/**
 * 
 * @author tanzuhao
 * @date: 2019年9月10日 下午4:37:34
 */
public interface StudentUserService {
	/**
	 * 
	 * @return
	 */
	public StudentUser findOneUser();
    /**
     * 
     * @return
     */
	public List<StudentUser> findAllUsers();
}
