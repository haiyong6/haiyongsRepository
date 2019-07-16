package com.ways.app.backstageConfig.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import com.ways.app.backstageConfig.dao.IOriginalAndCompetitorDao;
import com.ways.app.backstageConfig.model.OriginalAndCompetitor;
import com.ways.app.backstageConfig.service.IOriginalAndCompetitorManager;
import com.ways.framework.utils.AppFrameworkUtil;
import com.ways.framework.utils.Constant;
import com.ways.framework.utils.ExportExcelUtil;

/**
 * 本竞品管理Service实现类 
 * 
 * @author songguobiao
 * 20160401
 */
@Repository("originalAndCompetitorManagerImpl")
public class OriginalAndCompetitorManagerImpl implements IOriginalAndCompetitorManager {
	
	@Autowired
	private IOriginalAndCompetitorDao originalAndCompetitorDao;
	
	/**
	 * 获取本竞品数据
	 * 
	 * @param request
	 * @param paramsMap
	 * @return
	 */
	@Override
	public String getOriginalAndCompetitorData(HttpServletRequest request, Map<String, Object> paramsMap) {
		String json = "{}";
		int count = 0;
		// 定义结果结序列化MAP
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<OriginalAndCompetitor> list = originalAndCompetitorDao.getOriginalAndCompetitorData(paramsMap);
			if (null != list && 0 != list.size()) {
				count = list.size();
			}
			resultMap.put("iTotalRecords", count);
			resultMap.put("iTotalDisplayRecords", count);
			resultMap.put("aaData", list);
			json = AppFrameworkUtil.serializableJSONData(resultMap);
			request.getSession().setAttribute(Constant.getExportExcelOriginalAndCompetitorParams, paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 获取车型数据
	 * 
	 * @param request
	 * @param paramsMap
	 */
	@Override
	public void getSubModelModal(HttpServletRequest request, Map<String, Object> paramsMap) {	
		Object subModelShowType = paramsMap.get("subModelShowType");
		try {
	    	if(null == subModelShowType) {
	    		//返回品牌页
		    	request.setAttribute("brandLetterList", originalAndCompetitorDao.getSubModelByBrand(paramsMap));
	    	} else {
	    		//返回厂商页
		    	request.setAttribute("manfLetterList", originalAndCompetitorDao.getSubModelByManf(paramsMap));
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加本竞品组
	 * 
	 * @param paramsMap
	 * @return
	 */
	@Override
	public String addOriginalAndCompetitor(Map<String, Object> paramsMap) {
		String json = "";
		String[] bpSubModelIds = (String[])paramsMap.get("bpSubModelIds");
		Integer bpSort = Integer.parseInt((String)paramsMap.get("bpSubModelSort"));
		String[] jpSubModelIds = (String[])paramsMap.get("jpSubModelIds");
		Integer jpSort = 1;
	    int count = 0;
	    // 添加本竞品组
	    for(int i = 0; i < bpSubModelIds.length; i++) {
	    	OriginalAndCompetitor oc = new OriginalAndCompetitor();
	    	oc.setBpSubModelSort(bpSort);
	    	oc.setBpSubModelId(Integer.parseInt(bpSubModelIds[i]));
	    	for(String jpSubModelId : jpSubModelIds) {
	    		oc.setjpSubModelId(Integer.parseInt(jpSubModelId));
	    		oc.setJpSubModelSort(jpSort);
	    		jpSort++;
	    		count += originalAndCompetitorDao.addOriginalAndCompetitorByAdd(oc);
	    	}
	    	jpSort = 1;
	    	bpSort++;
	    }
		if(count > 0) {
			json = "{\"result\":\"添加成功\"}";
		} else {
			json = "{\"result\":\"添加失败\"}";
		}
		return json;
	}
	
	/**
	 * 修改本竞品组
	 * 
	 * @param paramsMap
	 */
	public String updateOriginalAndCompetitor(Map<String, Object> paramsMap) {
		String json = "";
		String[] bpSubModelIds = (String[])paramsMap.get("bpSubModelIds");
		Integer bpSubModelSort = Integer.parseInt((String)paramsMap.get("bpSubModelSort"));
		String[] jpSubModelIds = (String[])paramsMap.get("jpSubModelIds");
		Integer oldSort = Integer.parseInt((String)paramsMap.get("oldSort"));
		Integer jpSort = 1;
		int count = 0;
		// 删除原来的本竞品组数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bpSubModelId", Integer.parseInt(bpSubModelIds[0]));
		map.put("oldSort", oldSort);
		count += originalAndCompetitorDao.deleteOriginalAndCompetitorByUpdate(map);
		
		// 新增最新的本竞品组数据
		for(String jpSubModelId : jpSubModelIds) {
			Map<String, Object> jpMap = new HashMap<String, Object>();
			jpMap.put("bpSubModelId", bpSubModelIds[0]);
			jpMap.put("jpSubModelId", jpSubModelId);
			jpMap.put("bpSubModelSort", bpSubModelSort);
			jpMap.put("jpSubModelSort", jpSort++);
			count += originalAndCompetitorDao.addOriginalAndCompetitorByUpdate(jpMap);
		}
		if(count > 0) {
			json = "{\"result\":\"修改成功\"}";
		} else {
			json = "{\"result\":\"修改失败\"}";
		}
		return json;
	}
	
	/**
	 * 获取本竞品组数据
	 * 
	 * @param paramsMap
	 * @return
	 */
	@Override
	public String getOriginalAndCompetitor(Map<String, Object> paramsMap) {
		String json = "";
		// 定义结果结序列化MAP
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<OriginalAndCompetitor> bjpList = originalAndCompetitorDao.getOriginalAndCompetitorData(paramsMap);
			resultMap.put("bjpList", bjpList);
			json = AppFrameworkUtil.serializableJSONData(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return json;
	}
	
	/**
	 * 更新本竞品的车型排序
	 * 
	 * @param paramsMap
	 * @return
	 */
	@Override
	public String updateOriginalAndCompetitorSort(Map<String, Object> paramsMap) {
		String json = "";
		int count = 0;
		String bpSort = (String)paramsMap.get("bpSort");
		String[] bpInfos = bpSort.split(",");
		paramsMap.put("bpSubModelId", Integer.parseInt(bpInfos[0]));
		paramsMap.put("bpSubModelSort", Integer.parseInt(bpInfos[1]));
		paramsMap.put("bpNewSort", Integer.parseInt(bpInfos[2]));
		count += originalAndCompetitorDao.updateOriginalAndCompetitorBpSort(paramsMap);
		
		String jpSort = (String)paramsMap.get("jpSort");
		String[] jpInfos = jpSort.split("\\|\\|");
		for(String jpInfo : jpInfos) {
			paramsMap.put("bpSubModelId", Integer.parseInt(jpInfo.split(",")[0]));
			paramsMap.put("jpSubModelId", Integer.parseInt(jpInfo.split(",")[1]));
			paramsMap.put("jpNewSort", Integer.parseInt(jpInfo.split(",")[2]));
			count += originalAndCompetitorDao.updateOriginalAndCompetitorJpSort(paramsMap);
		}
		if(count > 0) {
			json = "{\"result\":\"更新成功\"}";
		} else {
			json = "{\"result\":\"更新失败\"}";
		}
		return json;
	}
	
	/**
	 * 根据本品车型ID获取本竞品组数据
	 * @param paramsMap
	 */
	@Override
	public String getBJPGroupByBpSubModelId(Map<String, Object> paramsMap) {
		String json = "";
		// 定义结果结序列化MAP
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<OriginalAndCompetitor> ocList = originalAndCompetitorDao.getOriginalAndCompetitorData(paramsMap);
		resultMap.put("bjpList", ocList);
		json = AppFrameworkUtil.serializableJSONData(resultMap);
		return json;
	}
	/**
	 * 删除本竞品组
	 * 
	 * @param paramsMap
	 * @return
	 */
	public String deleteOriginalAndCompetitor(Map<String, Object> paramsMap) {
		String json = "{}";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int count = originalAndCompetitorDao.deleteOriginalAndCompetitor(paramsMap);
			if(count > 0) {
				resultMap.put("result", "删除成功");
			} else {
				resultMap.put("errorMsg", "删除失败");
			}
		} catch(Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "删除失败");
		}
		json = AppFrameworkUtil.serializableJSONData(resultMap);
		return json;
	}
	
	/**
	 * 导出
	 * 
	 * @param request
	 * @param paramsMap
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Workbook exportExcel(HttpServletRequest request, Map<String, Object> paramsMap) {
		Workbook wb = new HSSFWorkbook();
		Sheet s = wb.createSheet("本竞品管理-报告");
		paramsMap = (Map<String, Object>) request.getSession().getAttribute(Constant.getExportExcelOriginalAndCompetitorParams);
		paramsMap.remove("start");
		List<OriginalAndCompetitor> dataList = originalAndCompetitorDao.getOriginalAndCompetitorData(paramsMap);
		String[] vTitles = null;// 标题数组
		vTitles = new String[] {"本品厂商", "本平车型", "本品车型排序", "竞品厂商", "竞品车型", "竞品车型排序"};
		int rowIndex = 0;// 行号锁引
		Row row = ExportExcelUtil.createRow(s, rowIndex, 500);
		Cell cell = null;
		CellStyle titleStyle = ExportExcelUtil.getExcelTitleBackgroundStyle(wb);// 表格标题样式
		CellStyle textStyle = ExportExcelUtil.getExcelFillTextStyle(wb);// 内容文本样式
		// 写型号属性表格标题
		for (int i = 0; i < vTitles.length; i++) {
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
			OriginalAndCompetitor oc = dataList.get(j);
			ExportExcelUtil.setCellValueAndStyle(cell, oc.getBpManfName(), textStyle);
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell, oc.getBpSubModelName(), textStyle);
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell, String.valueOf(oc.getBpSubModelSort()), textStyle);
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell, oc.getJpManfName(), textStyle);
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell, oc.getJpSubModelName(), textStyle);
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell, String.valueOf(oc.getJpSubModelSort()), textStyle);
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
	public void downloadOriginalAndCompetitorTemplate(HttpServletRequest request, HttpServletResponse response) {
		//获取项目根目录  
        String path = request.getSession().getServletContext().getRealPath("/excelTemplate/");
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
        response.setContentType("application/vnd.ms-excel");  
        ServletOutputStream out = null;
        String fileType = request.getParameter("fileType");
        try {  
        	//2.设置文件头：最后一个参数是设置下载文件名  
        	response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode("本竞品-导入模版." + fileType, "UTF-8"));  
        	File file = new File(path + File.separator + "本竞品-导入模版." + fileType);  
            FileInputStream inputStream = new FileInputStream(file);  
            //3.通过response获取ServletOutputStream对象(out)  
            out = response.getOutputStream();  
            int b = 0;  
            byte[] buffer = new byte[512];  
            while ((b = inputStream.read(buffer)) != -1){  
                //4.写到输出流(out)中  
                out.write(buffer,0,b);  
            }  
            inputStream.close();  
            out.flush();
            out.close();  
        } catch (IOException e) {  
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
	public String importOriginalAndCompetitorFile(HttpServletRequest request) {
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
	        if (iterator.hasNext()) {  
	            FileItem fi = (FileItem) iterator.next();  
	            String fileName = fi.getName();  
	            List<Map<String, Object>> dataList = readExcelContent(fi.getInputStream(), fileName.substring(fileName.lastIndexOf(".")));
	            int count = 0;
	            for(Map<String, Object> map : dataList) {
	           		OriginalAndCompetitor oc = new OriginalAndCompetitor();
	           		oc.setBpSubModelName((String)map.get("bpSubModelName"));
	           		oc.setBpSubModelSort(Integer.parseInt((String)map.get("bpSubModelSort")));
	           		oc.setJpManfName((String)map.get("jpManfName"));
	           		oc.setJpSubModelName((String)map.get("jpSubModelName"));
	           		oc.setJpSubModelSort(Integer.parseInt((String)map.get("jpSubModelSort")));
	            	count += originalAndCompetitorDao.addOriginalAndCompetitorByImport(oc);
	            }
	            if(count == dataList.size()) {
	            	json = "{\"result\":\"导入成功\"}";
	            } else {
	            	json = "{\"result\":\"导入失败\"}";
	            }
	        }  
        } catch (Exception e) {  
        	e.printStackTrace();
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
	public List<Map<String, Object>> readExcelContent(InputStream is, String fileType) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        String[] dataName = {"bpManfName", "bpSubModelName", "bpSubModelSort", "jpManfName", "jpSubModelName", "jpSubModelSort"};
        try {
        	if("xls".equals(fileType)) {
        		HSSFWorkbook wb = new HSSFWorkbook(is);
        		HSSFSheet sheet = wb.getSheetAt(0);
                // 得到总行数
                int rowNum = sheet.getLastRowNum();
                // 正文内容应该从第二行开始,第一行为表头的标题
                for (int i = 1; i <= rowNum; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();                	
                    HSSFRow row = sheet.getRow(i);
                    int colNum = row.getPhysicalNumberOfCells();
                    int j = 0;
                    while (j < colNum) {
                    	HSSFCell cell = row.getCell(j);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String value = cell.getStringCellValue();
                        map.put(dataName[j], value);
                        j++;
                    }
                    dataList.add(map);
                }
        	} else {
        		XSSFWorkbook wb = new XSSFWorkbook(is);
        		XSSFSheet sheet = wb.getSheetAt(0);
        		 // 得到总行数
                int rowNum = sheet.getLastRowNum();
                // 正文内容应该从第二行开始,第一行为表头的标题
                for (int i = 1; i <= rowNum; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();                	
                    XSSFRow row = sheet.getRow(i);
                    int colNum = row.getPhysicalNumberOfCells();
                    int j = 0;
                    while (j < colNum) {
                    	XSSFCell cell = row.getCell(j);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String value = cell.getStringCellValue();
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
