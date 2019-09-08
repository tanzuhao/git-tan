package com.tanzuhao.core.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.tanzuhao.core.base.Mapper;
import com.tanzuhao.core.system.dto.SPermission;

public interface SPermissionMapper extends Mapper<SPermission>{
	  /** 
     * 根据用户ID获取资源权限列表 
     * @param sUserId 
     * @return 
     */  
    @Select(value=" SELECT * FROM s_permission sp " +   
            " LEFT JOIN s_role_permission srp ON sp.id = srp.fk_permission_id " +   
            " LEFT JOIN s_role sr ON srp.fk_role_id = sr.id " +   
            " LEFT JOIN s_user_role sur ON sr.id = sur.fk_role_id " +   
            " LEFT JOIN s_user su ON sur.fk_user_id = su.id " +   
            " WHERE su.id = #{sUserId} ")  
    public List<SPermission> findSPermissionListBySUserId(Long sUserId);  
      
    /** 
     * 根据资源路径获取资源权限列表 
     * @param sPermissionUrl 
     * @return 
     */  
    @Select(value=" SELECT * FROM s_permission sp WHERE sp.url = #{sUserId} ")  
    public List<SPermission> findSPermissionListBySPermissionUrl(String sPermissionUrl);  
}
