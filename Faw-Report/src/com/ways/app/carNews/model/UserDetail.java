package com.ways.app.carNews.model;

import java.io.Serializable;

public class UserDetail implements Serializable{
	private static final long serialVersionUID = -8430896377577231974L;
	/**
	 * 型号编码
	 */
	private String userId;
	/**
	 * 细分市场中文名称
	 */
	private String userName;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
