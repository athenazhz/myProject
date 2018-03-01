package com.qingniao.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingniao.core.dao.user.UserMapper;
import com.qingniao.core.pojo.user.User;
import com.qingniao.core.pojo.user.UserExample;
import com.qingniao.core.service.LoginService;
import com.qingniao.core.utils.MD5Utils;
@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserMapper userMapper;
	/**
	 * 通过用户名和密码登陆
	 * 
	 */
	public User getUser(String username, String password) {
		//对密码进行md5加密
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(MD5Utils.md5(password));
		List<User> users = userMapper.selectByExample(userExample);
		return users.get(0);
	}

}
