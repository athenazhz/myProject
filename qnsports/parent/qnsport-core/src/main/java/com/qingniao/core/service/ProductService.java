package com.qingniao.core.service;

import java.util.List;

import com.qingniao.common.page.PageInfo;
import com.qingniao.core.pojo.product.Product;
import com.qingniao.core.pojo.product.ProductExample;

public interface ProductService {
	public void insertProduct(Product product);

	public PageInfo selectByExample(ProductExample productExample);
	
	public void onSale(Long[] ids);
	
	public void sellOut(Long[] ids);
	
	public Product selectByPrimaryKey(Long id);
}
