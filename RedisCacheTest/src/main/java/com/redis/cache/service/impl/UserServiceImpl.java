package com.redis.cache.service.impl;

import com.redis.cache.dao.UserDao;
import com.redis.cache.entity.User;
import com.redis.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


/**
 * @author lijie
 * @version 1.00
 * @Description: TODO
 * @date 2020-03-11 16:59
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Cacheable(value = "UserCache",key = "#userId",condition = "#userId > 2")
    public String getUserById(Integer userId) {
        User user = userDao.getUserById(userId);
        if(null != user){
            return user.toString();
        }
        return "查无此人.";
    }
}
