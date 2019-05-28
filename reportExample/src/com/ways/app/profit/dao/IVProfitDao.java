package com.ways.app.profit.dao;

import java.util.List;
import java.util.Map;

import com.ways.app.profit.model.VProfit;

public interface IVProfitDao {

	public List<Map<String, String>> initDate(Map<String, Object> paramsMap);

	public List<Map<String, String>> findPurchaseCity(Map<String, Object> paramsMap);

	public List<VProfit> getFawVProfit(Map<String, Object> paramsMap);

}
