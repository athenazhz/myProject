package com.qingniao.core.service;

import java.util.List;

import com.qingniao.core.pojo.product.Sku;
import com.qingniao.core.pojo.product.SkuExample;

public interface SkuService {
	public void insertSku(Sku sku);
	
	public List<Sku> selectByProductId(Long productId);
	
	public void updateSku(Sku sku);
	
	public Sku selectById(Long id);

	public Sku loadSkuById(Long id);
}
