package com.ways.app.basePeriodTPConfig.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ways.app.basePeriodTPConfig.dao.IBasePeriodTPDao;
import com.ways.app.basePeriodTPConfig.model.BasePeriodTP;
import com.ways.app.basePeriodTPConfig.service.IBasePeriodTPManager;
import com.ways.framework.utils.AppFrameworkUtil;
import com.ways.framework.utils.Constant;
import com.ways.framework.utils.ExportExcelUtil;

/**
 * 常用对象管理Service实现类 
 * 
 * @author songguobiao
 * 20160401
 */
@Repository("basePeriodTPManagerImpl")
public class BasePeriodTPManagerImpl implements IBasePeriodTPManager {
	@Autowired
	private IBasePeriodTPDao basePeriodTPDao;
	
	@Override
	public void initDate(HttpServletRequest request, Map<String, Object> paramsMap) {
		//初始化获取时间范围
	    List<Map<String, String>> dateList = basePeriodTPDao.initDate(paramsMap);
		if(null != dateList && dateList.size() > 0) {
			Map<String, String> resultDateMap = dateList.get(0);
			String beginDate = String.valueOf(resultDateMap.get("BEGINDATE"));
			String endDate = String.valueOf(resultDateMap.get("ENDDATE"));
			request.setAttribute("beginDate", beginDate);
			request.setAttribute("endDate", endDate);
		}
	}
	
	/**
	 * 获取常用对象数据
	 * 
	 * @param request
	 * @param paramsMap
	 * @return
	 */
	@Override
	public String getBasePeriodTPData(HttpServletRequest request, Map<String, Object> paramsMap) {
		String json = "{}";
		int count = 0;
		// 定义结果结序列化MAP
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<BasePeriodTP> list = basePeriodTPDao.getBasePeriodTPData(paramsMap);
			if(null != list && 0 != list.size()) {
				count = list.get(0).getTotalCount();
			}
			resultMap.put("iTotalRecords", count);
			resultMap.put("iTotalDisplayRecords", count);
			resultMap.put("aaData", list);
			json = AppFrameworkUtil.serializableJSONData(resultMap);
			request.getSession().setAttribute(Constant.getExportExcelBasePeriodTPParams, paramsMap);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 获取常用对象
	 * 
	 * @return
	 */
	@Override
	public String getBasePeriodTP(Map<String, Object> paramsMap) {
		String json = "{}";
		// 定义结果结序列化MAP
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<BasePeriodTP> groupList = basePeriodTPDao.getBasePeriodTPData(paramsMap);
			resultMap.put("groupList", groupList);
			json = AppFrameworkUtil.serializableJSONData(resultMap);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 导出
	 * 
	 * @param request
	 * @param paramsMap
	 * @return 
	 */
	@Override
	public Workbook exportExcel(HttpServletRequest request, Map<String, Object> paramsMap) {
		String isCityBase = "";
		if(paramsMap.containsKey("isCityBase")) {
			isCityBase = paramsMap.get("isCityBase").toString();
		}
		
		Workbook wb = new HSSFWorkbook();
		Sheet s = wb.createSheet("常用对象管理-报告");
//		paramsMap = (Map<String, Object>) request.getSession().getAttribute(Constant.getExportExcelBasePeriodTPParams);
		paramsMap.remove("start");
		List<BasePeriodTP> dataList = null;
		if("TRUE".equals(isCityBase)) {
			dataList= basePeriodTPDao.getCityBasePeriodTPData(paramsMap);
		} else { 
			dataList = basePeriodTPDao.getBasePeriodTPData(paramsMap);
		}
		String[] vTitles = null;// 标题数组
		if("TRUE".equals(isCityBase)) {
			vTitles = new String[] {"车型编码", "日期", "厂商名称", "车型", "车型名称","城市ID", "城市名称", "厂商指导价（元）", "城市成交价（元）"};
		} else{
			vTitles = new String[] {"车型编码", "日期", "厂商名称", "车型", "车型名称", "厂商指导价（元）", "30城市加权均价（元）"};
		}
		int rowIndex = 0;// 行号锁引
		Row row = ExportExcelUtil.createRow(s, rowIndex, 500);
		Cell cell = null;
		CellStyle titleStyle = ExportExcelUtil.getExcelTitleBackgroundStyle(wb);// 表格标题样式
		CellStyle textStyle = ExportExcelUtil.getExcelFillTextStyle(wb);// 内容文本样式
		// 写型号属性表格标题
		for(int i = 0; i < vTitles.length; i++) {
			cell = row.createCell(i);
			ExportExcelUtil.setCellValueAndStyle(cell, vTitles[i], titleStyle);
			if(i == 4) {
                s.setColumnWidth(i, 12000);				
			} else {
				s.setColumnWidth(i, 5500);
			}
		}
		
		// 写数据
		for (int j = 0; j < dataList.size(); j++) {
			row = ExportExcelUtil.createRow(s, ++rowIndex, 600);
			int cellIndex = 0;
			cell = row.createCell(cellIndex++);
			BasePeriodTP cg = dataList.get(j);
			ExportExcelUtil.setCellValueAndStyle(cell, "'" + cg.getVersionCode(), textStyle);
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell, String.valueOf(cg.getYear()), textStyle);
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell, String.valueOf(cg.getManf()), textStyle);
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell, String.valueOf(cg.getModel()), textStyle);
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell, cg.getModelName(), textStyle);
			
