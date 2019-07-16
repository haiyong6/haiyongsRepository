package com.ways.app.monthly.tp.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ways.app.monthly.tp.dao.ITpMonthlyPriceDao;
import com.ways.app.monthly.tp.model.FawTp;
import com.ways.framework.base.dao.impl.BaseDaoImpl;

@Repository("tpMonthlyPriceImpl")
public class TpMonthlyPriceImpl extends BaseDaoImpl implements ITpMonthlyPriceDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<FawTp> findTpMonthlyPriceInfo(Map<String, Object> paramsMap) {
		List<FawTp>fawTpList = null;
		try {
			fawTpList = getSqlMapClient().queryForList("tpMonthly.getFawTp",paramsMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fawTpList;
	}

}
