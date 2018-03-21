package com.mybatis.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Items {
    private Integer iId;

    private Integer cId;

    private String iName;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date inTime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date outTime;

    private Double iPrice;

    private Integer state;

    private String url;
    
    private Kinds kinds;
    

    public Kinds getKinds() {
		return kinds;
	}

	public void setKinds(Kinds kinds) {
		this.kinds = kinds;
	}

	public Integer getiId() {
        return iId;
    }

    public void setiId(Integer iId) {
        this.iId = iId;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName == null ? null : iName.trim();
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Double getiPrice() {
        return iPrice;
    }

    public void setiPrice(Double iPrice) {
        this.iPrice = iPrice;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}