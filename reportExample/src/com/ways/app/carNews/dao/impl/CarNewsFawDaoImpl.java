package com.ways.app.carNews.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ways.app.carNews.dao.CarNewsFawDao;
import com.ways.app.carNews.model.BPSubModel;
import com.ways.app.carNews.model.BodyType;
import com.ways.app.carNews.model.LetterObj;
import com.ways.app.carNews.model.Segment;
import com.ways.app.cityPrice.model.FawCityTp;
import com.ways.framework.base.dao.impl.BaseDaoImpl;
/**
 *成交价报告DaoImpl实现类
 */
@Repository("CarNewsFawDaoImpl")
public class CarNewsFawDaoImpl extends BaseDaoImpl implements CarNewsFawDao {

	@Override
	public List<BodyType> getBodyType(Map<String, Object> paramsMap) {
		List<BodyType> cityList = null;
		try {
			cityList = getSqlMapClient().queryForList("newCarsFw.getBodyType",paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityList; 
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Segment> getSubmodelBySegment(Map<String, Object> paramsMap) 
	{
		return getSqlMapClientTemplate().queryForList("newCarsFw.getSubmodelBySegment",paramsMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LetterObj> getSubmodelByBrand(Map<String, Object> paramsMap) 
	{
		return getSqlMapClientTemplate().queryForList("newCarsFw.getSubmodelByBrand",paramsMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LetterObj> getSubmodelByManf(Map<String, Object> paramsMap) 
	{
		return getSqlMapClientTemplate().queryForList("newCarsFw.getSubmodelByManf",paramsMap);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BPSubModel> getSubmodelByBp(Map<String, Object> paramsMap) 
	{
		return getSqlMapClientTemplate().queryForList("newCarsFw.getSubmodelByBp",paramsMap);
	}
}
