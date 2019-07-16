package com.ways.app.carNews.dao;

import java.util.List;
import java.util.Map;

import com.ways.app.carNews.model.BPSubModel;
import com.ways.app.carNews.model.BodyType;
import com.ways.app.carNews.model.LetterObj;
import com.ways.app.carNews.model.Segment;
import com.ways.app.cityPrice.model.FawCityTp;

/**
 *成交价报告Dao接口类
 */
public interface CarNewsFawDao {

	/**
	 * 获车身形式
	 * @param paramsMap
	 * @return
	 */
	public List<BodyType> getBodyType(Map<String, Object> paramsMap);
	/**
	 * 获取子车型通过细分市场
	 * @param paramsMap
	 * @return
	 */
	public List<Segment> getSubmodelBySegment(Map<String, Object> paramsMap);
	
	/**
	 * 获取子车型通过品牌
	 * @param paramsMap
	 * @return
	 */
	public List<LetterObj> getSubmodelByBrand(Map<String, Object> paramsMap);
	
	/**
	 * 获取子车型通过厂商
	 * @param paramsMap
	 * @return
	 */
	public List<LetterObj> getSubmodelByManf(Map<String, Object> paramsMap);
	
	/**
	 * 获取子车型通过本品ID查找其竟品车型
	 * @param paramsMap
	 * @return
	 */
	public List<BPSubModel> getSubmodelByBp(Map<String, Object> paramsMap);
	
}
