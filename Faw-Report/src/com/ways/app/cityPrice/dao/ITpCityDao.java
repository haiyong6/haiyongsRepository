package com.ways.app.cityPrice.dao;

import java.util.List;
import java.util.Map;

import com.ways.app.cityPrice.model.CityArea;
import com.ways.app.cityPrice.model.FawCityTp;
import com.ways.app.cityPrice.model.FawCityTpPromotions;

/**
 *成交价报告Dao接口类
 */
public interface ITpCityDao {
	/**初始化时间
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, String>> initDate(Map<String, Object> paramsMap);
	
	/**购买城市标准切换
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, String>> findPurchaseCity(Map<String, Object> paramsMap);
	
	/**通过年份查询购买城市信息
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, String>> findPurchaseCity(String cityYear);
	
	/**查询城市成交价及tp
	 * @param paramsMap
	 * @return
	 */
	public List<FawCityTp> getFawCityTp(Map<String, Object> paramsMap);
	
	public List<FawCityTp> getFawCityTps(Map<String, Object> paramsMap);
	/**
	 * 获取原始内部促销信息
	 * @param paramsMap
	 * @return
	 */
	public List<FawCityTpPromotions> getPromotionsData(Map<String, Object> paramsMap);

	/**
	 * 获取城市大区
	 * @param paramsMap
	 * @return
	 */
	public List<CityArea> getCityArea(Map<String, Object> paramsMap);
	
}
