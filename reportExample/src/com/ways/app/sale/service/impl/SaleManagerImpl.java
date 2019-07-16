package com.ways.app.sale.service.impl;

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

import com.ways.app.carNews.dao.CarNewsFawDao;
import com.ways.app.profit.model.VProfit;
import com.ways.app.sale.dao.SaleFawDao;
import com.ways.app.sale.model.SaleDetail;
import com.ways.app.sale.service.SaleManager;
import com.ways.framework.utils.AppFrameworkUtil;
import com.ways.framework.utils.Constant;
import com.ways.framework.utils.ExportExcelUtil;

@Repository("saleManagerImpl")
public class SaleManagerImpl implements SaleManager {
	@Autowired
	private SaleFawDao saleFawDao;
	@Autowired
	private CarNewsFawDao carNewsFawDao;

	@Override
	public void initData(HttpServletRequest request,Map<String, Object> paramsMap) {
		//初始化获取时间范围
		List<Map<String, String>> dateList = saleFawDao.initDate(paramsMap);
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
	public String findSaleInfo(HttpServletRequest request,Map<String, Object> paramsMap) {
		String json = "{}";
		int count = 0;
		// 定义结果结序列化MAP
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<SaleDetail> list = saleFawDao.findSaleInfo(paramsMap);
			if (null != list && 0 != list.size())
				count = list.get(0).getTotalCount();

			resultMap.put("iTotalRecords", count);
			resultMap.put("iTotalDisplayRecords", count);
			resultMap.put("sEcho", paramsMap.get("sEcho"));
			resultMap.put("aaData", list);
			json = AppFrameworkUtil.serializableJSONData(resultMap);
			request.getSession().setAttribute(Constant.getExportExcelSaleParams, paramsMap);
//			request.getSession().setAttribute(Constant.getExportExcelCarNewsData, AppFrameworkUtil.structureConfigParamsGroupJSONData(list));
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
		Sheet s = wb.createSheet("型号销量-报告");
		paramsMap = (Map<String, Object>) request.getSession().getAttribute(Constant.getExportExcelSaleParams);
		paramsMap.remove("start");
		List<SaleDetail> dataList = saleFawDao.findSaleInfo(paramsMap);

		String[] vTitles = null;// 标题数组
		vTitles = new String[] { "上市时间", "型号编码", "年", "月", "型号名称", "型号销量",
				"生产商", "子车型", "品牌", "销量说明", "是否自加销量" , "指导价"};

		int rowIndex = 0;// 行号锁引
		Row row = ExportExcelUtil.createRow(s, rowIndex, 600);
		Cell cell = null;
		CellStyle titleStyle = ExportExcelUtil.getExcelTitleBackgroundStyle(wb);// 表格标题样式
		CellStyle textStyle = ExportExcelUtil.getExcelFillTextStyle(wb);// 内容文本样式
		CellStyle decimal = ExportExcelUtil.getExcelDecimalStyle(wb);
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
			SaleDetail sale = dataList.get(j);
			
			int cellIndex = 0;
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,sale.getLaunchDate(), textStyle);

			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell, sale.getVersionCode()+"~",textStyle);

			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell, sale.getYear(),textStyle);

			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,sale.getMonth(), textStyle);

			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,sale.getVersionName(), textStyle);

			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,sale.getSale()+"", textStyle);

			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,sale.getManfName(), textStyle);

			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,sale.getModelName(), textStyle);

			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,sale.getBrandName(), textStyle);

			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,sale.getRemark(), textStyle);
			
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,sale.getIsCustom(), textStyle);

			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell,sale.getMSRP(), thousandthStyle);

		}
		s.setDisplayGridlines(false);
		return wb;
	}
}
