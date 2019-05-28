package com.ways.app.profit.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ways.app.profit.dao.IVProfitDao;
import com.ways.app.profit.model.VProfit;
import com.ways.framework.base.dao.impl.BaseDaoImpl;

/**
 *型号利润Dao实现类
 */
@Repository("vProfitDaoImpl")
public class VProfitDaoImpl extends BaseDaoImpl implements IVProfitDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<VProfit> getFawVProfit(Map<String, Object> paramsMap) {
		List<VProfit> vProfitList = null;
		try {
			vProfitList = getSqlMapClient().queryForList("profit.getMainData",paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vProfitList; 
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> initDate(Map<String, Object> paramsMap) {
		List<Map<String,String>> dateList = null;
		try {
			dateList = getSqlMapClient().queryForList("profit.initDate",paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateList; 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> findPurchaseCity(Map<String, Object> paramsMap) {
		List<Map<String,String>> cityList = null;
		try {
			cityList = getSqlMapClient().queryForList("profit.findPurchaseCity",paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityList; 
	}
}
