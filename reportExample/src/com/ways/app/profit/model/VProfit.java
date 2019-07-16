package com.ways.app.profit.model;

import java.io.Serializable;

/**
 * 型号利润
 */
public class VProfit implements Serializable{

	private static final long serialVersionUID = 4546701914580656922L;
	
	/**
	 * 型号编码
	 */
	private String versionCode;
	/**
	 * 细分市场大类
	 */
	private String segmentParentName;
	/**
	 * 细分市场中文名称
	 */
	private String segmentNameCn;
	/**
	 * 细分市场英文名称
	 */
	private String segmentNameEn;
	/**
	 * 生产商中文名称
	 */
	private String manfNameCn;
	/**
	 * 生产商英文名称
	 */
	private String manfNameEn;
	/**
	 * 品牌中文名称
	 */
	private String brandNameCn;
	/**
	 * 品牌英文名称
	 */
	private String brandNameEn;
	/**
	 * 排量
	 */
	private String emissionsName;
	/**
	 * 排挡中文名称
	 */
	private String trnsmsNameCn;
	/**
	 * 排挡英文名称
	 */
	private String trnsmsNameEn;
	/**
	 * 型号标识中文
	 */
	private String versionShortNameCn;
	/**
	 * 型号名称
	 */
	private String versionNameCn;
	/**
	 * 型号英文名称
	 */
	private String versionNameEn;
	/**
	 * 型号标识英文
	 */
	private String versionShortNameEn;
	/**
	 * 子车型英文名称
	 */
	private String submodelNameCn;
	/**
	 * 子车型英文名称
	 */
	private String submodelNameEn;
	/**
	 * 车身形势中文名称
	 */
	private String bodyTypeNameCn;
	/**
	 * 车身形势英文名称
	 */
	private String bodyTypeNameEn;
	/**
	 * 年月
	 */
	private String ym;
	/**
	 * 批次
	 */
	private String week;
	/**
	 * 指导价
	 */
	private String Msrp;
	/**
	 * 加权平均价
	 */
	private String price;
	/**
	 * 经销商成本
	 */
	private String sellerCost;
	/**
	 * 开票价
	 */
	private String invoicePrice;
	/**
	 * 返利金额
	 */
	private String rebatePrice;
	/**
	 * 考核奖励
	 */
	private String rewardAssessment;
	/**
	 * 促销补贴
	 */
	private String promotionalAllowance;
	/**
	 * 销量
	 */
	private String sale;
	/**
	 * 车型利润
	 */
	private String modelProfit;
	/**
	 * 记录条数
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

	public String getSegmentParentName() {
		return segmentParentName;
	}
	
	public void setSegmentParentName(String segmentParentName) {
		this.segmentParentName = segmentParentName;
	}
	
	public String getSegmentNameCn() {
		return segmentNameCn;
	}

	public void setSegmentNameCn(String segmentNameCn) {
		this.segmentNameCn = segmentNameCn;
	}

	public String getSegmentNameEn() {
		return segmentNameEn;
	}

	public void setSegmentNameEn(String segmentNameEn) {
		this.segmentNameEn = segmentNameEn;
	}

	public String getManfNameCn() {
		return manfNameCn;
	}

	public void setManfNameCn(String manfNameCn) {
		this.manfNameCn = manfNameCn;
	}

	public String getManfNameEn() {
		return manfNameEn;
	}

	public void setManfNameEn(String manfNameEn) {
		this.manfNameEn = manfNameEn;
	}

	public String getBrandNameCn() {
		return brandNameCn;
	}

	public void setBrandNameCn(String brandNameCn) {
		this.brandNameCn = brandNameCn;
	}

	public String getBrandNameEn() {
		return brandNameEn;
	}

	public void setBrandNameEn(String brandNameEn) {
		this.brandNameEn = brandNameEn;
	}

	public String getEmissionsName() {
		return emissionsName;
	}

	public void setEmissionsName(String emissionsName) {
		this.emissionsName = emissionsName;
	}

	public String getTrnsmsNameCn() {
		return trnsmsNameCn;
	}

	public void setTrnsmsNameCn(String trnsmsNameCn) {
		this.trnsmsNameCn = trnsmsNameCn;
	}

	public String getTrnsmsNameEn() {
		return trnsmsNameEn;
	}

	public void setTrnsmsNameEn(String trnsmsNameEn) {
		this.trnsmsNameEn = trnsmsNameEn;
	}

	public String getVersionShortNameCn() {
		return versionShortNameCn;
	}

	public void setVersionShortNameCn(String versionShortNameCn) {
		this.versionShortNameCn = versionShortNameCn;
	}

	public String getVersionShortNameEn() {
		return versionShortNameEn;
	}

	public void setVersionShortNameEn(String versionShortNameEn) {
		this.versionShortNameEn = versionShortNameEn;
	}

	public String getSubmodelNameCn() {
		return submodelNameCn;
	}

	public void setSubmodelNameCn(String submodelNameCn) {
		this.submodelNameCn = submodelNameCn;
	}

	public String getSubmodelNameEn() {
		return submodelNameEn;
	}

	public void setSubmodelNameEn(String submodelNameEn) {
		this.submodelNameEn = submodelNameEn;
	}

	public String getBodyTypeNameCn() {
		return bodyTypeNameCn;
	}

	public void setBodyTypeNameCn(String bodyTypeNameCn) {
		this.bodyTypeNameCn = bodyTypeNameCn;
	}

	public String getBodyTypeNameEn() {
		return bodyTypeNameEn;
	}

	public void setBodyTypeNameEn(String bodyTypeNameEn) {
		this.bodyTypeNameEn = bodyTypeNameEn;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		switch (Integer.parseInt(week)) {
		case 1:	this.week = "第一周";
				break;
		case 2:	this.week = "第二周";
				break;
		case 3:	this.week = "第三周";
				break;
		case 4:	this.week = "第四周";
				break;
		case 7:	this.week = "月";
				break;

		}
	}

	public String getMsrp() {
		return Msrp;
	}

	public void setMsrp(String msrp) {
		Msrp = msrp;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getSellerCost() {
		return sellerCost;
	}

	public void setSellerCost(String sellerCost) {
		this.sellerCost = sellerCost;
	}

	public String getInvoicePrice() {
		return invoicePrice;
	}

	public void setInvoicePrice(String invoicePrice) {
		this.invoicePrice = invoicePrice;
	}

	public String getRebatePrice() {
		return rebatePrice;
	}

	public void setRebatePrice(String rebatePrice) {
		this.rebatePrice = rebatePrice;
	}

	public String getRewardAssessment() {
		return rewardAssessment;
	}

	public void setRewardAssessment(String rewardAssessment) {
		this.rewardAssessment = rewardAssessment;
	}

	public String getPromotionalAllowance() {
		return promotionalAllowance;
	}

	public void setPromotionalAllowance(String promotionalAllowance) {
		this.promotionalAllowance = promotionalAllowance;
	}

	public String getModelProfit() {
		return modelProfit;
	}

	public void setModelProfit(String modelProfit) {
		this.modelProfit = modelProfit;
	}
	public String getVersionNameCn() {
		return versionNameCn;
	}

	public void setVersionNameCn(String versionNameCn) {
		this.versionNameCn = versionNameCn;
	}

	public String getVersionNameEn() {
		return versionNameEn;
	}

	public void setVersionNameEn(String versionNameEn) {
		this.versionNameEn = versionNameEn;
	}
	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}
	
}
