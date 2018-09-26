package com.ways.app.profit.service.impl;

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

import com.ways.app.profit.dao.IVProfitDao;
import com.ways.app.profit.model.VProfit;
import com.ways.app.profit.service.IVProfitManager;
import com.ways.framework.utils.AppFrameworkUtil;
import com.ways.framework.utils.Constant;
import com.ways.framework.utils.ExportExcelUtil;

@Repository("vProfitManagerImpl")
public class VProfitManagerImpl implements IVProfitManager {
	@Autowired
	private IVProfitDao ivProfitDao;

	@Override
	public void initData(HttpServletRequest request,Map<String, Object> paramsMap) {
		//初始化获取时间范围
		List<Map<String, String>> dateList = ivProfitDao.initDate(paramsMap);
		//初始化获取城市Mix年份
		List<Map<String, String>> cityList = ivProfitDao.findPurchaseCity(paramsMap);

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
	public String findFawVProfit(HttpServletRequest request,Map<String, Object> paramsMap) {
		String json = "{}";
		int count = 0;
		// 定义结果结序列化MAP
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<VProfit> list = ivProfitDao.getFawVProfit(paramsMap);
			if (null != list && 0 != list.size())
				count = list.get(0).getTotalCount();

			resultMap.put("iTotalRecords", count);
			resultMap.put("iTotalDisplayRecords", count);
			resultMap.put("sEcho", paramsMap.get("sEcho"));
			resultMap.put("aaData", list);
			json = AppFrameworkUtil.serializableJSONData(resultMap);
			request.getSession().setAttribute(Constant.getExportExcelVProfitParams, paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 导出
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Workbook exportExcel(HttpServletRequest request,Map<String, Object> paramsMap) {
		Workbook wb = new HSSFWorkbook();
		Sheet s = wb.createSheet("型号利润-报告");
		paramsMap = (Map<String, Object>) request.getSession().getAttribute(Constant.getExportExcelVProfitParams);
		paramsMap.remove("start");
		List<VProfit> dataList = ivProfitDao.getFawVProfit(paramsMap);

		String[] vTitles = null;// 标题数组
		vTitles = new String[] { "年月", "批次", "生产商", "品牌", "子车型", "细分市场大类", "细分市场",
				"车身形势", "型号名称", "型号标识", "型号编码", "指导价" , "返利", "考核奖励", 
				"促销", "经销商开票价", "加权成交价", "经销商利润", "销量"};

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

		// 写数据
		for (int j = 0; j < dataList.size(); j++) {
			row = ExportExcelUtil.createRow(s, ++rowIndex, 600);
			VProfit vProfit = dataList.get(j);
			
			int cellIndex = 0;
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,vProfit.getYm(), textStyle);

			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell, vProfit.getWeek(),textStyle);

			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell, vProfit.getManfNameCn(),textStyle);

			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,vProfit.getBrandNameCn(), textStyle);

			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,vProfit.getManfNameCn(), textStyle);

			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,vProfit.getSegmentParentName(), textStyle);
			
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,vProfit.getSegmentNameCn(), textStyle);

			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,vProfit.getBodyTypeNameCn(), textStyle);

			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,vProfit.getVersionNameCn(), textStyle);

			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,vProfit.getVersionShortNameEn(), textStyle);

			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,vProfit.getVersionCode()+"~", textStyle);

			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,vProfit.getMsrp(), thousandthStyle);

			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,vProfit.getRebatePrice(),thousandthStyle);
			
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,vProfit.getRewardAssessment(),thousandthStyle);
			
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,vProfit.getPromotionalAllowance(),thousandthStyle);
			
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,vProfit.getInvoicePrice(),thousandthStyle);
			
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,vProfit.getPrice(),thousandthStyle);
			
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,vProfit.getModelProfit(),thousandthStyle);
			
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,vProfit.getSale(),thousandthStyle);
			
		}
		s.setDisplayGridlines(false);
		return wb;
	}
}
