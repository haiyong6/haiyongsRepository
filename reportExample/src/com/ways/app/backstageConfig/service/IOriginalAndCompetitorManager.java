package com.ways.app.backstageConfig.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * 本竞品管理Service接口类
 * 
 * @author songguobiao
 * 20160401
 */
public interface IOriginalAndCompetitorManager {

	/**初始化数据
	 * 
	 * @param request
	 * @param paramsMap
	 */
	public String getOriginalAndCompetitorData(HttpServletRequest request,Map<String, Object> paramsMap);
	
	/**
	 * 获取初始化子车型弹出框控件值
	 * 
	 * @param request
	 * @param paramsMap
	 */
	public void getSubModelModal(HttpServletRequest request, Map<String, Object> paramsMap);
	
	/**
	 * 新增本竞品组
	 * 
	 * @param paramsMap
	 * @return
	 */
	public String addOriginalAndCompetitor(Map<String, Object> paramsMap);
	
	/**
	 * 修改本竞品组
	 * 
	 * @param paramsMap
	 * @return
	 */
	public String updateOriginalAndCompetitor(Map<String, Object> paramsMap);
    
	/**
	 * 获取本竞品组数据
	 * 
	 * @param paramsMap
	 * @return
	 */
	public String getOriginalAndCompetitor(Map<String, Object> paramsMap);
	
	/**
	 * 更新本竞品的车型排序
	 * 
	 * @param paramsMap
	 * @return
	 */
	public String updateOriginalAndCompetitorSort(Map<String, Object> paramsMap);
	
	/**
	 * 根据本品车型ID获取本竞品组数据
	 * @param paramsMap
	 */
	public String getBJPGroupByBpSubModelId(Map<String, Object> paramsMap);
	
	/**
	 * 删除本竞品组
	 * 
	 * @param paramsMap
	 * @return
	 */
	public String deleteOriginalAndCompetitor(Map<String, Object> paramsMap);
	
	/**导出数据
	 * 
	 * @param request
	 * @param paramsMap
	 * @return
	 */
	public Workbook exportExcel(HttpServletRequest request,Map<String, Object> paramsMap);
	
	/**
	 * 下载常用对象组模版文件
	 * 
	 * @param request
	 * @param response
	 */
	public void downloadOriginalAndCompetitorTemplate(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 导入文件数据
	 * 
	 * @param request
	 * @return
	 */
	public String importOriginalAndCompetitorFile(HttpServletRequest request);
}
