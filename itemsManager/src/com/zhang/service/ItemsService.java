package com.zhang.service;

import java.util.List;

import com.mybatis.pojo.Items;
import com.mybatis.pojo.ItemsExample;

public interface ItemsService {
	//模糊查询
	public List<Items> vagueQuery(ItemsExample example);
	//新增
	public int add(Items item);
	//单一查询
	public Items getOneItem(Integer iId);
	//修改
	public int edit(Items item);
	//删除
	public int delete(ItemsExample example);
}
