package com.ways.app.cityPrice.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Workbook;

/**
 *成交价报表Service接口类
 */
public interface ITpCityManager {
	/**初始化数据
	 * @param request
	 * @param paramsMap
	 */
	public void initData(HttpServletRequest request,Map<String, Object> paramsMap);
	
	/**查询成交价,加载DataTable数据
	 * @param paramsMap
	 * @return
	 */
	public String findFawCityTp(HttpServletRequest request,Map<String, Object> paramsMap);

	/**导出成交价数据
	 * @param request
	 * @param paramsMap
	 * @return
	 */
	public Workbook exportExcel(HttpServletRequest request,Map<String, Object> paramsMap);
}
