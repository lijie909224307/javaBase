package com.redis.cache.controller;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lijie
 * @version 1.00
 * @Description: TODO
 * @date 2020-03-11 15:54
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){

        return "哎哟我草";
    }


}
