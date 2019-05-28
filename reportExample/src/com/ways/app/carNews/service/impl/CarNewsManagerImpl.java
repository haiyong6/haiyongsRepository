package com.ways.app.carNews.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ways.app.carNews.dao.CarNewsDao;
import com.ways.app.carNews.dao.CarNewsFawDao;
import com.ways.app.carNews.model.carNewsDetail;
import com.ways.app.carNews.service.CarNewsManager;
import com.ways.framework.utils.AppFrameworkUtil;
import com.ways.framework.utils.Constant;
import com.ways.framework.utils.ExportExcelUtil;

@Repository("carNewsManagerImpl")
public class CarNewsManagerImpl implements CarNewsManager {
	@Autowired
	private CarNewsDao carNewsDao;
	@Autowired
	private CarNewsFawDao carNewsFawDao;

	@Override
	public void initData(HttpServletRequest request,Map<String, Object> paramsMap) {
		//初始化获取时间范围
		List<Map<String, String>> dateList = carNewsDao.initDate(paramsMap);
		//初始化获取城市Mix年份
//		List<Map<String, String>> cityList = tpCityDao.findPurchaseCity(paramsMap);

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

//		if (null != cityList && cityList.size() > 0) {
//			request.setAttribute("cityList", cityList);
//		}

	}

	@Override
	public String findNewCarsInfo(HttpServletRequest request,Map<String, Object> paramsMap) {
		String json = "{}";
		int count = 0;
		// 定义结果结序列化MAP
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<carNewsDetail> list = carNewsDao.findNewCarsInfo(paramsMap);
			if (null != list && 0 != list.size())
				count = list.get(0).getTotalCount();

			resultMap.put("iTotalRecords", count);
			resultMap.put("iTotalDisplayRecords", count);
			resultMap.put("sEcho", paramsMap.get("sEcho"));
			resultMap.put("aaData", list);
			json = AppFrameworkUtil.serializableJSONData(resultMap);
			request.getSession().setAttribute(Constant.getExportExcelCarNewsParams, paramsMap);
//			request.getSession().setAttribute(Constant.getExportExcelCarNewsData, AppFrameworkUtil.structureConfigParamsGroupJSONData(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@Override
	public void getSubmodelModal(HttpServletRequest request, Map<String, Object> paramsMap) 
	{	
		Object subModelShowType = paramsMap.get("subModelShowType");
		try {
	    	if("2".equals(subModelShowType)){
				//返回细分市场页
	    		request.setAttribute("segmentList", carNewsFawDao.getSubmodelBySegment(paramsMap));
	    	}else if("3".equals(subModelShowType)){
	    		//返回品牌页
		    	request.setAttribute("brandLetterList", carNewsFawDao.getSubmodelByBrand(paramsMap));
	    	}else if("4".equals(subModelShowType)){
	    		//返回厂商页
		    	request.setAttribute("manfLetterList", carNewsFawDao.getSubmodelByManf(paramsMap));
	    	}else{
	    		//本品页竟品页
		    	request.setAttribute("bpSubModelList", carNewsFawDao.getSubmodelByBp(paramsMap));
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Workbook exportExcel(HttpServletRequest request,
			Map<String, Object> paramsMap) {
			Workbook wb = null;
			paramsMap = (Map<String, Object>) request.getSession().getAttribute(Constant.getExportExcelCarNewsParams);
			paramsMap.remove("start");
			List<carNewsDetail> list = carNewsDao.findNewCarsInfo(paramsMap);
			String json = AppFrameworkUtil.structureConfigParamsGroupJSONData(list);
			String path = request.getSession().getServletContext().getRealPath("/"); 
			if(!AppFrameworkUtil.isEmpty(json))
			{
				JSONArray obj = (JSONArray) JSONObject.parse(json);
				try {
					wb = new HSSFWorkbook(new FileInputStream(new File(path+"excelExample/新车上市及指导价调整快讯.xls")));
					exportOriginalData(wb,wb.getSheet("新车快讯报告"),obj,paramsMap);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(null == wb){
				try{
					wb = new HSSFWorkbook(new FileInputStream(new File(path+"excelExample/新车上市及指导价调整快讯.xls")));
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			return wb;
		}
	
	private void exportOriginalData(Workbook wb,Sheet s,JSONArray gridObj,Map<String, Object> paramsMap){
		
		String languageType = (String) paramsMap.get("languageType");//语言类型ZH,EN
		String[] titles = null;//标题数组

		int rowIndex = 0;//行号索引
		
		Row row = null;
		Cell cell = null;
		CellStyle textStyle = ExportExcelUtil.getExcelFillTextStyle(wb);//内容文本样式
//		CellStyle textRedStyle= ExportExcelUtil.getExcelFillTextStyle(wb,"RED");//内容文本样式
		CellStyle textPercentStyle = ExportExcelUtil.getExcelFillPercentageStyle(wb);//
//		CellStyle textPercentRedStyle = ExportExcelUtil.getExcelFillPercentageRedStyle(wb);//红色
		
		CellStyle thousandthStyle = ExportExcelUtil.getExcelFormatThousandthStyle(wb);//格式化千分位样式
//		CellStyle thousandthREDStyle =ExportExcelUtil.getExcelFormatThousandthREDStyle(wb);
		
		//写表格数据内容
		for(int j = 0; j < gridObj.size(); j++)
		{
			JSONObject obj = gridObj.getJSONObject(j);
			rowIndex++;
			row = ExportExcelUtil.createRow(s, rowIndex, 400);
			int i=0;
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell, obj.getString("launchDate")+"~", textStyle);

			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell, obj.getString("versionCode")+"~", textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell,obj.getString("brandName"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell,obj.getString("brandNameEn"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell, obj.getString("manfName"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell,obj.getString("manfNameEn"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell,obj.getString("segment"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell, obj.getString("modelName"), textStyle);
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell,obj.getString("modelNameEn"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell,obj.getString("trim"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell, obj.getString("trimEn"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell, obj.getString("versionName"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell,obj.getString("versionNameEn"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell, obj.getString("MSRP"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell,  obj.getString("preVersionName"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell, obj.getString("preMSRP"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell, obj.getString("bodyType"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell, obj.getString("bodyTypeEn"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell, obj.getString("driveType"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell, obj.getString("driveTypeEn"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell, obj.getString("carIn"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell, obj.getString("carInEn"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell,obj.getString("changeDescription"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell, obj.getString("changeDescriptionEn"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell,  obj.getString("LWH"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell,obj.getString("wheelbase"), thousandthStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell,obj.getString("PL"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell,obj.getString("transmission"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell,obj.getString("maximumPower"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell,obj.getString("maximumTorque"), textStyle);
		
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell,obj.getString("KWL"), textStyle);
			
			cell = row.createCell(i++);
			ExportExcelUtil.setCellValueAndStyle(cell,obj.getString("sellingPoints"), textStyle);
			
		}
		
	}
	

	@Override
	public void getBodyTypeList(HttpServletRequest request,
			Map<String, Object> paramsMap) {
		request.setAttribute("bodyTypeList", carNewsFawDao.getBodyType(paramsMap));
	}
}
