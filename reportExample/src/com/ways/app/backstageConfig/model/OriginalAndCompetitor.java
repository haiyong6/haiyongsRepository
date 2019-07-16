package com.ways.app.backstageConfig.model;

import java.io.Serializable;

/**
 * 本竞品管理
 * 
 * @author songguobiao
 * 20160401
 */
public class OriginalAndCompetitor implements Serializable{
	
	private static final long serialVersionUID = -317747909875533639L;
	/**
	 * 本品厂商
	 */
	private String bpManfName;
	/**
	 * 本品车型ID
	 */
	private Integer bpSubModelId;
	/**
	 * 本品车型
	 */
	private String bpSubModelName;
	/**
	 * 本品车型排序
	 */
	private Integer bpSubModelSort;
	/**
	 * 竞品厂商
	 */
    private String jpManfName;
    /**
     * 竞品车型ID
     */
    private Integer jpSubModelId;
    /**
	 * 竞品车型
	 */
	private String jpSubModelName;
	/**
	 * 竞品车型排序
	 */
	private Integer jpSubModelSort;
	
	public String getBpManfName() {
		return bpManfName;
	}
	public void setBpManfName(String bpManfName) {
		this.bpManfName = bpManfName;
	}
	public Integer getBpSubModelId() {
		return bpSubModelId;
	}
	public void setBpSubModelId(Integer bpSubModelId) {
		this.bpSubModelId = bpSubModelId;
	}
	public String getBpSubModelName() {
		return bpSubModelName;
	}
	public void setBpSubModelName(String bpSubModelName) {
		this.bpSubModelName = bpSubModelName;
	}
	public Integer getBpSubModelSort() {
		return bpSubModelSort;
	}
	public void setBpSubModelSort(Integer bpSubModelSort) {
		this.bpSubModelSort = bpSubModelSort;
	}
	public String getJpManfName() {
		return jpManfName;
	}
	public void setJpManfName(String jpManfName) {
		this.jpManfName = jpManfName;
	}
	public Integer getJpSubModelId() {
		return jpSubModelId;
	}
	public void setjpSubModelId(Integer jpSubModelId) {
		this.jpSubModelId = jpSubModelId;
	}
	public String getJpSubModelName() {
		return jpSubModelName;
	}
	public void setJpSubModelName(String jpSubModelName) {
		this.jpSubModelName = jpSubModelName;
	}
	public Integer getJpSubModelSort() {
		return jpSubModelSort;
	}
	public void setJpSubModelSort(Integer jpSubModelSort) {
		this.jpSubModelSort = jpSubModelSort;
	}
}
