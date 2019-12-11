package com.jedis;

import redis.clients.jedis.Jedis;

public class RedisTest {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1",6379);
//		jedis.set("name", "李杰111");
		System.out.println(jedis.get("name"));
		
	}
}
