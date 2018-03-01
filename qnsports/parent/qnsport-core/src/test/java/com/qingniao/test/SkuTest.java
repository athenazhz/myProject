package com.qingniao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qingniao.core.dao.product.SkuMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class SkuTest {
	@Autowired
	private SkuMapper skuMapper;
	@Test
	public void demo1() {
		Long productId = 1l;
		float price = skuMapper.selectMinPrice(productId);
		System.out.println(price);
	}
}
