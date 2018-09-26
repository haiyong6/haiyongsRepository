package com.ways.app.moduleLog.dao.impl;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ways.app.moduleLog.dao.IModuleLogDao;
import com.ways.framework.base.dao.impl.BaseDaoImpl;


/**
 * 模块日志记录DAO层接口
 * @author yinlue
 *
 */

@Repository("ModuleLogDaoImpl")
public class ModuleLogDaoImpl extends BaseDaoImpl implements IModuleLogDao {
	

	/**
	 * 添加模块日志记录
	 */
	@Override
	public void addModuleLog(Map<String, Object> paramsMap) 
	{
		try {
			getSqlMapClient().insert("moduleLog.addModuleLog", paramsMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新模块日志记录
	 */
	@Override
	public void updateModuleLog(Map<String, Object> paramsMap)
	{
		try {
			getSqlMapClient().update("moduleLog.updateModuleLog", paramsMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
