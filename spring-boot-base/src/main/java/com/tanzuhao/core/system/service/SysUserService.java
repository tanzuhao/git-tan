package com.tanzuhao.core.system.service;

import java.util.List;

import com.tanzuhao.core.system.bean.SysUser;
/**
 * 
 * @author tanzuhao
 *
 */
public interface SysUserService {
	/**
	 * 
	 * @return
	 */
	public SysUser findOneUser();
    /**
     * 
     * @return
     */
	public List<SysUser> findAllUsers();
}
