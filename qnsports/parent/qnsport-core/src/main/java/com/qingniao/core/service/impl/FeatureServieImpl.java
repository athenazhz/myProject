package com.qingniao.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingniao.core.dao.product.FeatureMapper;
import com.qingniao.core.pojo.product.Feature;
import com.qingniao.core.pojo.product.FeatureExample;
import com.qingniao.core.service.FeatureService;
@Service
@Transactional
public class FeatureServieImpl implements FeatureService {
	@Autowired
	private FeatureMapper featureMapper;
	@Override
	public List<Feature> selectFeatureByExample(FeatureExample featureExample) {
		return featureMapper.selectByExample(featureExample);
	}

}
