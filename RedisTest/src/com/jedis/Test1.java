package com.jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.sound.midi.Soundbank;
import javax.swing.text.StyledEditorKit.ItalicAction;

import com.sun.corba.se.impl.interceptors.PINoOpHandlerImpl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class Test1 {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
//		System.out.println(jedis.ping());
//		System.out.println(jedis.get("x"));
//		jedis.set("name", "我了个擦");
//		System.out.println(jedis.get("name"));
		
		/*jedis.lpush("fruit", "苹果");
		jedis.lpush("fruit", "香蕉");
		jedis.lpush("fruit", "菠萝");
		List<String> list = jedis.lrange("fruit", 0, 100);
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			String string = list.get(i);
			System.out.println(string);
		}*/
		
		/*
		 * 取出所有的key 
	    Set<String> keys = jedis.keys("*");
		for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
			String string = iterator.next();
			System.out.println(string);
		}*/
		
		long start = System.currentTimeMillis();
		usePipeline();
		long end = System.currentTimeMillis();
		System.out.println("usePipeline:" + (end - start));
		
		start = System.currentTimeMillis();
		withoutPipeline();
		end = System.currentTimeMillis();
		System.out.println("withoutPipeline:" + (end - start));
	}
	
	public static void usePipeline(){
		Jedis jedis = new Jedis("localhost",6379);
		Pipeline pipeline = jedis.pipelined();
		for (int i = 0; i < 1000000; i++) {
			pipeline.incr("x");
		}
		pipeline.sync();
		jedis.disconnect();
	}
	
	public static void withoutPipeline(){
		Jedis jedis = new Jedis("localhost",6379);
		for(int i = 0 ; i < 1000000; i++ ){
			jedis.incr("x");
		}
		jedis.disconnect();
	}

}
