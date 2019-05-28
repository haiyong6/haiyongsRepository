package com.ways.app.cityPrice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ways.app.cityPrice.dao.ITpCityDao;
import com.ways.app.cityPrice.model.CityArea;
import com.ways.app.cityPrice.model.FawCityTp;
import com.ways.app.cityPrice.model.FawCityTpPromotions;
import com.ways.app.cityPrice.service.ITpCityManager;
import com.ways.framework.utils.AppFrameworkUtil;
import com.ways.framework.utils.Constant;
import com.ways.framework.utils.ExportExcelUtil;

@Repository("tpCityManagerImpl")
public class TpCityManagerImpl implements ITpCityManager {
	@Autowired
	private ITpCityDao tpCityDao;

	@Override
	public void initData(HttpServletRequest request,Map<String, Object> paramsMap) {
		//初始化获取时间范围
		List<Map<String, String>> dateList = tpCityDao.initDate(paramsMap);
		//初始化获取城市Mix年份
		List<Map<String, String>> cityList = tpCityDao.findPurchaseCity(paramsMap);

		if (null != dateList && dateList.size() > 0) {
			Map<String, String> resultDateMap = dateList.get(0);

			String beginDate = resultDateMap.get("BEGINDATE");
			String endDate = resultDateMap.get("ENDDATE");
			request.setAttribute("beginDate", beginDate);
			request.setAttribute("endDate", endDate);

			// 默认开始时间，时间规则是相差12个点
			String defaultBeginDate = (Integer.parseInt(endDate.replace("-", "")) - 99) + "";
			// 如果结束时间减一年小于开始时间，则默认为开始时间
			if (Integer.parseInt(defaultBeginDate) < Integer.parseInt(beginDate.replace("-", ""))) {
				request.setAttribute("defaultBeginDate", beginDate);
			} else {
				if (Integer.parseInt(defaultBeginDate.substring(4)) > 12) {
					defaultBeginDate = (Integer.parseInt(defaultBeginDate.substring(0, 4)) + 1) + "-01";
				} else {
					defaultBeginDate = defaultBeginDate.substring(0, 4) + "-" + defaultBeginDate.substring(4);
				}
				request.setAttribute("defaultBeginDate", defaultBeginDate);
			}
		}

		if (null != cityList && cityList.size() > 0) {
			request.setAttribute("cityList", cityList);
		}

	}

