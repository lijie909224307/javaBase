package com.redis.cache.controller;

import com.redis.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lijie
 * @version 1.00
 * @Description: TODO
 * @date 2020-03-11 17:00
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public String userById(@PathVariable("userId")Integer userId){
        return userService.getUserById(userId);
    }


}
