package com.ways.app.basePeriodTPConfig.model;

import java.io.Serializable;


/**
 * 常用对象管理
 * 
 * @author jacky.li
 * 201608017
 */
public class BasePeriodTP implements Serializable{

	private static final long serialVersionUID = -3358204100837872931L;
	/**
	 * 型号编号
	 */
	private String versionCode;
    /**
     * 日期
     */
	private String year;
	/**
	 * 厂商
	 */
	private String manf;
	/**
	 * 车型
	 */
	private String model;
	/**
	 * 车型名称
	 */
	private String modelName;
	/**
	 * 城市ID
	 */
	private String cityId;
	/**
	 * 城市名称
	 */
	private String cityName;
	/**
	 * 指导价
	 */
	private String MSRP;
	/**
	 * TP
	 */
	private String TP;
	/**
	 * 总条数
	 */
	private Integer totalCount;
	
	
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
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
	public String getManf() {
		return manf;
	}
	public void setManf(String manf) {
		this.manf = manf;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getMSRP() {
		return MSRP;
	}
	public void setMSRP(String mSRP) {
		MSRP = mSRP;
	}
	public String getTP() {
		return TP;
	}
	public void setTP(String TP) {
		this.TP = TP;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
}
