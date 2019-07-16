package com.ways.app.monthly.tp.model;

public class FawTp {
	private String year;
	private String month;
	private String price;
	private String versioncode;
	//记录数
	private int totalCount;
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getVersioncode() {
		return versioncode;
	}
	public void setVersioncode(String versioncode) {
		this.versioncode = versioncode;
	}
}