	@Override
	public String findFawCityTp(final HttpServletRequest request,Map<String, Object> paramsMap) {
		String json = "{}";
		int count = 0;
		// 定义结果结序列化MAP
		Map<String, Object> resultMap = new HashMap<String, Object>();
		request.getSession().setAttribute(Constant.getExportExcelCityTpParams, paramsMap);
		
	//	map.put("qBeginDate", AppFrameworkUtil.getReplaseStr(qBeginDate));
	//	map.put("qEndDate", AppFrameworkUtil.getReplaseStr(qEndDate));
		String qBeginDate=(String)paramsMap.get("qBeginDate");
		String qEndDate=(String)paramsMap.get("qEndDate");
		final String queryParams=Constant.getExportExcelCityTpResult+qBeginDate+qEndDate;
		request.getSession().setAttribute(queryParams,"test");
		
		boolean queryFlag=true;
		
//		Enumeration v=request.getSession().getAttributeNames();//获取所有
//        while(v.hasMoreElements()){
//            String obj  =  (String)v.nextElement();
//           
//            if(queryParams.equals(obj)){
////            	queryFlag=false;
//            }
//        }
        
        if(queryFlag){
	        @SuppressWarnings("unused")
			final Map<String, Object> paramsExport=paramsMap;
	        
			new Thread(new Runnable() {  
	            @Override  
	            public void run() {  
	            	//request.getSession().setAttribute(queryParams,getExportResult(paramsExport,request));
	            }  
	        }).start();  
        }
        
		try {
//			if(request.getSession().getAttribute("getExportExcelCityTpParamss")==null){
			List<FawCityTp> list = null;
			list =
			tpCityDao.getFawCityTp(paramsMap);
			
			List<FawCityTp> listPage = new ArrayList<FawCityTp>();
			if (null != list && 0 != list.size()){
		//		count = list.get(0).getTotalCount();
				count = list.size();
		
				for(int i=0;i<50;i++ ){
					FawCityTp tp=list.get(i);
					tp.setTotalCount(count);
					listPage.add(tp);
				}
			
			}
//			System.out.println("count>>>>>>>>>>>>>>>>>>>>>>>>"+count);
			
			resultMap.put("iTotalRecords", count);
			resultMap.put("iTotalDisplayRecords", count);
			resultMap.put("sEcho", paramsMap.get("sEcho"));
		//	resultMap.put("aaData", list);
			resultMap.put("aaData", listPage);
			json = AppFrameworkUtil.serializableJSONData(resultMap);
//			Thread.sleep(1000);
//			}else{
//				json = (String) request.getSession().getAttribute("getExportExcelCityTpParamss");
//			}
			
//			request.getSession().setAttribute("getExportExcelCityTpParamss", json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/*private List<FawCityTp> getExportResult(Map<String, Object> paramsMap,final HttpServletRequest request){
		String cityYear = (String) paramsMap.get("qDateType");
		String queryYear=Constant.getExportExcelCityTpResult+cityYear;
		
		List<Map<String, String>> cityList=null;
		cityList=(List<Map<String, String>>) request.getSession().getAttribute(queryYear);
		
		if(cityList==null||cityList.size()==0){
			cityList = tpCityDao.findPurchaseCity(cityYear);
			request.getSession().setAttribute(queryYear, cityList);
		}
		
		StringBuffer cityId=new StringBuffer();
		List<String> cityIds=new ArrayList<String>();
		for(Map tempObject:cityList){
			cityId.append(","+tempObject.get("CITYID")+" as C"+tempObject.get("CITYID"));
			cityIds.add("C"+tempObject.get("CITYID"));
		}
		String cityListStr=cityId.toString().replaceFirst(",", "");
		paramsMap.put("cityList", cityListStr);
		
		List<FawCityTp> dataList = tpCityDao.getFawCityTps(paramsMap);
		//System.out.println("dataList.size"+dataList.size());
		return dataList;
	}*/

	/**
	 * 导出
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Workbook exportExcel(HttpServletRequest request,  Map<String, Object> paramsMap) {
		Workbook wb = new HSSFWorkbook();
		Sheet s = wb.createSheet("成交价-报告");
		paramsMap = (Map<String, Object>) request.getSession().getAttribute(Constant.getExportExcelCityTpParams);
		String cityYear = (String) paramsMap.get("qDateType");
		String priceType = request.getParameter("priceTypeId");
		
		paramsMap.put("priceType", priceType);
		String versioncode = "";
		String ym = "";
		String week = "";
		int priceIndex = 43;
		List<FawCityTp> dataList = null;
		String qBeginDate = (String)paramsMap.get("qBeginDate");
		String qEndDate = (String)paramsMap.get("qEndDate");
		String queryParams = Constant.getExportExcelCityTpResult + qBeginDate + qEndDate;
		try {
			dataList=(List<FawCityTp>) request.getSession().getAttribute(queryParams);
		} catch(Exception e) {
			dataList = null;
		}
		String queryYear = Constant.getExportExcelCityTpResult + cityYear;
		
		List<Map<String, String>> cityList = null;
		cityList = (List<Map<String, String>>) request.getSession().getAttribute(queryYear);
		
		if(cityList == null || cityList.size() == 0) {
			cityList = tpCityDao.findPurchaseCity(cityYear);
			request.getSession().setAttribute(queryYear, cityList);
		}
	//	List<Map<String, String>> cityList = tpCityDao.findPurchaseCity(cityYear);
			
		StringBuffer cityId = new StringBuffer();
		List<String> cityIds = new ArrayList<String>();
		StringBuffer cityId1 = new StringBuffer();
		for(@SuppressWarnings("rawtypes") Map tempObject:cityList) {
			cityId.append("," + tempObject.get("CITYID") + " as C" + tempObject.get("CITYID"));
			cityIds.add("C" + tempObject.get("CITYID"));
			cityId1.append("," + tempObject.get("CITYID"));
		}
		String cityListStr = cityId.toString().replaceFirst(",", "");
		String cityListStr1 = cityId1.toString().replaceFirst(",", "");
		paramsMap.put("cityList", cityListStr);
		paramsMap.put("cityList2", cityListStr1);
		paramsMap.remove("start");
		if(dataList == null || dataList.size() == 0) {		
		dataList = tpCityDao.getFawCityTps(paramsMap);	
		}
		List<FawCityTpPromotions> list =tpCityDao.getPromotionsData(paramsMap);
		
		paramsMap.put("cityYear", cityYear);
		List<CityArea> cityAreaList = tpCityDao.getCityArea(paramsMap);//获取城市大区
		//把各城市的内部促销信息按照唯一标识拼接起来
		String key = list.get(0).getKey();
		Map<String,String> map = new HashMap<String,String>();
		String promotions = "";
		for(int i = 0; i < list.size(); i++ ){
			if(list.get(i).getKey().equals(key)){
				if(list.get(i).getPromotions() != null){
					promotions += list.get(i).getPromotions() + "/";
				} 
			} else{
				map.put(key,promotions);
				key = list.get(i).getKey();
				promotions = "";
				
				if(list.get(i).getPromotions() != null){
					promotions += list.get(i).getPromotions() + "/";
				}
			}
			//最后一条加入map
			if(i == list.size()-1){
				map.put(key, promotions);
			}
		}
		String[] vTitles = null;// 标题数组
		vTitles = new String[] { "车型编码", "日期", "批次", "级别大类", "级别", "厂商名称", "品牌","车型名称", "排量","燃料类型",
				"排挡方式", "型号标识", "型号全称", "上市日期","停产日期", "车身形式", "厂商指导价（元）" };
		priceIndex = vTitles.length + cityList.size()-8;
		int rowIndex = 0;// 行号锁引
		Row row = ExportExcelUtil.createRow(s, rowIndex, 600);
		Cell cell = null;
		CellStyle titleStyle = ExportExcelUtil.getExcelTitleBackgroundStyle(wb);// 表格标题样式
		CellStyle textStyle = ExportExcelUtil.getExcelFillTextStyle(wb);// 内容文本样式
		CellStyle thousandthStyle = ExportExcelUtil.getExcelFormatThousandthStyle(wb);// 格式化千分位样式
		// 写型号属性表格标题
		for (int i = 0; i < vTitles.length; i++) {
			cell = row.createCell(i);
			ExportExcelUtil.setCellValueAndStyle(cell, vTitles[i], titleStyle);
			s.setColumnWidth(i, 4500);
		}
		// 写城市表格标题
		for (int i = 0; i < cityList.size(); i++) {
			//2017年3月24日新需求，把长春放到加权价的后面
			if(!cityList.get(i).get("CITYNAME").equals("长春")
					&& !cityList.get(i).get("CITYNAME").equals("厦门")
					&& !cityList.get(i).get("CITYNAME").equals("洛阳")
					&& !cityList.get(i).get("CITYNAME").equals("苏州")
					&& !cityList.get(i).get("CITYNAME").equals("西宁")
					&& !cityList.get(i).get("CITYNAME").equals("佛山")
					&& !cityList.get(i).get("CITYNAME").equals("温州")
					&& !cityList.get(i).get("CITYNAME").equals("运城")){
				cell = row.createCell(i + vTitles.length);
			   // System.out.println(i + a +" : "+cityList.get(i).get("CITYNAME"));
				if("0".equals(priceType)){
					ExportExcelUtil.setCellValueAndStyle(cell,cityList.get(i).get("CITYNAME") + "最低参考价(元）", titleStyle);
				}else if("1".equals(priceType)){
					ExportExcelUtil.setCellValueAndStyle(cell,cityList.get(i).get("CITYNAME") + "市场价(元）", titleStyle);
				}else{
					ExportExcelUtil.setCellValueAndStyle(cell,cityList.get(i).get("CITYNAME") + "一汽大众TP(元）", titleStyle);

				}
				
				s.setColumnWidth(i + vTitles.length, 20*256);
			}
		
			
		}
		// 城市加权列
		cell = row.createCell(vTitles.length + cityList.size()-8);
		if(cityList.size()>25){
			if("0".equals(priceType)){
				ExportExcelUtil.setCellValueAndStyle(cell, "30城市最低价加权均价（元）", titleStyle);
			}else if("1".equals(priceType)){
//			cell = row.createCell(vTitles.length + cityList.size()+1);
				ExportExcelUtil.setCellValueAndStyle(cell, "30城市市场价加权均价（元）", titleStyle);
			}else{
				ExportExcelUtil.setCellValueAndStyle(cell, "30城市一汽大众TP加权均价（元）", titleStyle);
			}
			s.setColumnWidth(vTitles.length + cityList.size()-8, 20*256);
		}else{
			if("0".equals(priceType)){
				ExportExcelUtil.setCellValueAndStyle(cell, "23城市最低价加权均价（元）", titleStyle);
			}else if("1".equals(priceType)){
//			cell = row.createCell(vTitles.length + cityList.size()+1);
				ExportExcelUtil.setCellValueAndStyle(cell, "23城市市场价加权均价（元）", titleStyle);
			}else{
				ExportExcelUtil.setCellValueAndStyle(cell, "23城市一汽大众TP加权均价（元）", titleStyle);
			}
			s.setColumnWidth(vTitles.length + cityList.size()-8, 4500);
		}
		
		//城市大区列标题
		for (int i = 0; i < cityAreaList.size(); i++) {
			cell = row.createCell(vTitles.length + cityList.size()-7 + i);
				if("0".equals(priceType)){
					ExportExcelUtil.setCellValueAndStyle(cell,cityAreaList.get(i).getAreaName() + "最低参考价(元）", titleStyle);
				}else if("1".equals(priceType)){
					ExportExcelUtil.setCellValueAndStyle(cell,cityAreaList.get(i).getAreaName() + "市场价(元）", titleStyle);
				}else{
					ExportExcelUtil.setCellValueAndStyle(cell,cityAreaList.get(i).getAreaName() + "一汽大众TP(元）", titleStyle);
				}
				
				s.setColumnWidth(vTitles.length + cityList.size()-7 + i, 20*256);
		}
		
		int p = vTitles.length + cityList.size()-7 + cityAreaList.size();
		//s.setColumnWidth(vTitles.length + cityList.size()-1, 4500);
		
		//长春列
		
		for (int i = 0; i < cityList.size(); i++){
			if(cityList.get(i).get("CITYNAME").equals("长春") || "西宁".equals(cityList.get(i).get("CITYNAME"))
					||"苏州".equals(cityList.get(i).get("CITYNAME"))
					||"温州".equals(cityList.get(i).get("CITYNAME"))
					||"厦门".equals(cityList.get(i).get("CITYNAME"))
					||"佛山".equals(cityList.get(i).get("CITYNAME"))
					||"运城".equals(cityList.get(i).get("CITYNAME"))
					||"洛阳".equals(cityList.get(i).get("CITYNAME"))
					){
				cell = row.createCell(p);
				if("0".equals(priceType)){
					ExportExcelUtil.setCellValueAndStyle(cell,cityList.get(i).get("CITYNAME") + "最低参考价(元）", titleStyle);
				}else if("1".equals(priceType)){
					ExportExcelUtil.setCellValueAndStyle(cell,cityList.get(i).get("CITYNAME") + "市场价(元）", titleStyle);
				}else{
					ExportExcelUtil.setCellValueAndStyle(cell,cityList.get(i).get("CITYNAME") + "一汽大众TP(元）", titleStyle);
				}
				
				s.setColumnWidth(p, 20*256);
				p++;
			} 
			
		}
		
		//内部促销信息列
		cell = row.createCell(vTitles.length + cityList.size() + cityAreaList.size() + 1);
		ExportExcelUtil.setCellValueAndStyle(cell, "内部促销信息", titleStyle);
		s.setColumnWidth(vTitles.length + cityList.size() + cityAreaList.size() + 1, 150*256);

		// 写数据
		for (int j = 0; j < dataList.size(); j++) {

			FawCityTp cityTp = dataList.get(j);
			//相同型号,年月,批次的只创建一行再将城市行转列填充至相应行中去
			if (!versioncode.equals(cityTp.getVersionCode())
					|| !ym.equals(cityTp.getYm())
					|| !week.equals(cityTp.getWeek())) {

				row = ExportExcelUtil.createRow(s, ++rowIndex, 600);
				
				for (int i = 0; i < cityList.size(); i++) {
					cell = row.createCell(vTitles.length+i);
					ExportExcelUtil.setCellValueAndStyle(cell, "-",thousandthStyle);
				}
				
				int cellIndex = 0;
				cell = row.createCell(cellIndex++);
				ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getVersionCode() + "~", textStyle);

				cell = row.createCell(cellIndex++);
				ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getYm(), textStyle);

				cell = row.createCell(cellIndex++);
				ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getWeek(), textStyle);

				cell = row.createCell(cellIndex++);
				ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getSegmentParentName(), textStyle);
				
				cell = row.createCell(cellIndex++);
				ExportExcelUtil.setCellValueAndStyle(cell,cityTp.getSegmentNameCn(), textStyle);

				cell = row.createCell(cellIndex++);
				ExportExcelUtil.setCellValueAndStyle(cell,cityTp.getManfNameCn(), textStyle);

				cell = row.createCell(cellIndex++);
				ExportExcelUtil.setCellValueAndStyle(cell,cityTp.getBrandNameCn(), textStyle);
				
				cell = row.createCell(cellIndex++);
				ExportExcelUtil.setCellValueAndStyle(cell,cityTp.getSubmodelNameCn(), textStyle);
				
				cell = row.createCell(cellIndex++);
				ExportExcelUtil.setCellValueAndStyle(cell,cityTp.getEmissionsName(), textStyle);

				cell = row.createCell(cellIndex++);
				ExportExcelUtil.setCellValueAndStyle(cell,cityTp.getFuelTypeName(), textStyle);
				
				cell = row.createCell(cellIndex++);
				ExportExcelUtil.setCellValueAndStyle(cell,cityTp.getTrnsmsNameCn(), textStyle);

				cell = row.createCell(cellIndex++);
				ExportExcelUtil.setCellValueAndStyle(cell,cityTp.getVersionShortNameCn(), textStyle);

				cell = row.createCell(cellIndex++);
				ExportExcelUtil.setCellValueAndStyle(cell,cityTp.getVersionNameCn(), textStyle);


				cell = row.createCell(cellIndex++);
				ExportExcelUtil.setCellValueAndStyle(cell,cityTp.getLaunchDate(), textStyle);
				
				cell = row.createCell(cellIndex++);
				ExportExcelUtil.setCellValueAndStyle(cell,cityTp.getNoProductDate(), textStyle);
				
				cell = row.createCell(cellIndex++);
				ExportExcelUtil.setCellValueAndStyle(cell,cityTp.getBodyTypeNameCn(), textStyle);

				cell = row.createCell(cellIndex++);
				ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getMsrp(),thousandthStyle);
				
				
				for(String temp:cityIds){
					//c47为长春
					if(!temp.equals("C47")
						&&!temp.equals("C44")
						&&!temp.equals("C81")
						&&!temp.equals("C38")
						&&!temp.equals("C70")
						&&!temp.equals("C98")
						&&!temp.equals("C40")
						&&!temp.equals("C50")){
						cell = row.createCell(cellIndex++);
						ExportExcelUtil.setCellValueAndStyle(cell, getMsg(cityTp,temp),thousandthStyle);
					}
				}
				
				if("0".equals(priceType)){
					cell = row.createCell(priceIndex);
					if(AppFrameworkUtil.isEmpty(cityTp.getPrice())){
						ExportExcelUtil.setCellValueAndStyle(cell, "-",textStyle);
					}  else{
						ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getPrice(),thousandthStyle);
					}
				}else if("1".equals(priceType)){
					cell = row.createCell(priceIndex);
					if(AppFrameworkUtil.isEmpty(cityTp.getPrices())){
						ExportExcelUtil.setCellValueAndStyle(cell, "-",textStyle);
					}  else{
						ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getPrices(),thousandthStyle);
					}
				}else{
					cell = row.createCell(priceIndex);
					
					if(AppFrameworkUtil.isEmpty(cityTp.getPriceFawvw())){
						ExportExcelUtil.setCellValueAndStyle(cell, "-",textStyle);
					}  else{
						ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getPriceFawvw(),thousandthStyle);
					}
				} 
				
				//6大区数据
					cell = row.createCell(priceIndex+1);
					if(AppFrameworkUtil.isEmpty(cityTp.getMixAvg1())){
						ExportExcelUtil.setCellValueAndStyle(cell, "-",textStyle);
					}  else{
						ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getMixAvg1(),thousandthStyle);
					}
					
					cell = row.createCell(priceIndex+2);
					if(AppFrameworkUtil.isEmpty(cityTp.getMixAvg2())){
						ExportExcelUtil.setCellValueAndStyle(cell, "-",textStyle);
					}  else{
						ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getMixAvg2(),thousandthStyle);
					}
					
					cell = row.createCell(priceIndex+3);
					if(AppFrameworkUtil.isEmpty(cityTp.getMixAvg3())){
						ExportExcelUtil.setCellValueAndStyle(cell, "-",textStyle);
					}  else{
						ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getMixAvg3(),thousandthStyle);
					}
					
					cell = row.createCell(priceIndex+4);
					if(AppFrameworkUtil.isEmpty(cityTp.getMixAvg4())){
						ExportExcelUtil.setCellValueAndStyle(cell, "-",textStyle);
					}  else{
						ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getMixAvg4(),thousandthStyle);
					}
					
					cell = row.createCell(priceIndex+5);
					if(AppFrameworkUtil.isEmpty(cityTp.getMixAvg5())){
						ExportExcelUtil.setCellValueAndStyle(cell, "-",textStyle);
					}  else{
						ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getMixAvg5(),thousandthStyle);
					}
				
					cell = row.createCell(priceIndex+6);
					if(AppFrameworkUtil.isEmpty(cityTp.getMixAvg6())){
						ExportExcelUtil.setCellValueAndStyle(cell, "-",textStyle);
					}  else{
						ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getMixAvg6(),thousandthStyle);
					}
					
					//长春列
					cell = row.createCell(priceIndex+7);
					if(AppFrameworkUtil.isEmpty(cityTp.getC47())){
						ExportExcelUtil.setCellValueAndStyle(cell, "-",textStyle);
					}  else{
						ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getC47(),thousandthStyle);
					}
					//2017年8月14日新加7个城市
					cell = row.createCell(priceIndex+8);
					if(AppFrameworkUtil.isEmpty(cityTp.getC44())){//苏州
						ExportExcelUtil.setCellValueAndStyle(cell, "-",textStyle);
					}  else{
						ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getC44(),thousandthStyle);
					}
					
					cell = row.createCell(priceIndex+9);
					if(AppFrameworkUtil.isEmpty(cityTp.getC81())){//温州
						ExportExcelUtil.setCellValueAndStyle(cell, "-",textStyle);
					}  else{
						ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getC81(),thousandthStyle);
					}
					
					cell = row.createCell(priceIndex+10);
					if(AppFrameworkUtil.isEmpty(cityTp.getC38())){//厦门
						ExportExcelUtil.setCellValueAndStyle(cell, "-",textStyle);
					}  else{
						ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getC38(),thousandthStyle);
					}
					
					cell = row.createCell(priceIndex+11);
					if(AppFrameworkUtil.isEmpty(cityTp.getC70())){//佛山
						ExportExcelUtil.setCellValueAndStyle(cell, "-",textStyle);
					}  else{
						ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getC70(),thousandthStyle);
					}
					
					cell = row.createCell(priceIndex+12);
					if(AppFrameworkUtil.isEmpty(cityTp.getC98())){//运城
						ExportExcelUtil.setCellValueAndStyle(cell, "-",textStyle);
					}  else{
						ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getC98(),thousandthStyle);
					}
					
					cell = row.createCell(priceIndex+13);
					if(AppFrameworkUtil.isEmpty(cityTp.getC40())){//洛阳
						ExportExcelUtil.setCellValueAndStyle(cell, "-",textStyle);
					}  else{
						ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getC40(),thousandthStyle);
					}
					
					cell = row.createCell(priceIndex+14);
					if(AppFrameworkUtil.isEmpty(cityTp.getC50())){//西宁
						ExportExcelUtil.setCellValueAndStyle(cell, "-",textStyle);
					}  else{
						ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getC50(),thousandthStyle);
					}
					
				versioncode = cityTp.getVersionCode();
				ym = cityTp.getYm();
				week = cityTp.getWeek();
			}
			/***
			//按照城市排序将最低价填充
			cell = row.getCell(vTitles.length-1+cityTp.getCsort());
			if("0".equals(priceType)){
				ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getMinPrice(),thousandthStyle);
			}else if("1".equals(priceType)){
				ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getMinPrices(),thousandthStyle);
			}else{
				ExportExcelUtil.setCellValueAndStyle(cell, cityTp.getMinPriceFawvw(),thousandthStyle);
			}
			**/
			cell = row.createCell(vTitles.length + cityList.size() + cityAreaList.size() + 1);
			CellStyle cs = cell.getCellStyle();
			cs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			//设置边框颜色
			cs.setBorderLeft(CellStyle.SOLID_FOREGROUND);   
			cs.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());//设置下边框颜色
			cs.setBorderBottom(CellStyle.SOLID_FOREGROUND);   
			cs.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());//设置左边框颜色
			cs.setBorderRight(CellStyle.SOLID_FOREGROUND);   
			cs.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());//设置右边框颜色
			ExportExcelUtil.setCellValueAndStyle(cell, map.get(cityTp.getKey()), cs);
		}
		s.setDisplayGridlines(false);
		return wb;
	}
	
	private String getMsg(FawCityTp fawCityTp,String property){
		@SuppressWarnings("rawtypes")
		Class clazz =fawCityTp.getClass();
		String msg=null;
		try {
			@SuppressWarnings("unchecked")
			java.lang.reflect.Method m = clazz.getDeclaredMethod("get"+property);
			msg = (String)m.invoke(fawCityTp);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		if(AppFrameworkUtil.isEmpty(msg))
			return "-";
		 return msg;
	}
	
	
	
}
