package com.ways.app.sale.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.ways.app.sale.dao.SaleFawDao;
import com.ways.app.sale.model.SaleDetail;
import com.ways.framework.base.dao.impl.BaseDaoImpl;
/**
 *成交价报告DaoImpl实现类
 */
@Repository("SaleFawDaoImpl")
public class SaleFawDaoImpl extends BaseDaoImpl implements SaleFawDao {

	@Override
	public List<Map<String, String>> initDate(Map<String, Object> paramsMap) {
		
		List<Map<String,String>> dateList = null;
		try {
			dateList = getSqlMapClient().queryForList("saleFw.initDate",paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateList; 
	}

	@Override
	public List<SaleDetail> findSaleInfo(Map<String, Object> paramsMap) {
		List<SaleDetail> dateList = null;
		try {
			dateList = getSqlMapClient().queryForList("saleFw.findSaleInfo",paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateList; 
	}
}
