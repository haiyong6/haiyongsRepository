package com.ways.app.basePeriodTPConfig.dao;

import java.util.List;
import java.util.Map;

import com.ways.app.basePeriodTPConfig.model.BasePeriodTP;

/**
 * 常用对象管理Dao接口类
 *  
 * @author songguobiao
 * 20160401
 */
public interface IBasePeriodTPDao {
	
	/**初始化时间
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, String>> initDate(Map<String, Object> paramsMap);
	
	/**
	 * 获取数据
	 * 
	 * @param paramsMap
	 * @return
	 */
    public List<BasePeriodTP> getBasePeriodTPData(Map<String, Object> paramsMap);
    	
	/**
	 * 导入数据
	 * 
	 * @param paramsMap
	 * @return
	 */
	public int addBasePeriodTPByImport(Map<String, Object> paramsMap);
	
	public void delBasePeriodTPError();
	
	public void addBasePeriodTPError(Map<String, Object> paramsMap);

	public void delCityBasePeriodTPError();

	public int addCityBasePeriodTPByImport(Map<String, Object> dataList);

	public void addCityBasePeriodTPError(Map<String, Object> paramsMap);

	public List<BasePeriodTP> getCityBasePeriodTPData(Map<String, Object> paramsMap);
    
}
