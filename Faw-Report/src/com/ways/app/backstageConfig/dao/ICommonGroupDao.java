package com.ways.app.backstageConfig.dao;

import java.util.List;
import java.util.Map;

import com.ways.app.backstageConfig.model.CommonGroup;
import com.ways.app.backstageConfig.model.LetterObj;
import com.ways.app.backstageConfig.model.SubModel;

/**
 * 常用对象管理Dao接口类
 *  
 * @author songguobiao
 * 20160401
 */
public interface ICommonGroupDao {
	
	/**
	 * 获取常用对象数据
	 * 
	 * @param paramsMap
	 * @return
	 */
    public List<CommonGroup> getCommonGroupData(Map<String, Object> paramsMap);
    
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
	 * 获取子车型下型号数据
	 * @param paramsMap
	 * @return
	 */
	public List<SubModel> getVersionModal(Map<String, Object> paramsMap);
	
	/**
	 * 删除常用对象组
	 * 
	 * @param paramsMap
	 * @return
	 */
	public int deleteCommonGroup(Map<String, Object> paramsMap);
	
	/**
	 * 添加常用对象组(分组信息)--通过导入文件数据方式
	 * 
	 * @param paramsMap
	 * @return 
	 */
	public int addCommonGroupByImport(Map<String, Object> paramsMap);
	
	/**
	 * 添加常用对象组(分组型号信息)--通过导入文件数据方式
	 * 
	 * @param commonGroup
	 * @return 
	 */
	public int addCommonGroupVersionByImport(CommonGroup commonGroup);
	
	/**
	 * 添加常用对象组(分组信息)--通过页面新增方式
	 * 
	 * @param commonGroup
	 * @return 
	 */
	public int addCommonGroupByAdd(CommonGroup commonGroup);
	
	/**
	 * 添加常用对象组(分组型号信息)--通过页面新增方式
	 * 
	 * @param commonGroup
	 * @return 
	 */
	public int addCommonGroupVersionByAdd(CommonGroup commonGroup);
	
	/**
	 * 删除常用对象组分组信息(修改)
	 * 
	 * @param paramsMap
	 * @return
	 */
	public int deleteCommonGroupByUpdate(Map<String, Object> paramsMap);
	
	/**
	 * 添加常用对象组分组信息(修改)
	 * 
	 * @param commonGroup
	 * @return
	 */
	public int addCommonGroupByUpdate(CommonGroup commonGroup);
	
	/**
	 * 添加常用对象组分组型号信息(修改)
	 * 
	 * @param paramsMap
	 * @return
	 */
	public int addCommonGroupVersionByUpdate(Map<String, Object> paramsMap);
	
	/**
	 * 根据分组名称获取分组信息(分组信息)
	 * 
	 * @param paramsMap
	 * @return
	 */
	public CommonGroup getBPInfoByGroupName(Map<String, Object> paramsMap);
	
	/**
	 * 根据分组名称获取分组信息(分组型号信息)
	 * 
	 * @param paramsMap
	 * @return
	 */
	public List<CommonGroup> getJPInfoByGroupName(Map<String, Object> paramsMap);
	
	/**
	 * 更新常用对象组的型号排序(分组排序)
	 * 
	 * @param paramsMap
	 * @return
	 */
	public int updateCommonGroupGroupSort(Map<String, Object> paramsMap);
	
	/**
	 * 更新常用对象组的型号排序(型号排序)
	 * 
	 * @param paramsMap
	 * @return
	 */
    public int updateCommonGroupVersionSort(Map<String, Object> paramsMap);
    
}
