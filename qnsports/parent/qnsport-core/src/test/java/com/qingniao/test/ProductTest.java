package com.qingniao.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qingniao.core.dao.product.ProductMapper;
import com.qingniao.core.pojo.product.Product;
import com.qingniao.core.pojo.product.ProductExample;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class ProductTest {
	@Autowired 
	private ProductMapper productMapper;
	@Test
	public void addProduct() {
		Product product = new Product();
		product.setId(1000l);
		product.setName("耐克运动衫");
		product.setDescription("质量好");
		productMapper.insertSelective(product);
	}
	@Test
	public void queryProduct() {
		ProductExample productExample = new ProductExample();
		List<Product> products = productMapper.selectByExample(productExample);
		System.out.println(products.size());
	}
	@Test
	public void editProduct() {
		Product product = new Product();
		product.setId(1000l);
		product.setName("李宁夹克衫");
		product.setDescription("衣服质量一般般");
		productMapper.updateByPrimaryKeySelective(product);
	}
	@Test
	public void deleteProduct() {
		productMapper.deleteByPrimaryKey(1000l);
	}
}
