package com.ways.app.sale.model;

import java.io.Serializable;

public class SaleDetail implements Serializable{
	private static final long serialVersionUID = -8430896377577231974L;
	
	/**
	 * 上市时间
	 */
	private String launchDate;
	/**
	 * 型号编码
	 */
	private String versionCode;
	/**
	 * 年
	 */
	private String year;
	/**
	 * 月
	 */
	private String month;
	/**
	 * 型号全称中文名称
	 */
	private String versionName;
	/**
	 * 型号销量
	 */
	private Double sale;
	/**
	 * 生产商中文名称
	 */
	private String manfName;
	/**
	 * 车型中文名称
	 */
	private String modelName;
	/**
	 * 品牌中文名称
	 */
	private String brandName;
	/**
	 * 销量说明
	 */
	private String remark;

	/**
	 * 是否自加销量
	 */
	private String isCustom;
	/**
	 * 指导价
	 */
	private String MSRP;
	
	private int totalCount;
	
	public String getLaunchDate() {
		return launchDate;
	}
	public void setLaunchDate(String launchDate) {
		this.launchDate = launchDate;
	}
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public Double getSale() {
		return sale;
	}
	public void setSale(Double sale) {
		this.sale = sale;
	}
	public String getManfName() {
		return manfName;
	}
	public void setManfName(String manfName) {
		this.manfName = manfName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsCustom() {
		return isCustom;
	}
	public void setIsCustom(String isCustom) {
		this.isCustom = isCustom;
	}
	public String getMSRP() {
		return MSRP;
	}
	public void setMSRP(String mSRP) {
		MSRP = mSRP;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
}
