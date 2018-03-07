package com.qingniao.core.service;


import java.util.List;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;

import com.qingniao.common.page.PageInfo;
import com.qingniao.core.pojo.Brand;
import com.qingniao.core.pojo.BrandExample;

public interface BrandService {
	//添加
	public void insertBrand(Brand brand);
	//按条件查询品牌
	public PageInfo selectByExample(BrandExample brandExample);
	//查询记录数
	public Integer selectCountByExample(BrandExample brandExample);
	//删除
	public void batchDelete(Long[] ids);
	//通过id查询
	public Brand selectById(Long brandId);
	//修改brand
	public void editBrand(Brand brand);
	//查询所有品牌
	public List<Brand> selectAllByExample(BrandExample brandExample);
}
