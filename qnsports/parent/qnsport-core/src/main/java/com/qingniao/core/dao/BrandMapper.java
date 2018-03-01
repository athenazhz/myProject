package com.qingniao.core.dao;

import java.util.List;

import com.qingniao.core.pojo.Brand;
import com.qingniao.core.pojo.BrandExample;

public interface BrandMapper {
	//添加品牌
	public void insertBrand(Brand brand);
	public List<Brand> selectByExample(BrandExample brandExample);
	public Integer selectCountByExample(BrandExample brandExample);
	public void batchDelete(Long[] ids);
	public Brand selectById(Long brandId);
	public void editBrand(Brand brand);
}
