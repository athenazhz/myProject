package com.qingniao.core.pojo;

public class BrandExample {
	private long brandId;//主键
	private String brandName;
	private String brandDescription;
	private String brandUrl;
	private String brandLogo;
	private Integer brandStatus;//1在售 2停售 3删除
	private Integer pageNo;
	private Integer pageSize = 3;
	private Integer startRow;
	
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		startRow = (pageNo-1) * pageSize;
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		startRow = (pageNo-1) * pageSize;
		this.pageSize = pageSize;
	}
	public Integer getStartRow() {
		return startRow;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	public long getBrandId() {
		return brandId;
	}
	public void setBrandId(long brandId) {
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
	
}
