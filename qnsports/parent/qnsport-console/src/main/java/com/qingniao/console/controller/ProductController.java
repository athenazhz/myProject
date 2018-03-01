package com.qingniao.console.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qingniao.common.page.PageInfo;
import com.qingniao.core.pojo.Brand;
import com.qingniao.core.pojo.BrandExample;
import com.qingniao.core.pojo.product.Color;
import com.qingniao.core.pojo.product.ColorExample;
import com.qingniao.core.pojo.product.Feature;
import com.qingniao.core.pojo.product.FeatureExample;
import com.qingniao.core.pojo.product.Img;
import com.qingniao.core.pojo.product.Product;
import com.qingniao.core.pojo.product.ProductExample;
import com.qingniao.core.pojo.product.ProductExample.Criteria;
import com.qingniao.core.pojo.product.Sku;
import com.qingniao.core.pojo.product.Type;
import com.qingniao.core.pojo.product.TypeExample;
import com.qingniao.core.service.BrandService;
import com.qingniao.core.service.ColorService;
import com.qingniao.core.service.FeatureService;
import com.qingniao.core.service.ImgService;
import com.qingniao.core.service.ProductService;
import com.qingniao.core.service.SkuService;
import com.qingniao.core.service.TypeService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Controller
public class ProductController {
	@Autowired
	private TypeService typeService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private FeatureService featureService;
	@Autowired
	private ColorService colorService;
	@Autowired
	private ProductService productService;
	@Autowired
	private SkuService skuService;
	@Autowired
	private ImgService imgService;
	@Autowired
	private JedisPool jedisPool;
	
	@RequestMapping("/product/list.do")
	public String productList(String name, Long brandId, Boolean isShow, Integer pageNo, Model model) {
		//加载品牌数据
		BrandExample brandExample = new BrandExample();
		brandExample.setBrandStatus(1);
		List<Brand> brandList = brandService.selectAllByExample(brandExample);
		model.addAttribute("brandList", brandList);
		//封装查询条件并制作分页工具栏
		StringBuilder stringBuilder = new StringBuilder();
		ProductExample productExample = new ProductExample();
		Criteria productcriteria = productExample.createCriteria();
		if (isShow != null) {
			productcriteria.andIsShowEqualTo(isShow);
			stringBuilder.append("isShow="+isShow);
		} else {
			productcriteria.andIsShowEqualTo(false);
			stringBuilder.append("isShow=false");
		}
		model.addAttribute("isShow", isShow);
		if (name != null && name.trim().length() != 0) {
			productcriteria.andNameLike("%"+name.trim()+"%");
			stringBuilder.append("&name="+name.trim());
			model.addAttribute("name", name);
		}
		if (brandId != null) {
			productcriteria.andBrandIdEqualTo(brandId);
			stringBuilder.append("&brandId="+brandId);
			model.addAttribute("brandId", brandId);
		}
		if (pageNo != null) {
			productExample.setPageNo(pageNo);
			model.addAttribute("pageNo", pageNo);
		}
		//查询
		PageInfo pageInfo = productService.selectByExample(productExample);
		//制作分页工具栏
		String url = "/product/list.do";
		pageInfo.pageView(url, stringBuilder.toString());
		model.addAttribute("pageInfo", pageInfo);
		return "product/list";
	}
	@RequestMapping("/product/toadd.do")
	public String toAdd(Model model) {
		//初始化商品类型
		TypeExample typeExample = new TypeExample();
		typeExample.createCriteria().andParentIdNotEqualTo(0l);
		List<Type> typeList = typeService.selectTypeByExample(typeExample);
		model.addAttribute("typeList", typeList);
		//初始化商品品牌
		BrandExample brandExample = new BrandExample();
		brandExample.setBrandStatus(1);
		List<Brand> brandList = brandService.selectAllByExample(brandExample);
		model.addAttribute("brandList", brandList);
		//初始化颜色
		ColorExample colorExample = new ColorExample();
		colorExample.createCriteria().andParentIdNotEqualTo(0l);
		List<Color> colorList = colorService.selectColorByExample(colorExample);
		model.addAttribute("colorList", colorList);
		//初始化材质
		FeatureExample featureExample = new FeatureExample();
		featureExample.createCriteria().andIsDelEqualTo(true);
		List<Feature> featureList = featureService.selectFeatureByExample(featureExample);
		model.addAttribute("featureList", featureList);
		return "product/add";
	}
	
	@RequestMapping("/product/save.do")
	public String productAdd(Product product) {
		Jedis jedis = jedisPool.getResource();
		Long pId = jedis.incr("productId");
		jedis.close();
		product.setId(pId);
		product.setIsDel(false);
		product.setIsShow(false);
		product.setCreateTime(new Date());
		product.setCheckTime(new Date());
		productService.insertProduct(product);
		Img img = product.getImg();
		img.setIsDef(false);
		img.setProductId(pId);
		imgService.insertImg(img);
		String colorIds = product.getColors();
		String sizeIds = product.getSizes();
		for (String  colorId : colorIds.split(",")) {
			Sku sku = new Sku();
			sku.setProductId(pId);
			sku.setColorId(Long.parseLong(colorId));
			sku.setCreateTime(new Date());
			for (String sizeId : sizeIds.split(",")) {
				//尺码
				sku.setSize(sizeId);
				//运费
				sku.setDeliveFee(10f);
				//市场价格
				sku.setMarketPrice(150f);
				//售价
				sku.setPrice(99f);
				//库存
				sku.setStock(100);
				//购买限制
				sku.setUpperLimit(100);
				skuService.insertSku(sku);
			}
		}
		return "redirect:/product/list.do";
	}
	
	@RequestMapping("/product/onSale.do")
	public String onSale(Long[] ids) {
		productService.onSale(ids);
		return "redirect:/product/list.do";
	}
	@RequestMapping("/product/sellOut.do")
	public String sellOut(Long[] ids) {
		productService.sellOut(ids);
		return "redirect:/product/list.do";
	}
}
