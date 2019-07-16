package com.ways.app.monthly.tp.dao;

import java.util.List;
import java.util.Map;

import com.ways.app.monthly.tp.model.FawTp;


public interface ITpMonthlyPriceDao {
	/**
	 * 分页获取一汽大众成交价
	 * @param paramsMap
	 * @return
	 */
	public List<FawTp> findTpMonthlyPriceInfo(Map<String, Object> paramsMap);
}
