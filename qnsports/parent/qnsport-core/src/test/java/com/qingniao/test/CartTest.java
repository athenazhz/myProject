package com.qingniao.test;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qingniao.core.pojo.user.User;
/**
 * 购物车测试
 * 把json转换成对象，把对象转换成json
 * @author Administrator
 *
 */

public class CartTest {
	//把对象转换成json
	@Test
	public void demo1() throws IOException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		//将对象中为null的属性忽略掉
		objectMapper.setSerializationInclusion(Inclusion.NON_NULL);
		User user = new User();
		user.setUsername("张三");
		user.setPassword("123456");
		StringWriter w = new StringWriter();
		objectMapper.writeValue(w, user);
		System.out.println(w.toString());
		User user2 = objectMapper.readValue(w.toString(), User.class);
		System.out.println(user2.toString());
	}
}
