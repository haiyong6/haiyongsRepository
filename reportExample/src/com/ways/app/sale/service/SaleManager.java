package com.ways.app.sale.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Workbook;

public interface SaleManager {

	/**初始化数据
	 * @param request
	 * @param paramsMap
	 */
	public void initData(HttpServletRequest request,Map<String, Object> paramsMap);
	
	public String findSaleInfo(HttpServletRequest request,Map<String, Object> paramsMap);
	/**导出成交价数据
	 * @param request
	 * @param paramsMap
	 * @return
	 */
	public Workbook exportExcel(HttpServletRequest request,Map<String, Object> paramsMap);
}
