package com.qingniao.freemarker.test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.qingniao.core.pojo.UserTest;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerTest {
	//框架入门测试
	@Test
	public void demo1() throws Exception {
		//创建Configuration对象
		Configuration configuration = new Configuration();
		//设置模板存放的目录
		String templateUrl = "D:/myproject/qnsports/parent/qnsport-console/src/main/webapp/WEB-INF/template/";
		//加载模板目录，设置读取模板编码方式
		configuration.setDirectoryForTemplateLoading(new File(templateUrl));
		configuration.setDefaultEncoding("utf-8");
		//获取模板对象
		Template template = configuration.getTemplate("temp.html");
		//创建数据
		Map map = new HashMap();
		map.put("name", "张怀志");
		//创建输出文件
		Writer out = new FileWriter("F:/freemarker/demo1.html");
		//通过模板输出文件
		template.process(map, out);
		//关流
		out.close();
		/**
		 * 模板中这样输出${name}
		 */
	}
	//输出对象
	@Test
	public void demo2() throws Exception {
		Configuration configuration = new Configuration();
		String templateUrl = "D:/myproject/qnsports/parent/qnsport-console/src/main/webapp/WEB-INF/template/";
		configuration.setDirectoryForTemplateLoading(new File(templateUrl));
		configuration.setDefaultEncoding("utf-8");
		Template template = configuration.getTemplate("temp.html");
		Writer out = new FileWriter(new File("F:/freemarker/demo2.html"));
		UserTest userTest = new UserTest();
		userTest.setUsername("张三");
		userTest.setPasswrod("123");
		Map rootMap = new HashMap();
		rootMap.put("userTest", userTest);
		template.process(rootMap, out);
		out.close();
		/**
		 * 
		 * 模板中这样输出${userTest.username}
		 */
	}
	//输出list
	@Test
	public void demo3() throws Exception {
		Configuration configuration = new Configuration();
		String url = "D:/myproject/qnsports/parent/qnsport-console/src/main/webapp/WEB-INF/template/";
		configuration.setDirectoryForTemplateLoading(new File(url));
		configuration.setDefaultEncoding("utf-8");
		Template template = configuration.getTemplate("temp.html");
		Writer out = new FileWriter("F:/freemarker/demo3.html");
		List users = new ArrayList<>();
		UserTest userTest = new UserTest();
		userTest.setPasswrod("123");
		userTest.setUsername("张怀志");
		UserTest userTest2 = new UserTest();
		userTest2.setPasswrod("1234");
		userTest2.setUsername("张怀志");
		users.add(userTest2);
		users.add(userTest);
		Map rootMap = new HashMap<>();
		rootMap.put("users", users);
		template.process(rootMap, out);
		out.close();
		/**
		 * 
		 * <#list users as user>
		 * 	${user.username}
		 * </#list>
		 * 
		 * 
		 * 
		 */
	}
	//输出map
	@Test
	public void demo4() throws Exception {
		Configuration configuration = new Configuration();
		String url = "D:/myproject/qnsports/parent/qnsport-console/src/main/webapp/WEB-INF/template/";
		configuration.setDirectoryForTemplateLoading(new File(url));
		configuration.setDefaultEncoding("utf-8");
		Template template = configuration.getTemplate("temp.html");
		Writer out = new FileWriter("F:/freemarker/demo4.html");
		Map rootMap = new HashMap();
		Map map = new HashMap();
		map.put("aaa", "123");
		map.put("bbb", "222");
		rootMap.put("m", map);
		template.process(rootMap, out);
		out.close();
		/**
		 * <#list m?keys as key>
				${m[key]}
			</#list>
		 * 
		 * 这里的m就是rootMap中的key值
		 */
	}
	//list中输出map
	@Test
	public void demo5() throws Exception {
		Configuration configuration = new Configuration();
		String url = "D:/myproject/qnsports/parent/qnsport-console/src/main/webapp/WEB-INF/template/";
		configuration.setDirectoryForTemplateLoading(new File(url));
		configuration.setDefaultEncoding("utf-8");
		Template template = configuration.getTemplate("temp.html");
		Writer out = new FileWriter("F:/freemarker/demo5.html");
		List list = new ArrayList();
		Map map = new HashMap();
		map.put("age", "12");
		map.put("name", "张山");
		list.add(map);
		Map rootMap = new HashMap();
		rootMap.put("list", list);
		template.process(rootMap, out);
		out.close();
		/**
		 * 
		 * <#list list as m>
				${m.name}
				${m.age}
			</#list>
		 * 
		 * 或
		 * <#list list as m>
				<#list m?keys as key>
					${m[key]}
				</#list>
			</#list>
		 * 这里的m相当于是别名 随便取
		 */
	}
	//获取下标
	@Test
	public void demo6() throws Exception {
		Configuration configuration = new Configuration();
		String url = "D:/myproject/qnsports/parent/qnsport-console/src/main/webapp/WEB-INF/template/";
		configuration.setDirectoryForTemplateLoading(new File(url));
		configuration.setDefaultEncoding("utf-8");
		Template template = configuration.getTemplate("temp.html");
		Writer out = new FileWriter("F:/freemarker/demo6.html");
		Map rootMap = new HashMap();
		List users = new ArrayList();
		UserTest user1 = new UserTest();
		user1.setUsername("张十三");
		user1.setPasswrod("12");
		users.add(user1);
		UserTest user2 = new UserTest();
		user2.setPasswrod("22");
		user2.setUsername("lisi");
		users.add(user2);
		rootMap.put("users", users);
		template.process(rootMap, out);
		out.close();
		/**
		 * 
		 * 
		 * <#list users as user>
				${user_index}
			</#list>
		 * 
		 * 
		 */
	}
	//模板中赋值
	@Test
	public void demo7() throws Exception {
		Configuration configuration = new Configuration();
		String url = "D:/myproject/qnsports/parent/qnsport-console/src/main/webapp/WEB-INF/template/";
		configuration.setDirectoryForTemplateLoading(new File(url));
		configuration.setDefaultEncoding("utf-8");
		Template template = configuration.getTemplate("temp.html");
		Writer out = new FileWriter("F:/freemarker/demo7.html");
		Map rootMap = new HashMap();
		rootMap.put("name", "模板中赋值");
		template.process(rootMap, out);
		out.close();
	}
	
	//业务逻辑判断
	@Test
	public void demo8() throws Exception {
		Configuration configuration = new Configuration();
		String url = "D:/myproject/qnsports/parent/qnsport-console/src/main/webapp/WEB-INF/template/";
		configuration.setDirectoryForTemplateLoading(new File(url));
		configuration.setDefaultEncoding("utf-8");
		Template template = configuration.getTemplate("temp.html");
		Writer out = new FileWriter("F:/freemarker/demo8.html");
		Map rootMap = new HashMap();
		template.process(rootMap, out);
		out.close();
	}
	//日期处理
	@Test
	public void demo9() throws Exception {
		Configuration configuration = new Configuration();
		String url = "D:/myproject/qnsports/parent/qnsport-console/src/main/webapp/WEB-INF/template/";
		configuration.setDirectoryForTemplateLoading(new File(url));
		configuration.setDefaultEncoding("utf-8");
		Template template = configuration.getTemplate("temp.html");
		Writer out = new FileWriter("F:/freemarker/demo9.html");
		Map rootMap = new HashMap();
		rootMap.put("currentTime", new Date());
		template.process(rootMap, out);
		out.close();
	}
	//空值处理
	@Test
	public void demo10() throws Exception {
		Configuration configuration = new Configuration();
		String url = "D:/myproject/qnsports/parent/qnsport-console/src/main/webapp/WEB-INF/template/";
		configuration.setDirectoryForTemplateLoading(new File(url));
		configuration.setDefaultEncoding("utf-8");
		Template template = configuration.getTemplate("temp.html");
		Writer out = new FileWriter("F:/freemarker/demo10.html");
		Map rootMap = new HashMap();
		rootMap.put("name", null);
		template.process(rootMap, out);
		out.close();
	}
}
