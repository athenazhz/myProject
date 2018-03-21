package com.zhang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybatis.dao.KindsMapper;
import com.mybatis.pojo.Kinds;
import com.mybatis.pojo.KindsExample;
import com.zhang.service.KindsService;
@Service
public class KindsServiceImpl implements KindsService {
	@Autowired
	KindsMapper kindsMapper;
	//全查所有类别
	public List<Kinds> getAllKinds(){
		KindsExample kindsExample=new KindsExample();
		return kindsMapper.selectByExample(kindsExample);
	}
}
