package com.ways.app.cityPrice.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ways.app.cityPrice.dao.ITpCityDao;
import com.ways.app.cityPrice.model.CityArea;
import com.ways.app.cityPrice.model.FawCityTp;
import com.ways.app.cityPrice.model.FawCityTpPromotions;
import com.ways.framework.base.dao.impl.BaseDaoImpl;

/**
 *成交价报告DaoImpl实现类
 */
@Repository("tpCityDaoImpl")
public class TpCityDaoImpl extends BaseDaoImpl implements ITpCityDao {

	/**
	 * 查询成交价数据
	 * 
	 * @param paramsMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<FawCityTp> getFawCityTp(Map<String, Object> paramsMap) {
		List<FawCityTp> cityFawTpList = null;
		try {
			cityFawTpList = getSqlMapClient().queryForList("tpCity.getMainDataEx",paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityFawTpList; 
	}
	
	/**
	 * 导出成交价数据
	 * 
	 * @param paramsMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<FawCityTp> getFawCityTps(Map<String, Object> paramsMap) {
		List<FawCityTp> cityFawTpList = null;
		try {
			cityFawTpList = getSqlMapClient().queryForList("tpCity.getMainDatas",paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityFawTpList; 
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> initDate(Map<String, Object> paramsMap) {
		List<Map<String,String>> dateList = null;
		try {
			dateList = getSqlMapClient().queryForList("tpCity.initDate",paramsMap);
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
			cityList = getSqlMapClient().queryForList("tpCity.findPurchaseCity",paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityList; 
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> findPurchaseCity(String cityYear) {
		List<Map<String,String>> cityList = null;
		try {
			cityList = getSqlMapClient().queryForList("tpCity.findPurchaseCityByYear",cityYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityList; 
	}

	/**
	 * 获取原始内部促销信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<FawCityTpPromotions> getPromotionsData(Map<String, Object> paramsMap) {
		List<FawCityTpPromotions> cityFawTpList = null;
		try {
			cityFawTpList = getSqlMapClient().queryForList("tpCity.getPromotionsData",paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityFawTpList; 
	}

	/**
	 * 获取城市大区
	 */
	@Override
	public List<CityArea> getCityArea(Map<String, Object> paramsMap) {
		List<CityArea> cityAreaList = null;
		try {
			cityAreaList = getSqlMapClient().queryForList("tpCity.getCityArea",paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityAreaList;
	}

}
