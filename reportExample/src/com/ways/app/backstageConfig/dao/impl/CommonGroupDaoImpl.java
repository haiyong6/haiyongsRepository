package com.ways.app.backstageConfig.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ways.app.backstageConfig.dao.ICommonGroupDao;
import com.ways.app.backstageConfig.model.CommonGroup;
import com.ways.app.backstageConfig.model.LetterObj;
import com.ways.app.backstageConfig.model.SubModel;
import com.ways.framework.base.dao.impl.BaseDaoImpl;

/**
 * 常用对象管理Dao实现类
 *  
 * @author songguobiao
 * 20160401
 */
@Repository("commonGroupDaoImpl")
public class CommonGroupDaoImpl extends BaseDaoImpl implements ICommonGroupDao {
	
	/**
	 * 获取常用对象数据
	 * 
	 * @param paramsMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
    public List<CommonGroup> getCommonGroupData(Map<String, Object> paramsMap) {
    	List<CommonGroup> commonGroupList = null;
		try {
			commonGroupList = getSqlMapClient().queryForList("commonGroup.getCommonGroupData", paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commonGroupList; 
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
		return getSqlMapClientTemplate().queryForList("commonGroup.getSubModelByBrand", paramsMap);
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
		return getSqlMapClientTemplate().queryForList("commonGroup.getSubModelByManf", paramsMap);
	}
	
	/**
	 * 获取子车型下型号数据
	 * 
	 * @param paramsMap
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SubModel> getVersionModal(Map<String, Object> paramsMap) {
		return getSqlMapClientTemplate().queryForList("commonGroup.getVersionModal", paramsMap);
	}
	
	/**
	 * 删除常用对象组
	 * 
	 * @param paramsMap
	 * @return
	 */
	@Override
	public int deleteCommonGroup(Map<String, Object> paramsMap) {
		int a = getSqlMapClientTemplate().delete("commonGroup.deleteCommonGroupVersion", paramsMap);
		int b = getSqlMapClientTemplate().delete("commonGroup.deleteCommonGroup", paramsMap);
	 	return a + b;
	}
	
	/**
	 * 添加常用对象组(分组信息)--通过导入文件数据方式
	 * 
	 * @param paramsMap
	 * @return 
	 */
	@Override
	public int addCommonGroupByImport(Map<String, Object> paramsMap) {
		return (Integer)getSqlMapClientTemplate().update("commonGroup.addCommonGroupByImport", paramsMap);
	}
	
	/**
	 * 添加常用对象组(分组型号信息)--通过导入文件数据方式
	 * 
	 * @param commonGroup
	 * @return
	 */
	@Override
	public int addCommonGroupVersionByImport(CommonGroup commonGroup) {
		return (Integer)getSqlMapClientTemplate().update("commonGroup.addCommonGroupVersionByImport", commonGroup);
	}
	
	/**
	 * 添加常用对象组(分组信息)--通过页面新增方式
	 * 
	 * @param commonGroup
	 * @return 
	 */
	@Override
	public int addCommonGroupByAdd(CommonGroup commonGroup) {
		return (Integer)getSqlMapClientTemplate().update("commonGroup.addCommonGroupByAdd", commonGroup);
	}
	
	/**
	 * 添加常用对象组(分组型号信息)--通过页面新增方式
	 * 
	 * @param commonGroup
	 * @return
	 */
	@Override
	public int addCommonGroupVersionByAdd(CommonGroup commonGroup) {
		return (Integer)getSqlMapClientTemplate().update("commonGroup.addCommonGroupVersionByAdd", commonGroup);
	}
	
	/**
	 * 删除常用对象组分组信息(修改)
	 * 
	 * @param paramsMap
	 * @return 
	 */
	@Override
	public int deleteCommonGroupByUpdate(Map<String, Object> paramsMap) {
		int a = getSqlMapClientTemplate().delete("commonGroup.deleteCommonGroupByUpdate", paramsMap);
		int b = getSqlMapClientTemplate().delete("commonGroup.deleteCommonGroupVersionByUpdate", paramsMap);
		return a + b;
	}
	
	/**
	 * 添加常用对象组分组信息(修改)
	 * 
	 * @param commonGroup
	 * @return
	 */
	@Override
	public int addCommonGroupByUpdate(CommonGroup commonGroup) {
		return (Integer)getSqlMapClientTemplate().update("commonGroup.addCommonGroupByUpdate", commonGroup);
	}
	
	/**
	 * 添加常用对象组分组型号信息(修改)
	 * 
	 * @param paramsMap
	 * @return
	 */
	@Override
	public int addCommonGroupVersionByUpdate(Map<String, Object> paramsMap) {
		return (Integer)getSqlMapClientTemplate().update("commonGroup.addCommonGroupVersionByUpdate", paramsMap);
	}
	
	/**
	 * 根据分组名称获取分组信息(分组信息)
	 * 
	 * @param paramsMap
	 * @return
	 */
	@Override
	public CommonGroup getBPInfoByGroupName(Map<String, Object> paramsMap) {
		return (CommonGroup)getSqlMapClientTemplate().queryForObject("commonGroup.getBPInfoByGroupName", paramsMap);
	}
	
	/**
	 * 根据分组名称获取分组信息(分组型号信息)
	 * 
	 * @param paramsMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CommonGroup> getJPInfoByGroupName(Map<String, Object> paramsMap) {
		return (List<CommonGroup>)getSqlMapClientTemplate().queryForList("commonGroup.getJPInfoByGroupName", paramsMap);
	}
	
	/**
	 * 更新常用对象组的型号排序(分组排序)
	 * 
	 * @param paramsMap
	 * @return
	 */
	@Override
    public int updateCommonGroupGroupSort(Map<String, Object> paramsMap) {
		return (Integer)getSqlMapClientTemplate().update("commonGroup.updateCommonGroupGroupSort", paramsMap);
	}
	
	/**
	 * 更新常用对象组的型号排序(型号排序)
	 * 
	 * @param paramsMap
	 * @return
	 */
	@Override
    public int updateCommonGroupVersionSort(Map<String, Object> paramsMap) {
		return (Integer)getSqlMapClientTemplate().update("commonGroup.updateCommonGroupVersionSort", paramsMap);
	}
}
