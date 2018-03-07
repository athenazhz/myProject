package com.qingniao.core.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingniao.common.page.PageInfo;
import com.qingniao.core.dao.product.ImgMapper;
import com.qingniao.core.dao.product.ProductMapper;
import com.qingniao.core.dao.product.SkuMapper;
import com.qingniao.core.pojo.product.Color;
import com.qingniao.core.pojo.product.Img;
import com.qingniao.core.pojo.product.ImgExample;
import com.qingniao.core.pojo.product.Product;
import com.qingniao.core.pojo.product.ProductExample;
import com.qingniao.core.pojo.product.Sku;
import com.qingniao.core.pojo.product.SkuExample;
import com.qingniao.core.service.ProductService;
import com.qingniao.core.service.SkuService;
import com.qingniao.core.utils.FreeMarkerUtil;
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ImgMapper imgMapper;
	@Autowired
	private SkuMapper skuMapper;
	@Autowired
	private SkuService skuService;
	@Autowired
	private SolrServer solrServer;
	@Autowired
	private FreeMarkerUtil freeMarkerUtil;
	public void insertProduct(Product product) {
		productMapper.insertSelective(product);
	}
	@Override
	public PageInfo selectByExample(ProductExample productExample) {
		PageInfo pageInfo = new PageInfo(productExample.getPageNo(),productExample.getPageSize(),productMapper.countByExample(productExample));
		productExample.setPageNo(pageInfo.getPageNo());
		List<Product> productList = productMapper.selectByExample(productExample);
		for (Product product : productList) {
			ImgExample imgExample = new ImgExample();
			imgExample.createCriteria().andProductIdEqualTo(product.getId()).andIsDefEqualTo(false);
			List<Img> imgList = imgMapper.selectByExample(imgExample);
			if (imgList.size()>0) {
				product.setImg(imgList.get(0));
			}
		}
		pageInfo.setList(productList);
		return pageInfo;
	}
	@Override
	public void onSale(Long[] ids) {
		List<Long> idList = new ArrayList<Long>();
		for (Long id : ids) {
			idList.add(id);
			Product p = productMapper.selectByPrimaryKey(id);
			SolrInputDocument document = new SolrInputDocument();
			//要将product的id，name，图片路径，价格，品牌id存入solr
			document.setField("id", p.getId());
			document.setField("name_ik", p.getName());
			ImgExample imgExample = new ImgExample();
			imgExample.createCriteria().andProductIdEqualTo(id).andIsDefEqualTo(false);
			List<Img> imgs = imgMapper.selectByExample(imgExample);
			document.setField("url", imgs.get(0).getUrl());
			float price = skuMapper.selectMinPrice(id);
			document.setField("price", price);
			//这里要把brandId加入到schema.xml中，不然solr里面没有这个字段，上面的几个字段在xml已经存在，所以不用添加
			document.setField("brandId", p.getBrandId());
			try {
				solrServer.add(document);
				solrServer.commit();
			} catch (SolrServerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//生成静态页面
			//1.生成静态页面需要的数据
			Map root = new HashMap();
			root.put("productId", id);
			Product product = selectByPrimaryKey(id);
			List<Sku> skus = skuService.selectByProductId(id);
			Set<Color> colorList = new HashSet<Color>();
			for (Sku sku : skus) {
				colorList.add(sku.getColor());
			}
			root.put("skus", skus);
			root.put("colors", colorList);
			root.put("product", product);
			//2.调用工具类生成
			freeMarkerUtil.htmlGenerator(root);
		}
		
		//更新数据库
		ProductExample productExample = new ProductExample();
		Product product = new Product();
		product.setIsShow(true);
		productExample.createCriteria().andIdIn(idList);
		productMapper.updateByExampleSelective(product, productExample);
	}
	
	public void sellOut(Long[] ids) {
		List<Long> idList = new ArrayList<Long>();
		for (Long id : ids) {
			idList.add(id);
			try {
				solrServer.deleteById(""+id+"");
				solrServer.commit();
			} catch (SolrServerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ProductExample productExample = new ProductExample();
		Product product = new Product();
		product.setIsShow(false);
		productExample.createCriteria().andIdIn(idList);
		productMapper.updateByExampleSelective(product, productExample);
	}
	
	public Product selectByPrimaryKey(Long id) {
		Product product = productMapper.selectByPrimaryKey(id);
		ImgExample imgExample = new ImgExample();
		imgExample.createCriteria().andProductIdEqualTo(id).andIsDefEqualTo(false);
		List<Img> imgs = imgMapper.selectByExample(imgExample);
		product.setImg(imgs.get(0));
		return product;
	}
}
