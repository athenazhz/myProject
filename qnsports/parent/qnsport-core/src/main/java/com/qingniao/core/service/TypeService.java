package com.qingniao.core.service;

import java.util.List;

import com.qingniao.core.pojo.product.Type;
import com.qingniao.core.pojo.product.TypeExample;


public interface TypeService {
	public List<Type> selectTypeByExample(TypeExample typeExample);
}
