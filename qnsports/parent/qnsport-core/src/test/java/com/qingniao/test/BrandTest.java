package com.qingniao.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qingniao.common.page.PageInfo;
import com.qingniao.core.dao.BrandMapper;
import com.qingniao.core.pojo.Brand;
import com.qingniao.core.pojo.BrandExample;
import com.qingniao.core.service.BrandService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class BrandTest {
	@Autowired
	private BrandMapper brandMapper;
	@Autowired
	private BrandService brandService;
	@Test
	public void addBrand() {
		Brand brand = new Brand();
		brand.setBrandId(1l);
		brand.setBrandName("阿迪达斯");
		brand.setBrandDescription("质量好");
		brand.setBrandUrl("www.adidas.com");
		brand.setBrandLogo("22");
		brand.setBrandStatus(1);
		brandService.insertBrand(brand);
	}
	
	@Test
	public void selectByBrandExample() {
		BrandExample brandExample = new BrandExample();
		List<Brand> brands = brandMapper.selectByExample(brandExample);
		for (Brand brand : brands) {
			System.out.println(brand.getBrandName());
		}
	}
	@Test
	public void selectByBrandExample2() {
		BrandExample brandExample = new BrandExample();
		brandExample.setBrandName("");
		brandExample.setBrandStatus(1);
		brandExample.setPageNo(1);
		List<Brand> brands = brandMapper.selectByExample(brandExample);
		System.out.println(brands.size());
	}
	@Test
	public void selectByBrandExample4() {
		BrandExample brandExample = new BrandExample();
		brandExample.setBrandName("");
		brandExample.setBrandStatus(1);
		Integer count = brandService.selectCountByExample(brandExample);
		System.out.println(count);
	}
	@Test
	public void selectByBrandExample3() {
		BrandExample brandExample = new BrandExample();
		brandExample.setBrandName("");
		brandExample.setBrandStatus(1);
		brandExample.setPageNo(1);
		PageInfo pageInfo = new PageInfo(brandExample.getPageNo(), brandExample.getPageSize(), brandService.selectCountByExample(brandExample));
		List<Brand> brandList = brandMapper.selectByExample(brandExample);
		pageInfo.setList(brandList);
		//pageInfo.setPageView(null);制作分页工具栏
	}
	@Test
	public void makePageView() {
		BrandExample brandExample = new BrandExample();
		brandExample.setBrandName("");
		brandExample.setBrandStatus(1);
		brandExample.setPageNo(1);
		PageInfo pageInfo = new PageInfo(brandExample.getPageNo(), brandExample.getPageSize(), brandService.selectCountByExample(brandExample));
		List<Brand> brandList = brandMapper.selectByExample(brandExample);
		pageInfo.setList(brandList);
		StringBuilder params = new StringBuilder();
		params.append("name=张三").append("status=1");
		pageInfo.pageView("/brand/list.do", params.toString());
		List<String> pageView = pageInfo.getPageView();
		System.out.println(pageView);
	}
	@Test
	public void demo5() {
		long brandId = 7;
		Brand brand = brandMapper.selectById(brandId);
		System.out.println(brand.getBrandName());
	}
}
