package com.tanzuhao.core.system.mapper;

import org.apache.ibatis.annotations.Select;

import com.tanzuhao.core.base.Mapper;
import com.tanzuhao.core.system.dto.SUser;
/** 
 * 用户信息查询 
 * @author tanzuhao
 */ 
public interface SUserMapper extends Mapper<SUser> {
	/** 
     * 根据用户名获取用户 
     *  
     * @param name 
     * @return 
     */  
    @Select(value = "SELECT * FROM s_user WHERE name = #{name}")  
    public SUser findSUserByName(String name);  
}
