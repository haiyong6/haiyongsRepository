package com.ways.app.cityPrice.model;

public class FawCityTpPromotions {
	private String key;//唯一标识
	private String promotions;//要拼接的原内部促销数据
	private String csort;//按价格排序城市字段；
	private String versionId;//型号id
	private String week;//周
	private String ym;//年月
	
	
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getYm() {
		return ym;
	}
	public void setYm(String ym) {
		this.ym = ym;
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
	public String getCsort() {
		return csort;
	}
	public void setCsort(String csort) {
		this.csort = csort;
	}
	
	
}
