package com.qingniao.core.service.impl;

import java.security.KeyStore.PrivateKeyEntry;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingniao.core.dao.product.ColorMapper;
import com.qingniao.core.pojo.product.Color;
import com.qingniao.core.pojo.product.ColorExample;
import com.qingniao.core.service.ColorService;
@Service
@Transactional
public class ColorServiceImpl implements ColorService {
	@Autowired
	private ColorMapper colorMapper;

	public List<Color> selectColorByExample(ColorExample colorExample) {
		return colorMapper.selectByExample(colorExample);
	}
}
