package com.qingniao.core.utils;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * 生成静态页工具类
 * @author Administrator
 *
 */
@Service
public class FreeMarkerUtil implements ServletContextAware{
	@Autowired
	private FreeMarkerConfig freeMarkerConfig;
	
	private ServletContext servletContext;
	//生成商品静态页
	public void htmlGenerator(Map map) {
		Long productId = (Long)map.get("productId");
		Configuration configuration = freeMarkerConfig.getConfiguration();
		//用商品的id作为静态页面的名称
		try {
			Template template = configuration.getTemplate("productDetail.html");
			String path = "/html/product/"+productId+".html";//相对路径
			//获取服务器绝对路径
			String realPath = servletContext.getRealPath("");
			String filePath = realPath + path;//绝对路径
			Writer out = new OutputStreamWriter(new FileOutputStream(filePath),"utf-8");
			template.process(map, out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
