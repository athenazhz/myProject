package com.qingniao.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingniao.core.dao.product.ImgMapper;
import com.qingniao.core.pojo.product.Img;
import com.qingniao.core.service.ImgService;
@Service
@Transactional
public class ImgServiceImpl implements ImgService {
	@Autowired
	private ImgMapper imgMapper;

	@Override
	public void insertImg(Img img) {
		imgMapper.insertSelective(img);		
	}
}
