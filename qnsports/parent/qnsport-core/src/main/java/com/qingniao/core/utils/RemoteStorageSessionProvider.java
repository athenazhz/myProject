package com.qingniao.core.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
/**
 * 
 * 远程session实现类
 * @author Administrator
 *
 */
public class RemoteStorageSessionProvider implements SessionProvider {
	@Autowired
	private JedisPool jedisPool;
	/**
	 * 保存用户信息，判断是保存用户名还是验证码
	 * 
	 */
	public void setAttribute(HttpServletRequest request, HttpServletResponse response, String name, String value) {
		Jedis jedis = jedisPool.getResource();
		if (name.equals(Constants.USER_NAME)) {
			jedis.set(name+getSessionId(request, response), value);//保存用户名和sessionid到redis
			jedis.expire(name+getSessionId(request, response), 60*30);//设置保存时间为半小时
		} else if (name.equals(Constants.USER_CODE)) {
			jedis.set(name+getSessionId(request, response), value);//保存验证码和sessionid到redis
			jedis.expire(name+getSessionId(request, response), 30);//缓存30秒
		}
		jedis.close();
	}
	/**
	 * 
	 * 获取用户信息
	 */
	public String getAttribute(HttpServletRequest request, HttpServletResponse response, String name) {
		Jedis jedis = jedisPool.getResource();
		String value = jedis.get(name+getSessionId(request, response));
		jedis.close();
		return value;
	}
	/**
	 * 
	 * 自定义sessionid
	 */
	public String getSessionId(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		String c = findByCookie(cookies, Constants.USER_SESSIONID);
		if (c != null) {
			return c;
		} else {
			String sessoinId = IDUtils.getSessoinId();
			Cookie cookie = new Cookie(Constants.USER_SESSIONID, sessoinId);
			cookie.setPath("/");
			response.addCookie(cookie);
			return sessoinId;
		}
	}
	/**
	 * 
	 * 根据cookie找到sessionId
	 * @param cookies
	 * @param name
	 * @return
	 */
	public String findByCookie(Cookie[] cookies, String name) {
		for (Cookie cookie : cookies) {
			if (cookie.equals(name)) {
				return cookie.getValue();
			}
		}
		return null;
	}
}
