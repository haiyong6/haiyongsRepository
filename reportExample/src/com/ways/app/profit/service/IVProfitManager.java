package com.ways.app.profit.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Workbook;

public interface IVProfitManager {

	/**初始化时间
	 * @param request
	 * @param paramsMap
	 */
	public void initData(HttpServletRequest request, Map<String, Object> paramsMap);

	/**查询一汽大众型号利润
	 * @param request
	 * @param paramsMap
	 * @return
	 */
	public String findFawVProfit(HttpServletRequest request,Map<String, Object> paramsMap);
	/**导出成交价数据
	 * @param request
	 * @param paramsMap
	 * @return
	 */
	public Workbook exportExcel(HttpServletRequest request,Map<String, Object> paramsMap);

}
