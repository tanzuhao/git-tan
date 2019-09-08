package com.tanzuhao.core.system.service.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanzuhao.core.system.dto.SPermission;
import com.tanzuhao.core.system.dto.SRole;
import com.tanzuhao.core.system.dto.SUser;
import com.tanzuhao.core.system.mapper.SPermissionMapper;
import com.tanzuhao.core.system.mapper.SRoleMapper;
import com.tanzuhao.core.system.mapper.SUserMapper;

/**
 * Security 数据服务
 * 
 * @author tanzuhao
 */
@Service
public class SecurityDataService {
	@Autowired
	private SUserMapper sUserMapper;
	@Autowired
	private SRoleMapper sRoleMapper;
	@Autowired
	private SPermissionMapper sPermissionMapper;

	public SUser findSUserByName(String name) {
		SUser su = sUserMapper.findSUserByName(name);
		return su;
	}

	public List<SRole> findSRoleListBySUserId(Long sUserId) {
		return sRoleMapper.findSRoleListBySUserId(sUserId);
	}

	public List<SRole> findSRoleListBySPermissionUrl(String sPermissionUrl) {
		return sRoleMapper.findSRoleListBySPermissionUrl(sPermissionUrl);
	}

	public List<SPermission> findSPermissionListBySUserId(Long sUserId) {
		return sPermissionMapper.findSPermissionListBySUserId(sUserId);
	}

	public List<SPermission> findSPermissionListBySPermissionUrl(String sPermissionUrl) {
		return sPermissionMapper.findSPermissionListBySPermissionUrl(sPermissionUrl);
	}
}
