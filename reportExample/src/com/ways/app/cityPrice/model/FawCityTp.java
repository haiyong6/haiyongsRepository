package com.ways.app.cityPrice.model;

import java.io.Serializable;

public class FawCityTp implements Serializable{
	private static final long serialVersionUID = -8430896377577231974L;
	/**
	 * 型号全称(中文)
	 */
	private String versionNameCn;
	/**
	 * 型号全称（英文）
	 */
	 private String versionNameEn;
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
	 * 车上形势中文名称
	 */
	private String bodyTypeNameCn;
	/**
	 * 车上形势英文名称
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
	 * 记录条数
	 */
	private int totalCount;
	/**
	 * 城市中文名称
	 */
	private String cityNameCn;
	/**
	 * 城市英文名称
	 */
	private String cityNameEn;
	
	/**
	 * 城市排序
	 */
	private int csort;
	
	/**
	 * 最低价
	 */
	private String minPrice;
	/**
	 * 市场价加权平均价
	 */
	private String prices;
	/**
	 * 最低市场价
	 */
	private String minPrices;
	/**
	 * 上市日期
	 */
	private String launchDate;
	/**
	 * 停产日期
	 */
	private String noProductDate;
	/**
	 * 燃料字段
	 */
	private String fuelTypeName;
	/**
	 * 一汽大众TP加权平均价
	 */
	private String priceFawvw;
	/**
	 * 最低一汽大众TP
	 */
	private String minPriceFawvw;
	/**
	 * 内部促销信息
	 */
	private String promotions;
	/**
	 * 唯一标识
	 */
	private String key;
	/**
	 * 大区
	 */
	private String mixAvg1;//东北区
	private String mixAvg2;//华北区
	private String mixAvg3;//华中区
	private String mixAvg4;//华东区
	private String mixAvg5;//华南区
	private String mixAvg6;//西区
	/**
	 * 城市
	 */
	private String c1 ;//广州
	private String c2 ;//上海
	private String c3 ;//北京
	private String c4 ;//成都
	private String c5 ;//杭州
	private String c7 ;//武汉
	private String c8 ;//沈阳
	private String c11;//南京
	private String c12;//济南
	private String c13;//西安
	private String c14;//深圳
	private String c15;//郑州
	private String c16;//长沙
	private String c17;//哈尔滨
	private String c18;//石家庄
	private String c19;//太原
	private String c20;//南宁
	private String c21;//重庆
	private String c22;//昆明
	private String c23;//合肥
	private String c28;//呼和浩特
	private String c29;//贵阳
	private String c30;//南昌
	private String c31;//青岛
	private String c32;//福州
	private String c41;//兰州
	private String c42;//乌鲁木齐
	private String c47;//长春
	private String c48;//天津
	private String c60;//大连
	private String c61;//宁波
	private String c38;//厦门
	private String c40;//洛阳
	private String c44;//苏州
	private String c50;//西宁
	private String c70;//佛山
	private String c81;//温州
	private String c98;//运城

	
	public String getNoProductDate() {
		return noProductDate;
	}

	public void setNoProductDate(String noProductDate) {
		this.noProductDate = noProductDate;
	}

	public String getFuelTypeName() {
		return fuelTypeName;
	}

	public void setFuelTypeName(String fuelTypeName) {
		this.fuelTypeName = fuelTypeName;
	}

	public String getC38() {
		return c38;
	}

	public void setC38(String c38) {
		this.c38 = c38;
	}

	public String getC40() {
		return c40;
	}

	public void setC40(String c40) {
		this.c40 = c40;
	}

	public String getC44() {
		return c44;
	}

	public void setC44(String c44) {
		this.c44 = c44;
	}

	public String getC50() {
		return c50;
	}

	public void setC50(String c50) {
		this.c50 = c50;
	}

	public String getC70() {
		return c70;
	}

	public void setC70(String c70) {
		this.c70 = c70;
	}

	public String getC81() {
		return c81;
	}

	public void setC81(String c81) {
		this.c81 = c81;
	}

	public String getC98() {
		return c98;
	}

	public void setC98(String c98) {
		this.c98 = c98;
	}

	public String getMixAvg1() {
		return mixAvg1;
	}

	public void setMixAvg1(String mixAvg1) {
		this.mixAvg1 = mixAvg1;
	}

	public String getMixAvg2() {
		return mixAvg2;
	}

	public void setMixAvg2(String mixAvg2) {
		this.mixAvg2 = mixAvg2;
	}

	public String getMixAvg3() {
		return mixAvg3;
	}

	public void setMixAvg3(String mixAvg3) {
		this.mixAvg3 = mixAvg3;
	}

	public String getMixAvg4() {
		return mixAvg4;
	}

	public void setMixAvg4(String mixAvg4) {
		this.mixAvg4 = mixAvg4;
	}

	public String getMixAvg5() {
		return mixAvg5;
	}

