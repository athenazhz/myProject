package com.qingniao.core.pojo;

import com.qingniao.core.utils.SERVER_URL;

public class Brand {
	private Long brandId;//主键
	private String brandName;
	private String brandDescription;
	private String brandUrl;
	private String brandLogo;
	private Integer brandStatus;//1在售 2停售 3删除
	
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandDescription() {
		return brandDescription;
	}
	public void setBrandDescription(String brandDescription) {
		this.brandDescription = brandDescription;
	}
	public String getBrandUrl() {
		return brandUrl;
	}
	public void setBrandUrl(String brandUrl) {
		this.brandUrl = brandUrl;
	}
	public String getBrandLogo() {
		return brandLogo;
	}
	public void setBrandLogo(String brandLogo) {
		this.brandLogo = brandLogo;
	}
	public Integer getBrandStatus() {
		return brandStatus;
	}
	public void setBrandStatus(Integer brandStatus) {
		this.brandStatus = brandStatus;
	}
	public String getImgUrl() {
		return SERVER_URL.IMG_URL+brandLogo;
	}
}