			if("TRUE".equals(isCityBase)) {
				cell = row.createCell(cellIndex++);
				ExportExcelUtil.setCellValueAndStyle(cell, cg.getCityId(), textStyle);
				
				cell = row.createCell(cellIndex++);
				ExportExcelUtil.setCellValueAndStyle(cell, cg.getCityName(), textStyle);
			} 
			
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell, String.valueOf(cg.getMSRP()), textStyle);
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell, String.valueOf(cg.getTP()), textStyle);
		}
		return wb;
	}
	
	/**
	 * 下载常用对象组模版文件
	 * 
	 * @param request
	 * @param response
	 */
	@Override
	public void downloadBasePeriodTPTemplate(HttpServletRequest request, HttpServletResponse response) {
		//获取项目根目录  
        String path = request.getSession().getServletContext().getRealPath("/excelTemplate/");
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
        response.setContentType("application/vnd.ms-excel");  
        ServletOutputStream out = null;
        String fileType = request.getParameter("fileType");
        try {  
        	//2.设置文件头：最后一个参数是设置下载文件名  
        	response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode("基期TP-导入模版." + fileType, "UTF-8"));  
        	File file = new File(path + File.separator + "基期TP-导入模版." + fileType);  
            FileInputStream inputStream = new FileInputStream(file);  
            //3.通过response获取ServletOutputStream对象(out)  
            out = response.getOutputStream();  
            int b = 0;  
            byte[] buffer = new byte[512];  
            while((b = inputStream.read(buffer)) != -1) {  
                //4.写到输出流(out)中  
                out.write(buffer, 0, b);  
            }  
            inputStream.close();  
            out.flush();
            out.close();  
        } catch(IOException e) {  
            e.printStackTrace();  
        }  
	}
	
	/**
	 * 导入文件数据
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String importBasePeriodTPFile(HttpServletRequest request) {
		String json = "{}";
		try {  
		    DiskFileItemFactory factory = new DiskFileItemFactory();  
			// 设置缓冲区大小，这里是4kb
            factory.setSizeThreshold(4096);  
            ServletFileUpload upload = new ServletFileUpload(factory);  
            // 解决文件名称乱码
            upload.setHeaderEncoding("utf-8");
            // 设置最大文件尺寸，这里是10MB
            upload.setSizeMax(10485760);
            List<FileItem> items = upload.parseRequest(request);
	        Iterator<FileItem> iterator = items.iterator();
	        basePeriodTPDao.delBasePeriodTPError();
	        if(iterator.hasNext()) {
	            FileItem fi = (FileItem) iterator.next();  
	            String fileName = fi.getName();
	            String fileType=fileName.substring(fileName.lastIndexOf("."));
	            
	            List<Map<String, Object>> resultList =null;
	            int rowNum = 0;
	        	if("xls".equals(fileType)) {
	        		HSSFWorkbook wb = new HSSFWorkbook(fi.getInputStream());
	        		HSSFSheet sheet = wb.getSheetAt(0);
	                // 得到总行数
	                rowNum = sheet.getLastRowNum();
	                resultList = readExcelContent(sheet, null, rowNum ,fileType);
	        	} else {
	         		XSSFWorkbook wb = new XSSFWorkbook(fi.getInputStream());
	        		XSSFSheet sheet = wb.getSheetAt(0);
	        		 // 得到总行数
	                rowNum = sheet.getLastRowNum();
	                resultList = readExcelContent(null, sheet, rowNum, fileType);
	        	}
	            
	            int total = resultList.size();
	            int groupCount = 0;
	            for(Map<String, Object> dataList : resultList) {
	            	groupCount += basePeriodTPDao.addBasePeriodTPByImport(dataList);
	            }
//	            if(groupCount > 0 ) {
	            json = "{\"result\":\"导入文件总共：" + rowNum + "条,导入成功：" + groupCount + "条，导入失败：" + (total-groupCount) + "条\"}";
//	            } else {
//	            	json = "{\"result\":\"导入失败\"}";
//	            }
	        }  
        } catch(Exception e) {  
        	e.printStackTrace();
        	json="{\"result\":\"读取文件出错,请下载正确的文件模板\"}";
        }  
		return json;
	}
	
	/**
	 * 读取Excel文件内容
	 * 
	 * @param is
	 * @param fileType
	 * @return
	 */
	public List<Map<String, Object>> readExcelContent(HSSFSheet sheet, XSSFSheet xsheet, int rowNum, String fileType) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
