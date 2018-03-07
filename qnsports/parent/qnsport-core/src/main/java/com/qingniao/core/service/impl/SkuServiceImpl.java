package com.qingniao.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingniao.core.dao.product.ColorMapper;
import com.qingniao.core.dao.product.ImgMapper;
import com.qingniao.core.dao.product.ProductMapper;
import com.qingniao.core.dao.product.SkuMapper;
import com.qingniao.core.pojo.product.Color;
import com.qingniao.core.pojo.product.Img;
import com.qingniao.core.pojo.product.ImgExample;
import com.qingniao.core.pojo.product.Product;
import com.qingniao.core.pojo.product.Sku;
import com.qingniao.core.pojo.product.SkuExample;
import com.qingniao.core.service.SkuService;
@Service
@Transactional
public class SkuServiceImpl implements SkuService {
	@Autowired
	private SkuMapper skuMapper;
	@Autowired
	private ColorMapper colorMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private ImgMapper imgMapper;

	public void insertSku(Sku sku) {
		skuMapper.insertSelective(sku);
	}

	public List<Sku> selectByProductId(Long productId) {
		SkuExample skuExample = new SkuExample();
		skuExample.createCriteria().andProductIdEqualTo(productId);
		List<Sku> skuList = skuMapper.selectByExample(skuExample);
		for (Sku sku : skuList) {
			Color color = colorMapper.selectByPrimaryKey(sku.getColorId());
			sku.setColor(color);
		}
		return skuList;
	}
	
	@Override
	public void updateSku(Sku sku) {
		sku.setUpdateTime(new Date());
		skuMapper.updateByPrimaryKeySelective(sku);
	}
	
	public Sku selectById(Long id) {
		return skuMapper.selectByPrimaryKey(id);
	}

	/**
	 * 
	 * 通过skuid初始化对象
	 * @see com.qingniao.core.service.SkuService#loadSkuById(java.lang.Long)
	 */
	public Sku loadSkuById(Long id) {
		Sku sku = skuMapper.selectByPrimaryKey(id);
		//关联颜色
		sku.setColor(colorMapper.selectByPrimaryKey(sku.getColorId()));
		//关联商品
		Product product = productMapper.selectByPrimaryKey(sku.getProductId());
		//关联图片
		ImgExample imgExample = new ImgExample();
		imgExample.createCriteria().andProductIdEqualTo(product.getId()).andIsDefEqualTo(false);
		List<Img> imgs = imgMapper.selectByExample(imgExample);
		product.setImg(imgs.get(0));
		sku.setProduct(product);
		return sku;
	}
}
