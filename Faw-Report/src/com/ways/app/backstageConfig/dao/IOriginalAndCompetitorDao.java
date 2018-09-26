package com.ways.app.backstageConfig.dao;

import java.util.List;
import java.util.Map;

import com.ways.app.backstageConfig.model.LetterObj;
import com.ways.app.backstageConfig.model.OriginalAndCompetitor;

/**
 * 本竞品管理Dao接口类
 * 
 * @author songguobiao
 * 20160401
 */
public interface IOriginalAndCompetitorDao {
	
	/**
	 * 获取本竞品信息
	 * 
	 * @param paramsMap
	 * @return
	 */
    public List<OriginalAndCompetitor> getOriginalAndCompetitorData(Map<String, Object> paramsMap);
    
    /**
	 * 获取子车型通过品牌
	 * 
	 * @param paramsMap
	 * @return
	 */
	public List<LetterObj> getSubModelByBrand(Map<String, Object> paramsMap);
	
	/**
	 * 获取子车型通过厂商
	 * 
	 * @param paramsMap
	 * @return
	 */
	public List<LetterObj> getSubModelByManf(Map<String, Object> paramsMap);
	
    /**
     * 删除本竞品
     * 
     * @param paramsMap
     * @return
     */
    public int deleteOriginalAndCompetitor(Map<String, Object> paramsMap);
    
    /**
     * 添加本竞品--通过导入文件数据方式
     * 
     * @param originalAndCompetitor
     * @return
     */
    public int addOriginalAndCompetitorByImport(OriginalAndCompetitor originalAndCompetitor);
    
    /**
     * 添加本竞品--通过页面新增方式
     * 
     * @param originalAndCompetitor
     * @return
     */
    public int addOriginalAndCompetitorByAdd(OriginalAndCompetitor originalAndCompetitor);
    
    /**
	 * 修改本竞品组(删除原本的分组数据)
	 * 
	 * @param paramsMap
	 * @return
	 */
	public int deleteOriginalAndCompetitorByUpdate(Map<String, Object> paramsMap);
	
	/**
	 * 修改本竞品(添加新的分组数据)
	 * 
	 * @param paramsMap
	 * @return
	 */
	public int addOriginalAndCompetitorByUpdate(Map<String, Object> paramsMap);
	
	/**
	 * 更新本竞品的本品车型排序(本品车型)
	 * 
	 * @param paramsMap
	 * @return
	 */
	public int updateOriginalAndCompetitorBpSort(Map<String, Object> paramsMap);
	
	/**
	 * 更新本竞品的竞品车型排序(竞品车型)
	 * 
	 * @param paramsMap
	 * @return
	 */
	public int updateOriginalAndCompetitorJpSort(Map<String, Object> paramsMap);
}
