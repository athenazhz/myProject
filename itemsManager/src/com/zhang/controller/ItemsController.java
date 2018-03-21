package com.zhang.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybatis.pojo.Items;
import com.mybatis.pojo.ItemsExample;
import com.mybatis.pojo.ItemsExample.Criteria;
import com.mybatis.pojo.Kinds;
import com.zhang.service.ItemsService;
import com.zhang.service.KindsService;

@Controller
@RequestMapping("ItemsController")
public class ItemsController {
	@Autowired
	ItemsService is;
	@Autowired
	KindsService ks;
	//进入页面
	@ResponseBody
	@RequestMapping(value = "getList", produces = "application/json;charset=utf-8")
	public String getList(@RequestParam(value = "page", defaultValue = "1", required = false) int pageNum,
			@RequestParam(value = "cId", defaultValue = "0", required = false) Integer cId,
			@RequestParam(value = "iName", defaultValue = "", required = false) String iName,
			@RequestParam(value = "minPrice", defaultValue = "0", required = false) Double minPrice,
			@RequestParam(value = "maxPrice", defaultValue = "100000", required = false) Double maxPrice) {
		JSONObject json = new JSONObject();
		ItemsExample vagueQueryExample = new ItemsExample();
		vagueQueryExample.setOrderByClause("i.i_id asc");
		Criteria vagueQueryCriteria = vagueQueryExample.createCriteria();
		if (cId != 0){
			vagueQueryCriteria.andCIdEqualTo(cId);
		}	
		vagueQueryCriteria.andINameLike("%" + iName + "%");
		vagueQueryCriteria.andIPriceBetween(minPrice, maxPrice);
		int pageSize = 3;
		PageHelper.startPage(pageNum, pageSize);
		List<Items> list = is.vagueQuery(vagueQueryExample);
		PageInfo pageInfo = new PageInfo(list);
		List<Kinds> kList = ks.getAllKinds();
		json.put("pageInfo", pageInfo);
		json.put("kList", kList);
		json.put("cId", cId);
		return json.toJSONStringWithDateFormat(json, "yyyy-MM-dd");
	}
	//新增
	@RequestMapping("add")
	public ModelAndView add(ModelAndView mav,Items item){
		is.add(item);
		mav.setViewName("redirect:/index.jsp");
		return mav;
	}
	//跳转到新增页面
	@RequestMapping("switchAdd")
	public ModelAndView switchAdd(ModelAndView mav){
		List<Kinds> kList=ks.getAllKinds();
		mav.addObject("kList", kList);
		mav.setViewName("add");
		return mav;
	}
	//上传图片
	@ResponseBody
	@RequestMapping("upload")
	public String upload(@RequestParam("newFile") MultipartFile newFile,HttpServletRequest req) throws IllegalStateException, IOException{
		String realPath=req.getSession().getServletContext().getRealPath("/upload");
		String oriName=newFile.getOriginalFilename();
		File file=new File(realPath);
		if(!file.exists()){
			file.mkdirs();
		}
		File finalFile=new File(realPath, oriName);
		newFile.transferTo(finalFile);
		JSONObject json=new JSONObject();
		json.put("url", "/upload/"+oriName);
		return json.toJSONString();
	}
	//跳转修改页面
	@RequestMapping("switchDetail")
	public ModelAndView switchDetail(ModelAndView mav,@RequestParam("iId") Integer iId){
		Items item=is.getOneItem(iId);
		List<Kinds> kList=ks.getAllKinds();
		mav.addObject("item", item);
		mav.addObject("kList", kList);
		mav.setViewName("detail");
		return mav;
	}
	//修改
	@RequestMapping("edit")
	public ModelAndView edit(ModelAndView mav,Items item){
		is.edit(item);
		mav.setViewName("redirect:/index.jsp");
		return mav;
	}
	//删除
	@RequestMapping("del")
	public ModelAndView del(ModelAndView mav,@RequestParam("iId") String iIdwords){
		ItemsExample deleteExample=new ItemsExample();
		String[] ids=iIdwords.split(",");
		List<Integer> idList=new ArrayList<Integer>();
		for (String id : ids) {
			Integer iId=new Integer(id);
			idList.add(iId);
		}
		Criteria deleteCri=deleteExample.createCriteria();
		deleteCri.andIIdIn(idList);
		is.delete(deleteExample);
		mav.setViewName("redirect:/index.jsp");
		return mav;
	}
}
