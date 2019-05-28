package com.ways.app.basePeriodTPConfig.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ways.app.basePeriodTPConfig.service.IBasePeriodTPManager;
import com.ways.framework.base.BaseController;
import com.ways.framework.utils.AppFrameworkUtil;
import com.ways.framework.utils.Constant;
import com.ways.framework.utils.DataTableUntil;

/**
 * 常用对象组管理Controller
 * 
 * @author songguobiao
 * 20160401
 */
@Controller
public class BasePeriodTPController extends BaseController {
	protected final Log log = LogFactory.getLog(BasePeriodTPController.class);
	
	@Autowired
	private IBasePeriodTPManager basePeriodTPManager; 
	
	/**
	 * 进入主页方法
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/basePeriodTPConfig/commonGroupMain")
	public ModelAndView commonGroupMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    Model model = new ExtendedModelMap();
	    
		Map<String, Object> paramsMap = getPageParams(request);
		basePeriodTPManager.initDate(request, paramsMap);
	    
	    return new ModelAndView("/basePeriodTPConfig/commonGroupMain", model.asMap());
	}
	
	/**
	 * 通过条件加载数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/basePeriodTPConfig/getBasePeriodTPInfo")
	public void getCommonGroupInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> paramsMap = getPageParams(request);
        DataTableUntil.getParamByPage(request, paramsMap);
        String json = basePeriodTPManager.getBasePeriodTPData(request, paramsMap);
        AppFrameworkUtil.renderJSON(response, json);
    }
	
	/**
   	 * 导出数据
   	 * 
   	 * @return
   	 * @throws Exception
    */
	@SuppressWarnings("unchecked")
    @RequestMapping("/basePeriodTPConfig/basePeriodTPInfoExport")
    public void basePeriodTPInfoExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Map<String, Object> paramsMap = getParams(request);
    	paramsMap = (Map<String, Object>) request.getSession().getAttribute(Constant.getExportExcelBasePeriodTPParams);
   	    Workbook wb = basePeriodTPManager.exportExcel(request, paramsMap);
   	    String excelName = java.net.URLEncoder.encode("常用对象管理-报告" + AppFrameworkUtil.getSystemDate(), "UTF-8");
   		response.setContentType("application/vnd.ms-excel;charset=utf-8");  
   	    response.setHeader("Content-Disposition", "attachment;filename=" + excelName + ".xls");  
   		ServletOutputStream out = response.getOutputStream(); 
   		wb.write(out);
   		//关闭流
   		out.flush();
   		out.close();
    }
	
	/**
   	 * 导出城市基期TP数据
   	 * 
   	 * @return
   	 * @throws Exception
    */
	@SuppressWarnings("unchecked")
    @RequestMapping("/basePeriodTPConfig/cityBasePeriodTPInfoExport")
    public void cityBasePeriodTPInfoExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Map<String, Object> paramsMap = getParams(request);
    	paramsMap.putAll((Map<String, Object>) request.getSession().getAttribute(Constant.getExportExcelBasePeriodTPParams));
    	paramsMap.put("isCityBase", "TRUE");
   	    Workbook wb = basePeriodTPManager.exportExcel(request, paramsMap);
   	    String excelName = java.net.URLEncoder.encode("城市基期TP常用对象管理-报告" + AppFrameworkUtil.getSystemDate(), "UTF-8");
   		response.setContentType("application/vnd.ms-excel;charset=utf-8");  
   	    response.setHeader("Content-Disposition", "attachment;filename=" + excelName + ".xls");  
   		ServletOutputStream out = response.getOutputStream(); 
   		wb.write(out);
   		//关闭流
   		out.flush();
   		out.close();
    }
	
	/**
   	 * 导出error数据
   	 * 
   	 * @return
   	 * @throws Exception
    */
	@SuppressWarnings("unchecked")
    @RequestMapping("/basePeriodTPConfig/exportErrorData")
    public void exportErrorData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Map<String, Object> paramsMap = getParams(request);
    	paramsMap = (Map<String, Object>) request.getSession().getAttribute(Constant.getExportExcelBasePeriodTPParams);
    	paramsMap.put("downLoadErrorData", "YES");
   	    Workbook wb = basePeriodTPManager.exportExcel(request, paramsMap);
   	    String excelName = java.net.URLEncoder.encode("常用对象管理-报告" + AppFrameworkUtil.getSystemDate(), "UTF-8");
   		response.setContentType("application/vnd.ms-excel;charset=utf-8");  
   	    response.setHeader("Content-Disposition", "attachment;filename=" + excelName + ".xls");  
   		ServletOutputStream out = response.getOutputStream(); 
   		wb.write(out);
   		//关闭流
   		out.flush();
   		out.close();
    }
	
	/**
   	 * 导出城市基期TP error数据
   	 * 
   	 * @return
   	 * @throws Exception
    */
	@SuppressWarnings("unchecked")
    @RequestMapping("/basePeriodTPConfig/exportCityBasePeriodErrorData.do")
    public void exportCityBasePeriodErrorData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Map<String, Object> paramsMap = getParams(request);
    	paramsMap.putAll((Map<String, Object>) request.getSession().getAttribute(Constant.getExportExcelBasePeriodTPParams));
    	paramsMap.put("downLoadErrorData", "YES");
    	paramsMap.put("isCityBase", "TRUE");
   	    Workbook wb = basePeriodTPManager.exportExcel(request, paramsMap);
   	    String excelName = java.net.URLEncoder.encode("城市基期TP常用对象管理-报告" + AppFrameworkUtil.getSystemDate(), "UTF-8");
   		response.setContentType("application/vnd.ms-excel;charset=utf-8");  
   	    response.setHeader("Content-Disposition", "attachment;filename=" + excelName + ".xls");  
   		ServletOutputStream out = response.getOutputStream(); 
   		wb.write(out);
   		//关闭流
   		out.flush();
   		out.close();
    }
	
	/**
	 * 下载常用对象组导入模版文件
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/basePeriodTPConfig/downloadCommonGroupTemplate")
	public void downloadCommonGroupTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		basePeriodTPManager.downloadBasePeriodTPTemplate(request, response);
	}
	
	/**
	 * 下载城市基期TP常用对象组导入模版文件
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/basePeriodTPConfig/downloadCommonGroupCityBasePeriodTemplate.do")
	public void downloadCommonGroupCityBasePeriodTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		basePeriodTPManager.downloadCityBasePeriodTPTemplate(request, response);
	}
	
	/**
	 * 导入文件数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/basePeriodTPConfig/importCommonGroupFile")
	public void importCommonGroupFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String json = basePeriodTPManager.importBasePeriodTPFile(request);
		AppFrameworkUtil.renderJSON(response, json);
	}
	
	/**
	 * 导入城市基期TP文件数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/basePeriodTPConfig/importCommonGroupCityBasePeriodTPFile.do")
	public void importCommonGroupCityBasePeriodTPFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String json = basePeriodTPManager.importCityBasePeriodTPFile(request);
		AppFrameworkUtil.renderJSON(response, json);
	}
	
	 /**
     * 获取页面参数
     * 
     * @param request
     * @throws Exception
     * @return
     */
    private Map<String, Object> getPageParams(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		request.setCharacterEncoding("UTF-8");
		//添加记录日志属性
		map.put("userName", AppFrameworkUtil.getUserName(request));//用户名称
		map.put("moduleName", "基期TP-管理");//模块名称
		map.put("browser", request.getParameter("browser"));//浏览器
		map.put("ipAddress", AppFrameworkUtil.getRemoteHost(request));//IP地址
		String inputType = request.getParameter("inputType");//1：复选，2：单选;默认为1
		request.setAttribute("inputType", inputType);

		map.put("qVersionCode",request.getParameter("qVersionCode"));
		map.put("loginId", AppFrameworkUtil.getUserId(request));
    	map.put("qYear", request.getParameter("qYear"));
    	map.put("qManf", request.getParameter("qManf"));
		map.put("qModel", request.getParameter("qModel"));
		map.put("qModelName", request.getParameter("qModelName"));
		map.put("qTP", request.getParameter("qTP"));
		map.put("qMSRP", request.getParameter("qMSRP"));
		String qBeginDate = request.getParameter("qBeginDate");
		String qEndDate = request.getParameter("qEndDate");
		if(qBeginDate == null || "null".equals(qBeginDate)) {
			qBeginDate = "";
		}
		map.put("qBeginDate", qBeginDate);

		if(qEndDate == null || "null".equals(qEndDate)) {
			qEndDate = "";
		}
		map.put("qEndDate", qEndDate);

		return map;
	}
}