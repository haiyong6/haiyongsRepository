package com.ways.app.carNews.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ways.app.carNews.dao.CarNewsDao;
import com.ways.app.carNews.model.BodyType;
import com.ways.app.carNews.model.carNewsDetail;
import com.ways.framework.base.dao.impl.AutoWaysBaseDaoImpl;
/**
 *成交价报告DaoImpl实现类
 */
@Repository("carNewsDaoImpl")
public class CarNewsDaoImpl extends AutoWaysBaseDaoImpl implements CarNewsDao {
	
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<carNewsDetail> findNewCarsInfo(Map<String, Object> paramsMap) {
		List<carNewsDetail> cityFawTpList = null;
		try {
			cityFawTpList = getSqlMapClient().queryForList("carNews.findNewCarsInfo",paramsMap);
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
			dateList = getSqlMapClient().queryForList("carNews.initDate",paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateList; 
	}

}
