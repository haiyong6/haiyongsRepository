package com.ways.app.backstageConfig.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * 常用对象管理Service接口类
 * 
 * @author songguobiao
 * 20160401
 */
public interface ICommonGroupManager {
	
	/**
	 * 初始化数据
	 * 
	 * @param request
	 * @param paramsMap
	 * @return
	 */
	public String getCommonGroupData(HttpServletRequest request, Map<String, Object> paramsMap);
	
	/**
	 * 获取常用对象组
	 * 
	 * @param paramsMap
	 * @return
	 */
	public String getCommonGroup(Map<String, Object> paramsMap);
	
	/**
	 * 获取初始化子车型弹出框控件值
	 * 
	 * @param request
	 * @param paramsMap
	 */
	public void getSubModelModal(HttpServletRequest request, Map<String, Object> paramsMap);
	
	/**
	 * 获取车型下的型号数据
	 * 
	 * @param request
	 * @param paramsMap
	 */
	public void getVersionModal(HttpServletRequest request, Map<String, Object> paramsMap); 
	
	/**
	 * 删除常用对象组
	 * 
	 * @param paramsMap
	 * @return
	 */
	public String deleteCommonGroup(Map<String, Object> paramsMap);
	
	/**
	 * 新增常用对象组
	 * 
	 * @param paramsMap
	 * @return
	 */
	public String addCommonGroup(Map<String, Object> paramsMap);
	
	/**
	 * 根据分组名称获取分组信息
	 * 
	 * @param paramsMap
	 * @return
	 */
	public String getCommonGroupByGroupName(Map<String, Object> paramsMap);
	
	/**
	 * 修改常用对象组
	 * 
	 * @param paramsMap
	 * @return
	 */
	public String updateCommonGroup(Map<String, Object> paramsMap);
	
	/**
	 * 更新常用对象组型号排序
	 * @param paramsMap
	 * @return
	 */
	public String updateCommonGroupVersionSort(Map<String, Object> paramsMap);
	
	/**导出数据
	 * 
	 * @param request
	 * @param paramsMap
	 * @return
	 */
	public Workbook exportExcel(HttpServletRequest request, Map<String, Object> paramsMap);
	
	/**
	 * 下载常用对象组模版文件
	 * 
	 * @param request
	 * @param response
	 */
	public void downloadCommonGroupTemplate(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 导入文件数据
	 * 
	 * @param request
	 * @return
	 */
	public String importCommonGroupFile(HttpServletRequest request);
}
