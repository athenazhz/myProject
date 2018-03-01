package com.qingniao.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingniao.core.dao.UserTestMapper;
import com.qingniao.core.pojo.UserTest;
import com.qingniao.core.service.UserTestService;

@Service
@Transactional
public class UserTestServiceImpl implements UserTestService{
	@Autowired
	private UserTestMapper userTestMapper;
	public void insertUser(UserTest userTest) {
		userTestMapper.insertUserTest(userTest);
		int a = 1/0;
	}
}
