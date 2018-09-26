package com.ways.app.backstageConfig.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ways.app.backstageConfig.dao.IOriginalAndCompetitorDao;
import com.ways.app.backstageConfig.model.LetterObj;
import com.ways.app.backstageConfig.model.OriginalAndCompetitor;
import com.ways.framework.base.dao.impl.BaseDaoImpl;

/**
 * 本竞品管理Dao实现类
 *  
 * @author songguobiao
 * 20160401
 */
@Repository("originalAndCompetitorDaoImpl")
public class OriginalAndCompetitorDaoImpl extends BaseDaoImpl implements IOriginalAndCompetitorDao  {
	
	/**
	 * 获取本竞品数据
	 * 
	 * @param paramsMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
    public List<OriginalAndCompetitor> getOriginalAndCompetitorData(Map<String, Object> paramsMap) {
    	List<OriginalAndCompetitor> originalAndCompetitorList = null;
		try {
			originalAndCompetitorList = getSqlMapClient().queryForList("originalAndCompetitor.getOriginalAndCompetitorData", paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return originalAndCompetitorList; 
    }
	
	/**
	 * 根据品牌获取车型数据
	 * 
	 * @param paramsMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LetterObj> getSubModelByBrand(Map<String, Object> paramsMap) {
		return getSqlMapClientTemplate().queryForList("originalAndCompetitor.getSubModelByBrand", paramsMap);
	}

	/**
	 * 根据厂商获取车型数据
	 * 
	 * @param paramsMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LetterObj> getSubModelByManf(Map<String, Object> paramsMap) {
		return getSqlMapClientTemplate().queryForList("originalAndCompetitor.getSubModelByManf", paramsMap);
	}
	
	/**
	 * 删除本竞品
	 * 
	 * @param paramsMap
	 * @return
	 */
	@Override
	public int deleteOriginalAndCompetitor(Map<String, Object> paramsMap) {
		return getSqlMapClientTemplate().delete("originalAndCompetitor.deleteOriginalAndCompetitor", paramsMap);
	}
	
	/**
	 * 添加本竞品--通过导入文件数据方式
	 * 
	 * @param originalAndCompetitor
	 * @return
	 */
	@Override
	public int addOriginalAndCompetitorByImport(OriginalAndCompetitor originalAndCompetitor) {
		return getSqlMapClientTemplate().update("originalAndCompetitor.addOriginalAndCompetitorByImport", originalAndCompetitor);
	}
	
	/**
	 * 添加本竞品--通过页面新增方式
	 * 
	 * @param originalAndCompetitor
	 * @return
	 */
	@Override
	public int addOriginalAndCompetitorByAdd(OriginalAndCompetitor originalAndCompetitor) {
		return getSqlMapClientTemplate().update("originalAndCompetitor.addOriginalAndCompetitorByAdd", originalAndCompetitor);
	}
	
	/**
	 * 修改本竞品组(删除原本的分组数据)
	 * 
	 * @param paramsMap
	 * @return
	 */
	@Override
	public int deleteOriginalAndCompetitorByUpdate(Map<String, Object> paramsMap) {
		return (Integer)getSqlMapClientTemplate().update("originalAndCompetitor.deleteOriginalAndCompetitorByUpdate", paramsMap);
	}
	
	/**
	 * 修改本竞品(添加新的分组数据)
	 * 
	 * @param paramsMap
	 * @return
	 */
	@Override
	public int addOriginalAndCompetitorByUpdate(Map<String, Object> paramsMap) {
		return (Integer)getSqlMapClientTemplate().update("originalAndCompetitor.addOriginalAndCompetitorByUpdate", paramsMap);
	}
	
	/**
	 * 更新本竞品的本品车型排序
	 * 
	 * @param paramsMap
	 * @return
	 */
	@Override
	public int updateOriginalAndCompetitorBpSort(Map<String, Object> paramsMap) {
		return (Integer)getSqlMapClientTemplate().update("originalAndCompetitor.updateOriginalAndCompetitorBpSort", paramsMap);
	}
	
	/**
	 * 更新本竞品的竞品车型排序
	 * 
	 * @param paramsMap
	 * @return
	 */
	@Override
	public int updateOriginalAndCompetitorJpSort(Map<String, Object> paramsMap) {
		return (Integer)getSqlMapClientTemplate().update("originalAndCompetitor.updateOriginalAndCompetitorJpSort", paramsMap);
	}
	
}
