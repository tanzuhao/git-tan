package com.tanzuhao.core.system.service;

import java.util.List;

import com.tanzuhao.core.system.bean.SysUserBean;
/**
 * 
 * @author tanzuhao
 *
 */
public interface SysUserBeanService {
	/**
	 * 
	 * @return
	 */
	public SysUserBean findOneUser();
    /**
     * 
     * @return
     */
	public List<SysUserBean> findAllUsers();
}
