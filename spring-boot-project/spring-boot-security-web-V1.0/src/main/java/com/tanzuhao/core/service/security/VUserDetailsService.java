package com.tanzuhao.core.service.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tanzuhao.core.system.dto.SPermission;
import com.tanzuhao.core.system.dto.SRole;
import com.tanzuhao.core.system.dto.SUser;  

/**
 * 提供用户信息封装服务 
 * @author tanzuhao
 * @date: 2019年9月10日 下午6:25:54
 */
@Service  
public class VUserDetailsService implements UserDetailsService {  
  
    @Autowired  
    SecurityDataService securityDataService;  
    /** 
     * 根据用户输入的用户名返回数据源中用户信息的封装，返回一个UserDetails 
     */  
    @Override  
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {  
        SUser sUser = securityDataService.findSUserByName(username);  
        //用户角色列表  
        List<SRole> sRoleList = securityDataService.findSRoleListBySUserId(sUser.getId());  
        //用户资源权限列表  
        List<SPermission> sPermissionList = securityDataService.findSPermissionListBySUserId(sUser.getId());  
        return new VUserDetails(sUser, sRoleList, sPermissionList);  
    }  
  
}  
