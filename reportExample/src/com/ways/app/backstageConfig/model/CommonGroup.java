package com.ways.app.backstageConfig.model;

import java.io.Serializable;


/**
 * 常用对象管理
 * 
 * @author songguobiao
 * 20160401
 */
public class CommonGroup implements Serializable{

	private static final long serialVersionUID = -3358204100837872931L;
	/**
	 * 分组Id
	 */
	private Integer groupId;
    /**
     * 分组名称
     */
	private String groupName;
	/**
	 * 分组排序
	 */
	private Integer groupSort;
	/**
	 * 厂商
	 */
	private String manfName;
	/**
	 * 车型ID
	 */
	private Integer subModelId;
	/**
	 * 车型名称
	 */
	private String subModelName;
	/**
	 * 型号ID
	 */
	private Integer versionId;
	/**
	 * 型号名称
	 */
	private String versionName;
	/**
	 * 型号编码
	 */
	private String versionCode;
	/**
	 * 是否竞品
	 */
	private Integer isJP;
	/**
	 * 指导价
	 */
	private Integer MSRP;
	/**
	 * 型号排序
	 */
	private Integer versionSort;
	
    public Integer getGroupId() {
    	return groupId;
    }
    public void setGroupId(Integer groupId) {
    	this.groupId = groupId;
    }
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getGroupSort() {
		return groupSort;
	}
	public void setGroupSort(Integer groupSort) {
		this.groupSort = groupSort;
	}
	public String getManfName() {
		return manfName;
	}
	public void setManfName(String manfName) {
		this.manfName = manfName;
	}
	public Integer getSubModelId() {
		return subModelId;
	}
	public void setSubModelId(Integer subModelId) {
		this.subModelId = subModelId;
	}
	public String getSubModelName() {
		return subModelName;
	}
	public Integer getVersionId() {
		return versionId;
	}
	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}
	public void setSubModelName(String subModelName) {
		this.subModelName = subModelName;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public Integer getIsJP() {
		return isJP;
	}
	public void setIsJP(Integer isJP) {
		this.isJP = isJP;
	}
	public Integer getMSRP() {
		return MSRP;
	}
	public void setMSRP(Integer MSRP) {
		this.MSRP = MSRP;
	}
	public Integer getVersionSort() {
		return versionSort;
	}
	public void setVersionSort(Integer versionSort) {
		this.versionSort = versionSort;
	}
}
