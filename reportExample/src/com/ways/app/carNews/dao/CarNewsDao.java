package com.ways.app.carNews.dao;

import java.util.List;
import java.util.Map;

import com.ways.app.carNews.model.BodyType;
import com.ways.app.carNews.model.carNewsDetail;

/**
 *成交价报告Dao接口类
 */
public interface CarNewsDao {
	
	/**初始化时间
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, String>> initDate(Map<String, Object> paramsMap);
	
	/**查询城市成交价及tp
	 * @param paramsMap
	 * @return
	 */
	public List<carNewsDetail> findNewCarsInfo(Map<String, Object> paramsMap);
	
}
