package com.qingniao.core.utils.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qingniao.core.utils.Constants;
import com.qingniao.core.utils.LocalSessionProvider;

/**
 * 
 * 用户登录拦截器
 * @author Administrator
 *
 */
public class LoginIntercetptor implements HandlerInterceptor{
	@Autowired
	private LocalSessionProvider localSessionProvider;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取用户名
		String name = localSessionProvider.getAttribute(request, response, Constants.USER_NAME);
		//获得访问的url
		String url = request.getRequestURI();
		//判断是否是购买页面
		if (url.startsWith("/buy")) {
			//判断是否登陆
			if (name != null) {
				//设置一个标记
				request.setAttribute("isLogin", true);
			} else {
				request.setAttribute("isLogin", false);
				response.sendRedirect("/login.html?url="+request.getParameter("url"));
				return false;
			}
		} else {
			if (name != null) {
				request.setAttribute("isLogin", true);
			} else {
				request.setAttribute("isLogin", false);
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}