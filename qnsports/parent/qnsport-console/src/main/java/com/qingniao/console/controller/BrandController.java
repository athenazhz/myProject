package com.qingniao.console.controller;


import javax.jws.WebParam.Mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qingniao.common.page.PageInfo;
import com.qingniao.core.pojo.Brand;
import com.qingniao.core.pojo.BrandExample;
import com.qingniao.core.service.BrandService;

@Controller
public class BrandController {
	@Autowired
	private BrandService brandService;
	@RequestMapping("/brand/list.do") 
	public String brandList(BrandExample brandExample, Model model) {
		String url = "/brand/list.do";
		StringBuilder params = new StringBuilder();
		if (brandExample.getPageNo() == null) {
			brandExample.setPageNo(1);
		}
		if (brandExample.getBrandStatus() == null) {
			brandExample.setBrandStatus(1);
			params.append("brandStatus="+brandExample.getBrandStatus());
		}
		if (brandExample.getBrandName() != null) {
			brandExample.setBrandName(brandExample.getBrandName().trim());
			params.append("&brandName="+brandExample.getBrandName());
		}
		PageInfo pageInfo = brandService.selectByExample(brandExample);
		pageInfo.pageView(url, params.toString());
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("brandExample", brandExample);
		return "brand/list";
	}
	//跳转添加页面
	@RequestMapping("/brand/toadd.do")
	public String toBrandAdd() {
		return "brand/add";
	}
	//添加品牌
	@RequestMapping("/brand/add.do")
	public String brandAdd(Brand brand) {
		brandService.insertBrand(brand);
		return "redirect:/brand/list.do";
	}
	@RequestMapping("/brand/batchDelete.do")
	public String batchDelete(Long[] ids, BrandExample brandExample, Model model) {
		if (brandExample.getPageNo() != null) {
			model.addAttribute("pageNo", brandExample.getPageNo());
		}
		if (brandExample.getBrandName() != null) {
			model.addAttribute("brandName", brandExample.getBrandName());
		}
		if (brandExample.getBrandStatus() != null) {
			model.addAttribute("brandStatus", brandExample.getBrandStatus());
		}
		brandService.batchDelete(ids);
		return "redirect:/brand/list.do";
	}
	@RequestMapping("/brand/toedit.do")
	public String toEdit(Long brandId, Model model) {
		Brand brand = brandService.selectById(brandId);
		model.addAttribute("brand", brand);
		return "brand/edit";
	}
	@RequestMapping("/brand/editSave.do")
	public String edit(Brand brand) {
		brandService.editBrand(brand);
		return "redirect:/brand/list.do";
	}
}
