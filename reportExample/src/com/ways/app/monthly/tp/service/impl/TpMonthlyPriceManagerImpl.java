package com.ways.app.monthly.tp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ways.app.monthly.tp.dao.ITpMonthlyPriceDao;
import com.ways.app.monthly.tp.model.FawTp;
import com.ways.app.monthly.tp.service.ITpMonthlyPriceManager;
import com.ways.framework.utils.AppFrameworkUtil;

@Service("tpMonthlyPriceManagerImpl")
public class TpMonthlyPriceManagerImpl implements ITpMonthlyPriceManager {
	@Autowired
	private ITpMonthlyPriceDao tpMonthlyPriceDao;
	@Override
	public String findTpMonthlyPriceInfo(Map<String, Object> paramsMap) {
		String json = "{}";
		int count = 0;
		//定义结果结序列化MAP
		Map<String, Object> resultMap = new HashMap<String,Object>();
		try {
			List<FawTp> list = tpMonthlyPriceDao.findTpMonthlyPriceInfo(paramsMap);
			if(null != list && 0 != list.size()) count = list.get(0).getTotalCount();
			
			resultMap.put("iTotalRecords", count);
			resultMap.put("iTotalDisplayRecords", count);
			resultMap.put("sEcho", paramsMap.get("sEcho"));
			resultMap.put("aaData", list);
			json = AppFrameworkUtil.serializableJSONData(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

}
