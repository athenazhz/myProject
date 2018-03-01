package com.qingniao.console.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingniao.core.pojo.product.Sku;
import com.qingniao.core.pojo.product.SkuExample;
import com.qingniao.core.service.SkuService;

@Controller
public class SkuController {
	@Autowired
	private SkuService skuService;
	@RequestMapping("/sku/list.do")
	public String skuList(Long productId, Model model) {
		List<Sku> skuList = skuService.selectByProductId(productId);
		model.addAttribute("skuList", skuList);
		return "sku/list";
	}
	@ResponseBody
	@RequestMapping("/sku/save.do")
	public String skuSave(Sku sku) {
		skuService.updateSku(sku);
		JSONObject json = new JSONObject();
		json.put("message", "保存成功！");
		return json.toString();
	}
}
