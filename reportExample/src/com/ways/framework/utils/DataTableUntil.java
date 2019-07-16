package com.ways.framework.utils;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;


public class DataTableUntil {
	
	public static Map<String,Object> getParamByPage(HttpServletRequest request){
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		String limit = request.getParameter("iDisplayLength");//一页有多少条记录
		String start = request.getParameter("iDisplayStart");//开始索引值
		
		String order = null;
		String iColumnsStr = request.getParameter("iColumns");
		String iSortCol_0 = request.getParameter("iSortCol_0");
		String sSortDir_0 = request.getParameter("sSortDir_0");
		if(null != iColumnsStr && null != iSortCol_0 && null != sSortDir_0){
			String mDataProp = request.getParameter("mDataProp_"+iSortCol_0);
			String bSortable = request.getParameter("bSortable_"+iSortCol_0);
			if(mDataProp != null && bSortable != null && bSortable.equals("true") ){
				order = " order by "+mDataProp+ " "+ sSortDir_0 ;
			}
		}
		
		paramsMap.put("start", start != null && !"".equals(start) ? Integer.parseInt(start) : 0);
		paramsMap.put("limit", limit != null && !"".equals(limit) ? Integer.parseInt(limit) + (start != null && !"".equals(start) ? Integer.parseInt(start) : 0) : 0);
		paramsMap.put("order",order);
		
		return paramsMap;
	}
	
	/**
	 * 获取表格字符串参数
	 * @param request
	 * @return
	 */
	public static Map<String,Object> getParamByPage(HttpServletRequest request,Map<String,Object> paramsMap)
	{
		String limit = request.getParameter("iDisplayLength");//一页有多少条记录
		String start = request.getParameter("iDisplayStart");//开始索引值
		String order = null;
		String iColumnsStr = request.getParameter("iColumns");
		String iSortCol_0 = request.getParameter("iSortCol_0");
		String sSortDir_0 = request.getParameter("sSortDir_0");
		
		if(null != iColumnsStr && null != iSortCol_0 && null != sSortDir_0)
		{
			String mDataProp = request.getParameter("mDataProp_"+iSortCol_0);
			String bSortable = request.getParameter("bSortable_"+iSortCol_0);
			if(mDataProp != null && bSortable != null && bSortable.equals("true") ) order = " order by "+mDataProp+ " "+ sSortDir_0;
		}
		
		paramsMap.put("start", !AppFrameworkUtil.isEmpty(start) ? start : "0");
		paramsMap.put("limit", !AppFrameworkUtil.isEmpty(limit) ? Integer.parseInt(limit) + (!AppFrameworkUtil.isEmpty(start) ? Integer.parseInt(start) : 0)+"" : "0");
		paramsMap.put("order",order);
		
		return paramsMap;
	}
	
	public static String getCell(Object val)
	{
		if(val == null) return "";
		else return val.toString();
	}
}
