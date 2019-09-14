package com.tanzuhao.core.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tanzuhao.core.base.Mapper;
import com.tanzuhao.core.system.dto.User;

/**
 * 
 * @author tanzuhao
 * @date: 2019年9月9日 下午5:29:24
 */
public interface UserMapper extends Mapper<User> {
	//根据id删除用户
    @Delete("delete from sys_user where user_id = #{id}")
	public int deleteById(Long id);
    //查询用户列表
    @Select("select * from sys_user order by user_id")
    public List<User> listUser();
    //新增用户
    @Insert("insert into sys_user(user_id,username,password,nickname) values(#{userId},#{username},#{password},#{nickname})")
    public int insertUser(User user);
    //更新用户
    @Update("update sys_user set username=#{username} where user_id=#{userId}")
    public int updateUser(User user);
    
}