	public void setMixAvg5(String mixAvg5) {
		this.mixAvg5 = mixAvg5;
	}

	public String getMixAvg6() {
		return mixAvg6;
	}

	public void setMixAvg6(String mixAvg6) {
		this.mixAvg6 = mixAvg6;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPromotions() {
		return promotions;
	}

	public void setPromotions(String promotions) {
		this.promotions = promotions;
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC2() {
		return c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public String getC3() {
		return c3;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	public String getC4() {
		return c4;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}

	public String getC5() {
		return c5;
	}

	public void setC5(String c5) {
		this.c5 = c5;
	}

	public String getC7() {
		return c7;
	}

	public void setC7(String c7) {
		this.c7 = c7;
	}

	public String getC8() {
		return c8;
	}

	public void setC8(String c8) {
		this.c8 = c8;
	}

	public String getC11() {
		return c11;
	}

	public void setC11(String c11) {
		this.c11 = c11;
	}

	public String getC12() {
		return c12;
	}

	public void setC12(String c12) {
		this.c12 = c12;
	}

	public String getC13() {
		return c13;
	}

	public void setC13(String c13) {
		this.c13 = c13;
	}

	public String getC14() {
		return c14;
	}

	public void setC14(String c14) {
		this.c14 = c14;
	}

	public String getC15() {
		return c15;
	}

	public void setC15(String c15) {
		this.c15 = c15;
	}

	public String getC16() {
		return c16;
	}

	public void setC16(String c16) {
		this.c16 = c16;
	}

	public String getC17() {
		return c17;
	}

	public void setC17(String c17) {
		this.c17 = c17;
	}

	public String getC18() {
		return c18;
	}

	public void setC18(String c18) {
		this.c18 = c18;
	}

	public String getC19() {
		return c19;
	}

	public void setC19(String c19) {
		this.c19 = c19;
	}

	public String getC20() {
		return c20;
	}

	public void setC20(String c20) {
		this.c20 = c20;
	}

	public String getC21() {
		return c21;
	}

	public void setC21(String c21) {
		this.c21 = c21;
	}

	public String getC22() {
		return c22;
	}

	public void setC22(String c22) {
		this.c22 = c22;
	}

	public String getC23() {
		return c23;
	}

	public void setC23(String c23) {
		this.c23 = c23;
	}

	public String getC28() {
		return c28;
	}

	public void setC28(String c28) {
		this.c28 = c28;
	}

	public String getC29() {
		return c29;
	}

	public void setC29(String c29) {
		this.c29 = c29;
	}

	public String getC30() {
		return c30;
	}

	public void setC30(String c30) {
		this.c30 = c30;
	}

	public String getC31() {
		return c31;
	}

	public void setC31(String c31) {
		this.c31 = c31;
	}

	public String getC32() {
		return c32;
	}

	public void setC32(String c32) {
		this.c32 = c32;
	}

	public String getC41() {
		return c41;
	}

	public void setC41(String c41) {
		this.c41 = c41;
	}

	public String getC42() {
		return c42;
	}

	public void setC42(String c42) {
		this.c42 = c42;
	}

	public String getC47() {
		return c47;
	}

	public void setC47(String c47) {
		this.c47 = c47;
	}

	public String getC48() {
		return c48;
	}

	public void setC48(String c48) {
		this.c48 = c48;
	}

	public String getC60() {
		return c60;
	}

	public void setC60(String c60) {
		this.c60 = c60;
	}

	public String getC61() {
		return c61;
	}

	public void setC61(String c61) {
		this.c61 = c61;
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

	public String getMinPriceFawvw() {
		return minPriceFawvw;
	}

	public void setMinPriceFawvw(String minPriceFawvw) {
		this.minPriceFawvw = minPriceFawvw;
	}

	public String getPriceFawvw() {
		return priceFawvw;
	}

	public void setPriceFawvw(String priceFawvw) {
		this.priceFawvw = priceFawvw;
	}

	public String getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(String launchDate) {
		this.launchDate = launchDate;
	}

	public String getMinPrices() {
		return minPrices;
	}

	public void setMinPrices(String minPrices) {
		this.minPrices = minPrices;
	}

	public String getPrices() {
		return prices;
	}

	public void setPrices(String prices) {
		this.prices = prices;
	}

	public String getCityNameCn() {
		return cityNameCn;
	}

	public void setCityNameCn(String cityNameCn) {
		this.cityNameCn = cityNameCn;
	}

	public String getCityNameEn() {
		return cityNameEn;
	}

	public void setCityNameEn(String cityNameEn) {
		this.cityNameEn = cityNameEn;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

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
		case 6:	this.week = "上半月";
				break;
		case 7:	this.week = "下半月";
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
	
	public int getCsort() {
		return csort;
	}

	public void setCsort(int csort) {
		this.csort = csort;
	}

}
