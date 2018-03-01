package com.qingniao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class RedisTest {
	@Test
	public void demo1() {
		Jedis jedis = new Jedis("192.168.11.129", 6379);
		jedis.set("name", "张三");
		jedis.close();
	}
	@Test
	public void demo2() {
		Jedis jedis = new Jedis("192.168.11.129", 6379);
		String name = jedis.get("name");
		System.out.println(name);
		jedis.close();
	}
	@Test
	public void demo3() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(50);
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.11.129", 6379);
		Jedis jedis = jedisPool.getResource();
		jedis.hset("user3", "name", "李四");
		jedis.hset("user3", "age", "10");
		String name = jedis.hget("user3", "name");
		String age = jedis.hget("user3", "age");
		System.out.println(name+age);
		jedis.close();
	}
	@Autowired
	private JedisPool jedisPool;
	//redis整合spring测试
	@Test
	public void demo4() {
		Jedis jedis = jedisPool.getResource();
		jedis.del("user3");
		jedis.close();
	}
}
