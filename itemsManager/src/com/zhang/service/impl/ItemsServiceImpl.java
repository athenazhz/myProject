package com.zhang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybatis.dao.ItemsMapper;
import com.mybatis.pojo.Items;
import com.mybatis.pojo.ItemsExample;
import com.zhang.service.ItemsService;
@Service
public class ItemsServiceImpl implements ItemsService {
	@Autowired
	ItemsMapper itemsDao;
	//模糊查询
	public List<Items> vagueQuery(ItemsExample example) {
		return itemsDao.vagueQuery(example);
	}
	//新增
	public int add(Items item){
		return itemsDao.insert(item);
	}
	//单一查询
	public Items getOneItem(Integer iId){
		return itemsDao.selectByPrimaryKey(iId);
	}
	//修改
	public int edit(Items item){
		return itemsDao.updateByPrimaryKey(item);
	}
	//删除
	public int delete(ItemsExample example){
		return itemsDao.deleteByExample(example);
	}
}
