package com.cc.redis.secondkill;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtils {
	
	private static JedisPool jedisPool = null;
	
	static {
		System.out.println("aa");
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(10);
		jedisPool =  new JedisPool(poolConfig, "192.168.1.201", 6379);
		
	}
	
	public static Jedis getJedisConnection() {
		return jedisPool.getResource();
	}
	
}
