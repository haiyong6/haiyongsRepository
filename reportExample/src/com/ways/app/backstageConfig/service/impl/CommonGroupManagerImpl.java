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

import com.ways.app.backstageConfig.dao.ICommonGroupDao;
import com.ways.app.backstageConfig.model.CommonGroup;
import com.ways.app.backstageConfig.service.ICommonGroupManager;
import com.ways.framework.utils.AppFrameworkUtil;
import com.ways.framework.utils.Constant;
import com.ways.framework.utils.ExportExcelUtil;

/**
 * 常用对象管理Service实现类 
 * 
 * @author songguobiao
 * 20160401
 */
@Repository("commonGroupManagerImpl")
public class CommonGroupManagerImpl implements ICommonGroupManager {
	@Autowired
	private ICommonGroupDao commonGroupDao;
	
	/**
	 * 获取常用对象数据
	 * 
	 * @param request
	 * @param paramsMap
	 * @return
	 */
	@Override
	public String getCommonGroupData(HttpServletRequest request, Map<String, Object> paramsMap) {
		String json = "{}";
		int count = 0;
		// 定义结果结序列化MAP
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<CommonGroup> list = commonGroupDao.getCommonGroupData(paramsMap);
			if (null != list && 0 != list.size()) {
				count = list.size();
			}
			resultMap.put("iTotalRecords", count);
			resultMap.put("iTotalDisplayRecords", count);
			resultMap.put("aaData", list);
			json = AppFrameworkUtil.serializableJSONData(resultMap);
			request.getSession().setAttribute(Constant.getExportExcelCommonGroupParams, paramsMap);
		} catch (Exception e) {
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
	public String getCommonGroup(Map<String, Object> paramsMap) {
		String json = "{}";
		// 定义结果结序列化MAP
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<CommonGroup> groupList = commonGroupDao.getCommonGroupData(paramsMap);
			resultMap.put("groupList", groupList);
			json = AppFrameworkUtil.serializableJSONData(resultMap);
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
		    	request.setAttribute("brandLetterList", commonGroupDao.getSubModelByBrand(paramsMap));
	    	} else {
	    		//返回厂商页
		    	request.setAttribute("manfLetterList", commonGroupDao.getSubModelByManf(paramsMap));
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取车型下的型号数据
	 * 
	 * @param request
	 * @param paramsMap
	 */
	@Override
	public void getVersionModal(HttpServletRequest request, Map<String, Object> paramsMap) {
		try {
			//保存子车型下型号数据
			request.setAttribute("versionList", commonGroupDao.getVersionModal(paramsMap));
		} catch (Exception e) {
		    e.printStackTrace();	
		}
	}
	
	/**
	 * 删除常用对象组
	 * 
	 * @param paramsMap
	 * @return
	 */
	public String deleteCommonGroup(Map<String, Object> paramsMap) {
		String json = "{}";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int count = commonGroupDao.deleteCommonGroup(paramsMap);
			if(count > 1) {
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
	 * 添加常用对象组
	 * 
	 * @param paramsMap
	 * @return
	 */
	@Override
	public String addCommonGroup(Map<String, Object> paramsMap) {
		String json = "";
		String[] jpVersionIds = (String[])paramsMap.get("jpVersionIds");
		String[] bpVersionIds = (String[])paramsMap.get("bpVersionIds");
	    int versionCount = 0;
	    int count = 0;
	    // 添加常用对象组(分组型号--本品信息)
	    for(int i = 0; i < bpVersionIds.length; i++) {
	    	CommonGroup cg = new CommonGroup();
	    	cg.setVersionId(Integer.parseInt(bpVersionIds[i]));
	    	cg.setGroupSort(Integer.parseInt((String)paramsMap.get("groupSort")));
	    	cg.setGroupName((String)paramsMap.get("groupName"));
	    	cg.setIsJP(0);
	    	cg.setVersionSort(1);
	    	count += commonGroupDao.addCommonGroupByAdd(cg);
	    	versionCount += commonGroupDao.addCommonGroupVersionByAdd(cg);
	    	int sort = 2;
	    	for(String versionId : jpVersionIds) {
	    		CommonGroup commonGroup = new CommonGroup();
	 	        commonGroup.setVersionId(Integer.parseInt(versionId));
	 	        commonGroup.setGroupSort(Integer.parseInt((String)paramsMap.get("groupSort")));
	 	        commonGroup.setIsJP(1);
	 	        commonGroup.setVersionSort(sort);
	 			versionCount += commonGroupDao.addCommonGroupVersionByAdd(commonGroup);
	 			sort++;
	    	}
	    }
		if(count > 0 && versionCount > 1) {
			json = "{\"result\":\"添加成功\"}";
		} else {
			json = "{\"result\":\"添加失败\"}";
		}
		return json;
	}
	
	/**
	 * 修改常用对象组
	 * 
	 * @param paramsMap
	 */
	public String updateCommonGroup(Map<String, Object> paramsMap) {
		String json = "";
		int count = 0;
		String[] jpVersionIds = (String[])paramsMap.get("jpVersionIds");
		String[] bpVersionIds = (String[])paramsMap.get("bpVersionIds");
		String[] bpSubModelIds = (String[])paramsMap.get("bpSubModelIds");
		Integer groupId = Integer.parseInt((String)paramsMap.get("groupId"));
		String groupName = (String)paramsMap.get("groupName");// 更新的分组名称
		String oldGroupName = (String)paramsMap.get("oldGroupName");// 原本的分组名称
		Integer groupSort = Integer.parseInt((String)paramsMap.get("groupSort"));// 更新的分组排序
		
		// 删除原本的常用对象组数据
		Map<String, Object> bpMap = new HashMap<String, Object>();
		bpMap.put("groupId", groupId);
		bpMap.put("groupName", oldGroupName);
		commonGroupDao.deleteCommonGroupByUpdate(bpMap);
		// 增加新的常用对象
		CommonGroup cg = new CommonGroup();
		cg.setGroupId(groupId);
		cg.setGroupName(groupName);
		cg.setGroupSort(groupSort);
		cg.setSubModelId(Integer.parseInt(bpSubModelIds[0]));
		cg.setVersionId(Integer.parseInt(bpVersionIds[0]));
		count += commonGroupDao.addCommonGroupByUpdate(cg);
		bpMap.put("versionId", Integer.parseInt(bpVersionIds[0]));
		bpMap.put("isJP", 0);
		bpMap.put("jpSort", 1);
		count += commonGroupDao.addCommonGroupVersionByUpdate(bpMap);
		
		int jpSort = 2;
		for(String jpVersionId : jpVersionIds) {
			Map<String, Object> jpMap = new HashMap<String, Object>();
			jpMap.put("versionId", Integer.parseInt(jpVersionId));
			jpMap.put("groupId", groupId);
			jpMap.put("isJP", 1);
			jpMap.put("jpSort", jpSort++);
			count += commonGroupDao.addCommonGroupVersionByUpdate(jpMap);
		}
		if(count > 0) {
			json = "{\"result\":\"添加成功\"}";
		} else {
			json = "{\"result\":\"添加失败\"}";
		}
		return json;
	}
	
	/**
	 * 根据分组名称获取分组信息
	 * 
	 * @param paramsMap
	 * @return
	 */
	public String getCommonGroupByGroupName(Map<String, Object> paramsMap) {
		String json = "";
		// 定义结果结序列化MAP
		Map<String, Object> resultMap = new HashMap<String, Object>();
		CommonGroup cg = commonGroupDao.getBPInfoByGroupName(paramsMap);
		List<CommonGroup> groupList = commonGroupDao.getJPInfoByGroupName(paramsMap);
		resultMap.put("bpInfo", cg);
		resultMap.put("groupList", groupList);
		json = AppFrameworkUtil.serializableJSONData(resultMap);
		return json;
	}
	/**
	 * 更新常用对象组的型号排序
	 * 
	 * @param paramsMap
	 * @return
	 */
	public String updateCommonGroupVersionSort(Map<String, Object> paramsMap) {
		String json = "";
		int count = 0;
		String groupSort = (String)paramsMap.get("groupSort");
		String[] groupInfos = groupSort.split(",");
		paramsMap.put("groupId", Integer.parseInt(groupInfos[0]));
		paramsMap.put("groupNewSort", Integer.parseInt(groupInfos[1]));
		count += commonGroupDao.updateCommonGroupGroupSort(paramsMap);
		
		String versionSort = (String)paramsMap.get("versionSort");
		String[] versionInfos = versionSort.split("\\|\\|");
		for(String versionInfo : versionInfos) {
			paramsMap.put("groupId", Integer.parseInt(versionInfo.split(",")[0]));
			paramsMap.put("versionId", Integer.parseInt(versionInfo.split(",")[1]));
			paramsMap.put("versionNewSort", Integer.parseInt(versionInfo.split(",")[2]));
			count += commonGroupDao.updateCommonGroupVersionSort(paramsMap);
		}
		if(count > 0) {
			json = "{\"result\":\"添加成功\"}";
		} else {
			json = "{\"result\":\"添加失败\"}";
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
	@SuppressWarnings("unchecked")
	@Override
	public Workbook exportExcel(HttpServletRequest request, Map<String, Object> paramsMap) {
		Workbook wb = new HSSFWorkbook();
		Sheet s = wb.createSheet("常用对象管理-报告");
		paramsMap = (Map<String, Object>) request.getSession().getAttribute(Constant.getExportExcelCommonGroupParams);
		paramsMap.remove("start");
		List<CommonGroup> dataList = commonGroupDao.getCommonGroupData(paramsMap);
		String[] vTitles = null;// 标题数组
		vTitles = new String[] {"分组名", "分组排序", "厂商", "车型", "型号名称", "指导价", "型号序号"};
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
			CommonGroup cg = dataList.get(j);
			ExportExcelUtil.setCellValueAndStyle(cell, cg.getGroupName(), textStyle);
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell, String.valueOf(cg.getGroupSort()), textStyle);
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell, String.valueOf(cg.getManfName()), textStyle);
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell, String.valueOf(cg.getSubModelName()), textStyle);
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell, cg.getVersionName(), textStyle);
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell, String.valueOf(cg.getMSRP()), textStyle);
			cell = row.createCell(cellIndex++);
			ExportExcelUtil.setCellValueAndStyle(cell, String.valueOf(cg.getVersionSort()), textStyle);
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
	public void downloadCommonGroupTemplate(HttpServletRequest request, HttpServletResponse response) {
		//获取项目根目录  
        String path = request.getSession().getServletContext().getRealPath("/excelTemplate/");
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
        response.setContentType("application/vnd.ms-excel");  
        ServletOutputStream out = null;
        String fileType = request.getParameter("fileType");
        try {  
        	//2.设置文件头：最后一个参数是设置下载文件名  
        	response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode("常用对象组-导入模版." + fileType, "UTF-8"));  
        	File file = new File(path + File.separator + "常用对象组-导入模版." + fileType);  
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
	public String importCommonGroupFile(HttpServletRequest request) {
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
	            List<List<Map<String, Object>>> resultList = readExcelContent(fi.getInputStream(), fileName.substring(fileName.lastIndexOf(".")));
	            int groupCount = 0;
	            int groupVersionCount = 0;
	            for(List<Map<String, Object>> dataList : resultList) {
	            	// 添加分组信息(fdm_custom_group)
	            	groupCount += commonGroupDao.addCommonGroupByImport(dataList.get(0));
	            	// 添加分组型号信息(fdm_custom_group_version)
	            	for(int i = 0; i < dataList.size(); i++) {
	            		CommonGroup cg = new CommonGroup();
	            		Map<String, Object> map = dataList.get(i);
	            		cg.setVersionCode((String)map.get("versionCode"));
	            		cg.setGroupSort(Integer.parseInt((String)map.get("groupSort")));
	            		cg.setVersionSort(Integer.parseInt((String)map.get("versionSort")));
	            		cg.setIsJP(Integer.parseInt((String)map.get("isJP")));
	            		groupVersionCount += commonGroupDao.addCommonGroupVersionByImport(cg);
	            	}
	            }
	            if(groupCount > 0 && groupVersionCount > 0) {
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
	public List<List<Map<String, Object>>> readExcelContent(InputStream is, String fileType) {
		List<List<Map<String, Object>>> resultList = new ArrayList<List<Map<String, Object>>>();
        String[] dataName = {"groupName", "groupSort", "manfName", "subModelName", "versionCode", "versionName", "MSRP", "versionSort"};
        try {
        	if("xls".equals(fileType)) {
        		HSSFWorkbook wb = new HSSFWorkbook(is);
        		HSSFSheet sheet = wb.getSheetAt(0);
                // 得到总行数
                int rowNum = sheet.getLastRowNum();
                List<Map<String, Object>> dataList = null;
                // 分组名称，用来判断是否是新的分组
                String presentGroupName = "";
                // 正文内容应该从第二行开始,第一行为表头的标题
                for (int i = 1; i <= rowNum; i++) {
                	String groupName = sheet.getRow(i).getCell(0).getStringCellValue();
                	if(!"".equals(presentGroupName) && !groupName.equals(presentGroupName)) {
                		// 遇到不一样的分组名称则把上一个分组集合加到结果集中
                		resultList.add(dataList);
                		dataList = null;
                    }
                	dataList = getGroupInfo(dataList);
                    if(dataList.size() == 1) {
                    	presentGroupName = groupName;
                    }
                    Map<String, Object> map = new HashMap<String, Object>();                	
                    HSSFRow row = sheet.getRow(i);
                    int colNum = row.getPhysicalNumberOfCells();
                    int j = 0;
                    boolean flag = true;
                    while (j < colNum) {
                    	HSSFCell cell = row.getCell(j);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String value = cell.getStringCellValue();
                        // 型号排序是1的默认为本品,把本品信息放在集合第一个map中
                        if(j == colNum - 1 && "1".equals(value)) {
                        	map.put(dataName[j], value);
                        	map.put("isJP", "0");
                        	dataList.get(0).putAll(map);
                        	flag = false;
                        	j++;
                        } else { 
                        	map.put("isJP", "1");
                        	map.put(dataName[j], value);
                        	j++;
                        }
                    }
                    if(flag) {
                    	dataList.add(map);
                    }
                }
                // 把最后的分组加上
                resultList.add(dataList);
        	} else {
        		XSSFWorkbook wb = new XSSFWorkbook(is);
        		XSSFSheet sheet = wb.getSheetAt(0);
        		 // 得到总行数
                int rowNum = sheet.getLastRowNum();
                List<Map<String, Object>> dataList = null;
                // 分组名称，用来判断是否是新的分组
                String presentGroupName = "";
                // 正文内容应该从第二行开始,第一行为表头的标题
                for (int i = 1; i <= rowNum; i++) {
                	String groupName = sheet.getRow(i).getCell(0).getStringCellValue();
                	if(!"".equals(presentGroupName) && !groupName.equals(presentGroupName)) {
                		// 遇到不一样的分组名称则把上一个分组集合加到结果集中
                		resultList.add(dataList);
                		dataList = null;
                    }
                	dataList = getGroupInfo(dataList);
                    if(dataList.size() == 1) {
                    	presentGroupName = groupName;
                    }
                    Map<String, Object> map = new HashMap<String, Object>();                	
                    XSSFRow row = sheet.getRow(i);
                    int colNum = row.getPhysicalNumberOfCells();
                    int j = 0;
                    boolean flag = true;
                    while (j < colNum) {
                    	XSSFCell cell = row.getCell(j);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String value = cell.getStringCellValue();
                        // 型号排序是1的默认为本品,把本品信息放在集合第一个map中
                        if(j == colNum - 1 && "1".equals(value)) {
                        	map.put(dataName[j], value);
                        	map.put("isJP", "0");
                        	dataList.get(0).putAll(map);
                        	flag = false;
                        	j++;
                        } else { 
                        	map.put("isJP", "1");
                        	map.put(dataName[j], value);
                        	j++;
                        }
                    }
                    if(flag) {
                    	dataList.add(map);
                    }
                }
                // 把最后的分组加上
                resultList.add(dataList);
        	}
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return resultList;
    }
	
	/**
	 * 获取分组集合
	 * 
	 * @param dataList
	 * @return
	 */
	private List<Map<String, Object>> getGroupInfo(List<Map<String, Object>> dataList) {
		if(null == dataList) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> bpInfoMap = new HashMap<String, Object>();
			// 先把本品map放在集合的第一位
			list.add(bpInfoMap);
			return list;
		} else {
			return dataList;
		}
	}
}
