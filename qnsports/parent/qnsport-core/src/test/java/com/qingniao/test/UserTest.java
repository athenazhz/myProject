package com.qingniao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qingniao.core.pojo.user.User;
import com.qingniao.core.service.LoginService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class UserTest {
	@Autowired
	private LoginService loginService;
	@Test
	public void demo1() {
		String username = "zhangsan";
		String password = "123456";
		User user = loginService.getUser(username, password);
		System.out.println(username.toString());
	}
}
