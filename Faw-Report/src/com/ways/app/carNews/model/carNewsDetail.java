package com.ways.app.carNews.model;

import java.io.Serializable;

public class carNewsDetail implements Serializable{
	private static final long serialVersionUID = -8430896377577231974L;
	
	/**
	 * 型号编码
	 */
	private String versionCode;
	/**
	 * 上市时间
	 */
	private String launchDate;
	/**
	 * 细分市场中文名称
	 */
	private String segment;

	/**
	 * 生产商中文名称
	 */
	private String manfName;
	/**
	 * 生产商英文名称
	 */
	private String manfNameEn;
	/**
	 * 生产商中文名称
	 */
	private String trim;
	/**
	 * 生产商英文名称
	 */
	private String trimEn;
	/**
	 * 品牌中文名称
	 */
	private String brandName;
	/**
	 * 品牌英文名称
	 */
	private String brandNameEn;
	/**
	 * 指导价
	 */
	private String MSRP;
	/**
	 * 上一代指导价
	 */
	private String preMSRP;
	/**
	 * 排量
	 */
	private String PL;
	/**
	 * 车型中文名称
	 */
	private String modelName;
	/**
	 * 车型英文名称
	 */
	private String modelNameEn;
	/**
	 * 型号全称中文名称
	 */
	private String versionName;
	/**
	 * 型号全称英文名称
	 */
	private String versionNameEn;
	/**
	 * 上一代型号全称中文名称
	 */
	private String preVersionName;
	/**
	 * 车身形式
	 */
	private String bodyType;
	/**
	 * 车身形式英文
	 */
	private String bodyTypeEn;
	/**
	 * 驱动形式
	 */
	private String driveType;
	/**
	 * 驱动形式英文
	 */
	private String driveTypeEn;
	/**
	 * 国产、合资、出口
	 */
	private String carIn;
	/**
	 * 国产、合资、出口 英文
	 */
	private String carInEn;
	/**
	 * 变化描述
	 */
	private String changeDescription;
	/**
	 * 变化描述英文
	 */
	private String changeDescriptionEn;
	/**
	 * 长/宽/高
	 */
	private String LWH;
	/**
	 * 轴距
	 */
	private String wheelbase;
	/**
	 * 变速箱
	 */
	private String transmission;
	/**
	 * 最大功率/转速
	 */
	private String maximumPower;
	/**
	 * 最大扭矩/转速
	 */
	private String maximumTorque;
	/**
	 * 升功率
	 */
	private String KWL;
	/**
	 * 产品卖点
	 */
	private String sellingPoints;
	/**
	 * 条数
	 */
	private int totalCount;
	
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public String getLaunchDate() {
		return launchDate;
	}
	public void setLaunchDate(String launchDate) {
		this.launchDate = launchDate;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public String getManfName() {
		return manfName;
	}
	public void setManfName(String manfName) {
		this.manfName = manfName;
	}
	public String getManfNameEn() {
		return manfNameEn;
	}
	public void setManfNameEn(String manfNameEn) {
		this.manfNameEn = manfNameEn;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandNameEn() {
		return brandNameEn;
	}
	public void setBrandNameEn(String brandNameEn) {
		this.brandNameEn = brandNameEn;
	}
	public String getPL() {
		return PL;
	}
	public void setPL(String pL) {
		PL = pL;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelNameEn() {
		return modelNameEn;
	}
	public void setModelNameEn(String modelNameEn) {
		this.modelNameEn = modelNameEn;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getVersionNameEn() {
		return versionNameEn;
	}
	public void setVersionNameEn(String versionNameEn) {
		this.versionNameEn = versionNameEn;
	}
	public String getPreVersionName() {
		return preVersionName;
	}
	public void setPreVersionName(String preVersionName) {
		this.preVersionName = preVersionName;
	}
	public String getBodyType() {
		return bodyType;
	}
	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}
	public String getBodyTypeEn() {
		return bodyTypeEn;
	}
	public void setBodyTypeEn(String bodyTypeEn) {
		this.bodyTypeEn = bodyTypeEn;
	}
	public String getDriveType() {
		return driveType;
	}
	public void setDriveType(String driveType) {
		this.driveType = driveType;
	}
	public String getDriveTypeEn() {
		return driveTypeEn;
	}
	public void setDriveTypeEn(String driveTypeEn) {
		this.driveTypeEn = driveTypeEn;
	}
	public String getCarIn() {
		return carIn;
	}
	public void setCarIn(String carIn) {
		this.carIn = carIn;
	}
	public String getCarInEn() {
		return carInEn;
	}
	public void setCarInEn(String carInEn) {
		this.carInEn = carInEn;
	}
	public String getChangeDescription() {
		return changeDescription;
	}
	public void setChangeDescription(String changeDescription) {
		this.changeDescription = changeDescription;
	}
	public String getChangeDescriptionEn() {
		return changeDescriptionEn;
	}
	public void setChangeDescriptionEn(String changeDescriptionEn) {
		this.changeDescriptionEn = changeDescriptionEn;
	}
	public String getLWH() {
		return LWH;
	}
	public void setLWH(String lWH) {
		LWH = lWH;
	}
	public String getWheelbase() {
		return wheelbase;
	}
	public void setWheelbase(String wheelbase) {
		this.wheelbase = wheelbase;
	}
	public String getTransmission() {
		return transmission;
	}
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	public String getMaximumPower() {
		return maximumPower;
	}
	public void setMaximumPower(String maximumPower) {
		this.maximumPower = maximumPower;
	}
	public String getMaximumTorque() {
		return maximumTorque;
	}
	public void setMaximumTorque(String maximumTorque) {
		this.maximumTorque = maximumTorque;
	}
	public String getKWL() {
		return KWL;
	}
	public void setKWL(String kWL) {
		KWL = kWL;
	}
	public String getSellingPoints() {
		return sellingPoints;
	}
	public void setSellingPoints(String sellingPoints) {
		this.sellingPoints = sellingPoints;
	}
	public String getTrim() {
		return trim;
	}
	public void setTrim(String trim) {
		this.trim = trim;
	}
	public String getTrimEn() {
		return trimEn;
	}
	public void setTrimEn(String trimEn) {
		this.trimEn = trimEn;
	}
	public String getMSRP() {
		return MSRP;
	}
	public void setMSRP(String mSRP) {
		MSRP = mSRP;
	}
	public String getPreMSRP() {
		return preMSRP;
	}
	public void setPreMSRP(String preMSRP) {
		this.preMSRP = preMSRP;
	}
	
}
