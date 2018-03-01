package com.qingniao.partal.coontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qingniao.common.page.PageInfo;
import com.qingniao.core.pojo.Brand;
import com.qingniao.core.pojo.product.Color;
import com.qingniao.core.pojo.product.Img;
import com.qingniao.core.pojo.product.Product;
import com.qingniao.core.pojo.product.ProductExample;
import com.qingniao.core.pojo.product.Sku;
import com.qingniao.core.service.ProductService;
import com.qingniao.core.service.SkuService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Controller
public class ProductController {
	@Autowired
	private SolrServer solrServer;
	@Autowired
	private JedisPool jedisPool;
	@Autowired
	private ProductService productService;
	@Autowired
	private SkuService skuService;
	@RequestMapping("index.html")
	public String productList(String pName, Integer pageNo, Long bId, String pPrice, Model model) throws SolrServerException {
		//品牌显示，从redis缓存中加载品牌并显示到页面
		List<Brand> brandList = new ArrayList<Brand>();
		Jedis jedis = jedisPool.getResource();
		Set<String> keys = jedis.keys("brand*");
		for (String key : keys) {
			Brand brand = new Brand();
			brand.setBrandId(Long.parseLong(jedis.hget(key, "brandId")));
			brand.setBrandName(jedis.hget(key, "brandName"));
			brandList.add(brand);
		}
		model.addAttribute("brandList", brandList);
		ProductExample productExample = new ProductExample();
		productExample.setPageSize(4);
		StringBuilder params = new StringBuilder();
		SolrQuery solrQuery = new SolrQuery();
		if (pageNo != null) {
			productExample.setPageNo(pageNo);
			model.addAttribute("pageNo", pageNo);
		} else {
			productExample.setPageNo(1);
		}
		if (pName != null && pName.trim().length() > 0) {
			solrQuery.set("q", "name_ik:"+pName);
			//高亮显示搜索关键字
			//开启高亮
			solrQuery.setHighlight(true);
			//设置高亮字段
			solrQuery.addHighlightField("name_ik");
			//设置高亮前缀
			solrQuery.setHighlightSimplePre("<span style='color:red'>");
			//设置高亮后缀
			solrQuery.setHighlightSimplePost("</span>");
			model.addAttribute("pName", pName);
			params.append("pName="+pName);
		} else {
			solrQuery.set("q", "*:*");
		}
		Boolean flag = false;
		Map conditionMap = new HashMap();
		if (bId != null) {
			solrQuery.addFilterQuery("brandId:"+bId);
			model.addAttribute("brandId", bId);
			params.append("&bId="+bId);
			conditionMap.put("品牌：", jedis.hget("brand"+bId, "brandName"));
			flag = true;
		}
		if (pPrice != null) {
			String[] pPrices = pPrice.split("-");
			if (pPrices.length == 2) {
				Float minPrice = new Float(pPrices[0]);
				Float maxPrice = new Float(pPrices[1]);
				solrQuery.addFilterQuery("price:[" +minPrice+ " TO " +maxPrice+ "]");
				conditionMap.put("价格：", pPrice);
				flag = true;
			} else {
				Float minPrice = new Float(600);
				Float maxPrice = new Float(99999999f);
				solrQuery.addFilterQuery("price:[" +minPrice+ " TO " +maxPrice+ "]");
				conditionMap.put("价格", "600以上");
				flag = true;
			}
			model.addAttribute("pPrice", pPrice);
			params.append("&pPrice="+pPrice);
		}
		model.addAttribute("flag", flag);
		model.addAttribute("conditionMap", conditionMap);
		solrQuery.setStart(productExample.getStartRow());//设置起始页
		solrQuery.setRows(productExample.getPageSize());//每页查询数据条数
		solrQuery.addSort("price", ORDER.asc);//按价格升序排列
		//从solr服务器中查询出商品
		QueryResponse queryResponse = solrServer.query(solrQuery);
		SolrDocumentList docs = queryResponse.getResults();
		//循环遍历把数据封装到list中 
		List<Product> productList = new ArrayList<Product>();
		for (SolrDocument doc : docs) {
			Product product = new Product();
			String id = (String)doc.get("id");
			product.setId(Long.parseLong(id));
			if (pName != null && pName.trim().length() > 0) {
				Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
				Map<String, List<String>> map = highlighting.get(id);
				List<String> list = map.get("name_ik");
				String name = list.get(0);
				product.setName(name);
			} else {
				String name = (String)doc.get("name_ik");
				product.setName(name);
			}
			String url = (String)doc.get("url");
			Img img = new Img();
			img.setUrl(url);
			product.setImg(img);
			Integer brandId = (Integer)doc.get("brandId");
			product.setBrandId(Long.parseLong(brandId.toString()));
			float price = (float)doc.get("price");
			product.setMinPrice(price);
			productList.add(product);
		}
		PageInfo pageInfo = new PageInfo(productExample.getPageNo(), productExample.getPageSize(), (int)docs.getNumFound());
		pageInfo.setList(productList);
		String url = "/index.html";
		pageInfo.pageView(url, params.toString());
		model.addAttribute("pageInfo", pageInfo);
		return "product/product";
	}
	@RequestMapping("/product/productDetail.html")
	public String productDetail(Long id, Model model) {
		//加载商品和图片
		Product product = productService.selectByPrimaryKey(id);
		model.addAttribute("product", product);
		//加载商品对应的sku
		List<Sku> skuList = skuService.selectByProductId(id);
		model.addAttribute("skuList", skuList);
		//颜色
		Set<Color> colorList = new HashSet<Color>();
		for (Sku sku : skuList) {
			colorList.add(sku.getColor());
		}
		model.addAttribute("colorList", colorList);
		return "product/productDetail";
	}
}