//        String[] dataName = {"groupName", "groupSort", "manfName", "subModelName", "versionCode", "versionName", "MSRP", "versionSort"};
        String[] dataName = {"versionCode", "year", "manf", "model", "modelName", "MSRP", "TP"};
        try {
        	if("xls".equals(fileType)) {
//        		HSSFWorkbook wb = new HSSFWorkbook(is);
//        		HSSFSheet sheet = wb.getSheetAt(0);
                // 得到总行数
//                int rowNum = sheet.getLastRowNum();
                // 正文内容应该从第二行开始,第一行为表头的标题
                for(int i = 1; i <= rowNum; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();                	
                    HSSFRow row = sheet.getRow(i);
                    int colNum = row.getPhysicalNumberOfCells();
                    int j = 0;
//                    boolean flag = true;
                    while(j < colNum && j < dataName.length) {
                    	HSSFCell cell = row.getCell(j);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String value = cell.getStringCellValue();
                        if("year".equals(dataName[j])) {
                        	value = value.substring(0, 4);
                        }
                        map.put(dataName[j], value);
                        j++;
                    }
                    dataList.add(map);
                }

        	} else {
//        		XSSFWorkbook wb = new XSSFWorkbook(is);
//        		XSSFSheet sheet = wb.getSheetAt(0);
        		 // 得到总行数
 //               int rowNum = xsheet.getLastRowNum();
                // 正文内容应该从第二行开始,第一行为表头的标题
                for(int i = 1; i <= rowNum; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();        	
                    XSSFRow row = xsheet.getRow(i);
                    int colNum = row.getPhysicalNumberOfCells();
                    int j = 0;
//                    boolean flag = true;
                    while(j < colNum && j < dataName.length) {
                    	XSSFCell cell = row.getCell(j);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String value = cell.getStringCellValue();
                        if("year".equals(dataName[j])) {
                        	value=value.substring(0, 4);
                        }
                    	map.put(dataName[j], value);
                    	j++;
                    }
                	dataList.add(map);
                }
        	}
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return dataList;
    }
	
	/**
	 * 下载城市基期TP模板
	 */
	@Override
	public void downloadCityBasePeriodTPTemplate(HttpServletRequest request, HttpServletResponse response) {
		//获取项目根目录  
        String path = request.getSession().getServletContext().getRealPath("/excelTemplate/");
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
        response.setContentType("application/vnd.ms-excel");  
        ServletOutputStream out = null;
        String fileType = request.getParameter("fileType");
        try {  
        	//2.设置文件头：最后一个参数是设置下载文件名  
        	response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode("城市基期TP-导入模版." + fileType, "UTF-8"));  
        	File file = new File(path + File.separator + "城市基期TP-导入模版." + fileType);  
            FileInputStream inputStream = new FileInputStream(file);  
            //3.通过response获取ServletOutputStream对象(out)  
            out = response.getOutputStream();  
            int b = 0;  
            byte[] buffer = new byte[512];  
            while((b = inputStream.read(buffer)) != -1) {  
                //4.写到输出流(out)中  
                out.write(buffer, 0, b);  
            }  
            inputStream.close();  
            out.flush();
            out.close();  
        } catch(IOException e) {  
            e.printStackTrace();  
        } 
	}

	/**
	 * 导入城市基期TP数据
	 */
	@Override
	public String importCityBasePeriodTPFile(HttpServletRequest request) {
		String json = "{}";
		try {  
			DiskFileItemFactory factory = new DiskFileItemFactory();  
			// 设置缓冲区大小，这里是4kb
            factory.setSizeThreshold(4096);   
            ServletFileUpload upload = new ServletFileUpload(factory);  
            // 解决文件名称乱码
            upload.setHeaderEncoding("utf-8");
            // 设置最大文件尺寸，这里是10MB 
            upload.setSizeMax(10485760); 
            List<FileItem> items = upload.parseRequest(request);
	        Iterator<FileItem> iterator = items.iterator();
	        basePeriodTPDao.delCityBasePeriodTPError();
	        if(iterator.hasNext()) {  
	            FileItem fi = (FileItem) iterator.next();  
	            String fileName = fi.getName();
	            String fileType = fileName.substring(fileName.lastIndexOf("."));
	            
	            List<Map<String, Object>> resultList = null;
	            int rowNum = 0;
	        	if("xls".equals(fileType)) {
	        		HSSFWorkbook wb = new HSSFWorkbook(fi.getInputStream());
	        		HSSFSheet sheet = wb.getSheetAt(0);
	                // 得到总行数
	                rowNum = sheet.getLastRowNum();
	                resultList = readCityBasePeriodExcelContent(sheet, null, rowNum, fileType);
	        	} else {
	         		XSSFWorkbook wb = new XSSFWorkbook(fi.getInputStream());
	        		XSSFSheet sheet = wb.getSheetAt(0);
	        		 // 得到总行数
	                rowNum = sheet.getLastRowNum();
	                resultList = readCityBasePeriodExcelContent(null, sheet, rowNum, fileType);
	        	}
	            
	            int total = resultList.size();
	            int groupCount = 0;
	            for(Map<String, Object> dataList : resultList) {
	            	groupCount += basePeriodTPDao.addCityBasePeriodTPByImport(dataList);
	            }
//		            if(groupCount > 0 ) {
	            json = "{\"result\":\"导入文件总共：" + rowNum + "条,导入成功：" + groupCount + "条，导入失败：" + (total-groupCount) + "条\"}";
//		            } else {
//		            	json = "{\"result\":\"导入失败\"}";
//		            }
	        }  
        } catch (Exception e) {  
        	e.printStackTrace();
        	json="{\"result\":\"读取文件出错,请下载正确的文件模板\"}";
        }  
		return json;
	}

	private List<Map<String, Object>> readCityBasePeriodExcelContent(HSSFSheet sheet, XSSFSheet xsheet, int rowNum, String fileType) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
