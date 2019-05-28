package com.ways.app.basePeriodTPConfig.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.ways.app.basePeriodTPConfig.dao.IBasePeriodTPDao;
import com.ways.app.basePeriodTPConfig.model.BasePeriodTP;
import com.ways.framework.base.dao.impl.BaseDaoImpl;

/**
 * 常用对象管理Dao实现类
 *  
 * @author songguobiao
 * 20160401
 */
@Repository("BasePeriodTPDaoImpl")
public class BasePeriodTPDaoImpl extends BaseDaoImpl implements IBasePeriodTPDao {
	protected final Log log = LogFactory.getLog(BasePeriodTPDaoImpl.class);
	
	@Override
	public List<Map<String, String>> initDate(Map<String, Object> paramsMap) {
		List<Map<String, String>> dateList = null;
        try {
		    dateList = getSqlMapClient().queryForList("basePeriodTP.initDate", paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateList; 
	}
	
	@SuppressWarnings("unchecked")
	@Override
    public List<BasePeriodTP> getBasePeriodTPData(Map<String, Object> paramsMap) {
    	List<BasePeriodTP> basePeriodTPList = null;
		try {
		    if("YES".equals(paramsMap.get("downLoadErrorData"))) {
				basePeriodTPList = getSqlMapClient().queryForList("basePeriodTP.getBasePeriodTPErrorData", paramsMap);
			} else {
				basePeriodTPList = getSqlMapClient().queryForList("basePeriodTP.getBasePeriodTPData", paramsMap);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return basePeriodTPList; 
    }
	
	@SuppressWarnings("unchecked")
	@Override
    public List<BasePeriodTP> getCityBasePeriodTPData(Map<String, Object> paramsMap) {
    	List<BasePeriodTP> cityBasePeriodTPList = null;
		try {
			if("YES".equals(paramsMap.get("downLoadErrorData"))) {
				cityBasePeriodTPList = getSqlMapClient().queryForList("basePeriodTP.getCityBasePeriodTPErrorData", paramsMap);
			} else {
				cityBasePeriodTPList = getSqlMapClient().queryForList("basePeriodTP.getCityBasePeriodTPData", paramsMap);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return cityBasePeriodTPList; 
    }
	
	@Override
	public int addBasePeriodTPByImport(Map<String, Object> paramsMap) {
		int result = 0;
		try {
			getSqlMapClientTemplate().update("basePeriodTP.delBasePeriodTPByImport", paramsMap);
			result = (Integer)getSqlMapClientTemplate().update("basePeriodTP.addBasePeriodTPByImport", paramsMap);
		} catch(Exception e) {
			addBasePeriodTPError(paramsMap);
	//		System.out.println("---->versionCode:"+paramsMap.get("versionCode")+"   year:"+paramsMap.get("year"));
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public int addCityBasePeriodTPByImport(Map<String, Object> paramsMap) {
		int result = 0;
		int count = 0;//先判断数据库中有无此数据
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>(); 
		try {
			list = getSqlMapClientTemplate().queryForList("basePeriodTP.selectCityBasePeriodTPByImport",paramsMap);
			  if(null != list.get(0).get("COUNT") && !"".equals(list.get(0).get("COUNT"))){
				  count = Integer.parseInt(list.get(0).get("COUNT").toString());
			  }
			getSqlMapClientTemplate().update("basePeriodTP.delCityBasePeriodTPByImport", paramsMap);
			
			result = (Integer)getSqlMapClientTemplate().update("basePeriodTP.addCityBasePeriodTPByImport", paramsMap);
		} catch(Exception e) {
			addCityBasePeriodTPError(paramsMap);
	//		System.out.println("---->versionCode:"+paramsMap.get("versionCode")+"   year:"+paramsMap.get("year"));
			e.printStackTrace();
		}
		return result - count;
	}
	
	@Override
	public void delBasePeriodTPError() {
		try {
			getSqlMapClient().delete("basePeriodTP.delBasePeriodTPError");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addBasePeriodTPError(Map<String, Object> paramsMap) {
		try {
			getSqlMapClient().update("basePeriodTP.addBasePeriodTPError", paramsMap);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addCityBasePeriodTPError(Map<String, Object> paramsMap) {
		try {
			getSqlMapClient().update("basePeriodTP.addCityBasePeriodTPError", paramsMap);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delCityBasePeriodTPError() {
		try {
			getSqlMapClient().delete("basePeriodTP.delCityBasePeriodTPError");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
