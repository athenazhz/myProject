package com.qingniao.core.service;

import java.util.List;

import com.qingniao.core.pojo.product.Feature;
import com.qingniao.core.pojo.product.FeatureExample;

public interface FeatureService {
	public List<Feature> selectFeatureByExample(FeatureExample featureExample);
}
