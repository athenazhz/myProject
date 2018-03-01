package com.qingniao.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingniao.common.page.PageInfo;
import com.qingniao.core.dao.BrandMapper;
import com.qingniao.core.pojo.Brand;
import com.qingniao.core.pojo.BrandExample;
import com.qingniao.core.service.BrandService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Transactional
@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	private BrandMapper brandMapper;
	@Autowired
	private JedisPool jedisPool;
	@Override
	public void insertBrand(Brand brand) {
		brandMapper.insertBrand(brand);
		//把数据保存到redis中
		Jedis jedis = jedisPool.getResource();
		jedis.hset("brand"+brand.getBrandId(), "brandId", brand.getBrandId().toString());
		jedis.hset("brand"+brand.getBrandId(), "name", brand.getBrandName());
		jedis.close();
		//数据保存到数据库中
		
		
	}
	public PageInfo selectByExample(BrandExample brandExample) {
		PageInfo pageInfo = new PageInfo(brandExample.getPageNo(), brandExample.getPageSize(), brandMapper.selectCountByExample(brandExample));
		brandExample.setPageNo(pageInfo.getPageNo());
		List<Brand> brandList = brandMapper.selectByExample(brandExample);
		pageInfo.setList(brandList);
		return pageInfo;
	}
	public Integer selectCountByExample(BrandExample brandExample) {
		return brandMapper.selectCountByExample(brandExample);
	}
	@Override
	public void batchDelete(Long[] ids) {
		//删除redis中的数据
		Jedis jedis = jedisPool.getResource();
		for (Long id : ids) {
			jedis.del("brand"+id);
		}
		jedis.close();
		brandMapper.batchDelete(ids);
	}
	@Override
	public Brand selectById(Long brandId) {
		return brandMapper.selectById(brandId);
	}
	@Override
	public void editBrand(Brand brand) {
		brandMapper.editBrand(brand);
		//修改后更新到redis
		Jedis jedis = jedisPool.getResource();
		jedis.hset("brand"+brand.getBrandId(), "brandId", brand.getBrandId().toString());
		jedis.hset("brand"+brand.getBrandId(), "brandName", brand.getBrandName());
		jedis.close();
	}
	@Override
	public List<Brand> selectAllByExample(BrandExample brandExample) {
		return brandMapper.selectByExample(brandExample);
	}

}
