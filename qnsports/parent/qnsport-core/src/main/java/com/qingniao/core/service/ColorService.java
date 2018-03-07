package com.qingniao.core.service;

import java.util.List;

import com.qingniao.core.pojo.product.Color;
import com.qingniao.core.pojo.product.ColorExample;

public interface ColorService {
	public List<Color> selectColorByExample(ColorExample colorExample);
}
