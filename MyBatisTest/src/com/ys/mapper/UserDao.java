package com.ys.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ys.pojo.User;

public interface UserDao {
	
	@Select(value="select * from `user` as u where u.id = #{id}")
	public User selectUserById(Integer id);
	
	@Insert("insert into `user` values(default,#{userName},#{sex},#{birthday},#{address})")
	public void insertOne(User user);
	
	@Update("update `user` set user_name = #{userName} where id = #{id}")
	public void updateUserById(User user);
	
	@Delete("delete from `user` where user_name like CONCAT('%',#{userName},'%')")
	public void deleteByUserName(String userName);
	

}
