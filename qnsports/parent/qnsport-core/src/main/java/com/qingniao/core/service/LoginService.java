package com.qingniao.core.service;


import com.qingniao.core.pojo.user.User;


public interface LoginService {
	public User getUser(String username, String password);
}
