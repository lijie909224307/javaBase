package com.ys.dao;

import com.ys.pojo.User;

public interface UserMapper {
	
	User getUserById(Integer id);
	
	int updateUserById(User user);
	

}
