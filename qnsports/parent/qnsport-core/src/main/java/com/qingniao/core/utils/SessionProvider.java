package com.qingniao.core.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 提供本地和远程的session存储
 * @author Administrator
 *
 */
public interface SessionProvider {
	public void setAttribute(HttpServletRequest request, HttpServletResponse response, String name, String value);
	public String getAttribute(HttpServletRequest request, HttpServletResponse response, String name);
	public String getSessionId(HttpServletRequest request, HttpServletResponse response);
}
