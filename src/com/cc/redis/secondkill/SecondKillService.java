package com.cc.redis.secondkill;

import java.util.List;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

public class SecondKillService {
	
	public boolean secondKill(String prdId) {
		
		String returnMessage = "";
		if (prdId == null || prdId.trim().equals("")) {
			return false;
		}
		//生成userId
		String userId = (int)(Math.random() * 50000) + "";
		
		Jedis jedis = JedisPoolUtils.getJedisConnection();
		
//		JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
//		
//		Jedis jedis= jedisPool.getResource();
		
		String redisUserKey = "sk:" + prdId + ":users";
		
		String qualtityStr = "sk:" + prdId + ":qt";
		//是否秒杀过
//		boolean isExits = jedis.exists(redisUserKey);
//		if (!isExits) {
//			jedis.sadd(redisUserKey, "");
//		}
		Boolean isMem = jedis.sismember(redisUserKey, userId);
		
		if (isMem) {
			returnMessage = "秒杀失败,您已秒杀过";
			System.err.println(returnMessage);
			jedis.close();
			return false;
		}
		
		jedis.watch(qualtityStr);
		//库存是否充足
		int qualtity = Integer.parseInt(jedis.get(qualtityStr));
		if (qualtity <= 0) {
			returnMessage = "秒杀失败，库存不足";
			System.err.println(returnMessage);
			jedis.close();
			return false;
		}
		
		//秒杀！
		
		Transaction transaction = jedis.multi();
		transaction.decr(qualtityStr);
		transaction.sadd(redisUserKey, userId);
		List<Object> exec = transaction.exec();
		
		if (exec == null || exec.isEmpty()) {
			returnMessage = "秒杀失败！被他人抢占";
			System.err.println(returnMessage);
			jedis.close();
			return false;
		} else {
			returnMessage = "秒杀成功！";
			System.out.println(returnMessage);
			jedis.close();
			return true;
		}
		
	}
	
}
