package com.qingniao.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingniao.core.dao.product.TypeMapper;
import com.qingniao.core.pojo.product.Type;
import com.qingniao.core.pojo.product.TypeExample;
import com.qingniao.core.service.TypeService;
@Service
@Transactional
public class TypeServiceImpl implements TypeService {
	@Autowired
	private TypeMapper typeMapper;

	//查询所有商品类别
	public List<Type> selectTypeByExample(TypeExample typeExample) {
		return typeMapper.selectByExample(typeExample);
	}
}
