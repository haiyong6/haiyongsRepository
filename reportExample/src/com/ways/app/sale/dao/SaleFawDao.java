package com.ways.app.sale.dao;

import java.util.List;
import java.util.Map;

import com.ways.app.sale.model.SaleDetail;

/**
 *成交价报告Dao接口类
 */
public interface SaleFawDao {

	
	/**初始化时间
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, String>> initDate(Map<String, Object> paramsMap);
	
	/**查询城市成交价及tp
	 * @param paramsMap
	 * @return
	 */
	public List<SaleDetail> findSaleInfo(Map<String, Object> paramsMap);
	
}
