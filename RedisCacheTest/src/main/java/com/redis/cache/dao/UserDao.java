package com.redis.cache.dao;

import com.redis.cache.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author lijie
 * @version 1.00
 * @Description: TODO
 * @date 2020-03-11 16:57
 */
@Repository
public interface UserDao {

    User getUserById(Integer userId);

}
