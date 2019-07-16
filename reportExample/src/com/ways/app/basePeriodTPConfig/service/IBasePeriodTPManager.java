package com.ways.app.basePeriodTPConfig.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * 常用对象管理Service接口类
 */
public interface IBasePeriodTPManager {
	
	/**
	 * 初始化时间
	 * 
	 * @param request
	 * @param paramsMap
	 * @return
	 */
	public void initDate(HttpServletRequest request, Map<String, Object> paramsMap);
	
	/**
	 * 初始化数据
	 * 
	 * @param request
	 * @param paramsMap
	 * @return
	 */
	public String getBasePeriodTPData(HttpServletRequest request, Map<String, Object> paramsMap);
	
	/**
	 * 获取常用对象组
	 * 
	 * @param paramsMap
	 * @return
	 */
	public String getBasePeriodTP(Map<String, Object> paramsMap);
	
	/**
	 * 导出数据
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
	public void downloadBasePeriodTPTemplate(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 导入文件数据
	 * 
	 * @param request
	 * @return
	 */
	public String importBasePeriodTPFile(HttpServletRequest request);
	
	/**
	 * 城市基期TP模板
	 * 
	 * @param request
	 * @param response
	 */
	public void downloadCityBasePeriodTPTemplate(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 导入城市基期TP数据
	 * 
	 * @param request
	 * @return
	 */
	public String importCityBasePeriodTPFile(HttpServletRequest request);
}