//        String[] dataName = {"groupName", "groupSort", "manfName", "subModelName", "versionCode", "versionName", "MSRP", "versionSort"};
        String[] dataName = {"versionCode", "year", "manf", "model", "modelName","cityId","cityName", "MSRP", "TP"};
        try {
        	if("xls".equals(fileType)) {
//        		HSSFWorkbook wb = new HSSFWorkbook(is);
//        		HSSFSheet sheet = wb.getSheetAt(0);
                // 得到总行数
//                int rowNum = sheet.getLastRowNum();
                // 正文内容应该从第二行开始,第一行为表头的标题
                for (int i = 1; i <= rowNum; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();                	
                    HSSFRow row = sheet.getRow(i);
                    int colNum = row.getPhysicalNumberOfCells();
                    int j = 0;
//                    boolean flag = true;
                    while(j < colNum && j < dataName.length) {
                    	HSSFCell cell = row.getCell(j);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String value = cell.getStringCellValue();
                        if("year".equals(dataName[j])) {
                        	value=value.substring(0, 4);
                        }
                        map.put(dataName[j], value);
                        j++;
                    }
                    dataList.add(map);
                }

        	} else {
//        		XSSFWorkbook wb = new XSSFWorkbook(is);
//        		XSSFSheet sheet = wb.getSheetAt(0);
        		 // 得到总行数
 //               int rowNum = xsheet.getLastRowNum();
                // 正文内容应该从第二行开始,第一行为表头的标题
                for(int i = 1; i <= rowNum; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();        	
                    XSSFRow row = xsheet.getRow(i);
                    int colNum = row.getPhysicalNumberOfCells();
                    int j = 0;
//                    boolean flag = true;
                    while(j < colNum && j < dataName.length) {
                    	XSSFCell cell = row.getCell(j);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String value = cell.getStringCellValue();
                        if("year".equals(dataName[j])) {
                        	if(null != value && !"".equals(value)) {
                        		value=value.substring(0, 4);
                        	}
                        	
                        }
                    	map.put(dataName[j], value);
                    	j++;
                    }
                	dataList.add(map);
                }
        	}
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return dataList;
	}
}
