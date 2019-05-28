package com.ways.app.backstageConfig.controller;

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

import com.ways.app.backstageConfig.service.IOriginalAndCompetitorManager;
import com.ways.framework.base.BaseController;
import com.ways.framework.utils.AppFrameworkUtil;
import com.ways.framework.utils.DataTableUntil;

/**
 * 本竞品配置Controller
 * 
 * @author songguobiao
 * 20160401
 */
@Controller
public class OriginalAndCompetitorController extends BaseController {
	protected final Log log = LogFactory.getLog(OriginalAndCompetitorController.class);
	
	@Autowired
	private IOriginalAndCompetitorManager originalAndCompetitorManager; 
	
	/**
	 * 进入主页方法
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/backstageConfig/originalAndCompetitorMain")
	public ModelAndView originalAndCompetitorMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    Model model = new ExtendedModelMap();
	    return new ModelAndView("/backstageConfig/originalAndCompetitorMain", model.asMap());
	}
	
	/**
	 * 通过条件加载本竞品数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/backstageConfig/getOriginalAndCompetitorData")
	public void getOriginalAndCompetitorData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> paramsMap = getPageParams(request);
        DataTableUntil.getParamByPage(request, paramsMap);
        String json = originalAndCompetitorManager.getOriginalAndCompetitorData(request, paramsMap);
        AppFrameworkUtil.renderJSON(response, json);
    }
	
	/**
     * 获取初始化子车型控件值
     * @return
     * @throws Exception
     */
    @RequestMapping("/backstageConfig/originalAndCompetitor/getSubModelModal")
    public ModelAndView getSubModelModal(HttpServletRequest request,HttpServletResponse response) throws Exception {
    	Map<String, Object> map = getPageParams(request);
    	String subModelShowType = (String)map.get("subModelShowType");
    	String isJp = (String)map.get("isJp");
    	originalAndCompetitorManager.getSubModelModal(request, map);
    	if(null == subModelShowType || "1".equals(subModelShowType)) {
    		if("0".equals(isJp)) {
    			return new ModelAndView("/backstageConfig/subModelModalByBrandtl", new ExtendedModelMap().asMap());
    		} else {
    			return new ModelAndView("/backstageConfig/jpSubModelModalByBrandtl", new ExtendedModelMap().asMap());
    		}
    	} else {
    		if("0".equals(isJp)) {
    			return new ModelAndView("/backstageConfig/subModelModalByManftl", new ExtendedModelMap().asMap());
    		} else {
    			return new ModelAndView("/backstageConfig/jpSubModelModalByManftl", new ExtendedModelMap().asMap());
    		}
    	}
    }
    
    /**
     * 保存本竞品组
     * 
     * @param request
     * @param response
     * @throws Excpetion
     */
	@RequestMapping("/backstageConfig/saveOriginalAndCompetitor")
    public void saveOriginalAndCompetitor(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Map<String, Object> paramsMap = getModelAndVersionParams(request);
    	String type = (String)paramsMap.get("saveType");
    	String json = "";
    	if("add".equals(type)) {
    		json = originalAndCompetitorManager.addOriginalAndCompetitor(paramsMap);
    	} else {
    		json = originalAndCompetitorManager.updateOriginalAndCompetitor(paramsMap);
    	}
        AppFrameworkUtil.renderJSON(response, json);
	}
	
	/**
	 * 根据本品车型ID获取本竞品组数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/backstageConfig/getBJPGroupByBpSubModelId")
	public void getBJPGroupByBpSubModelId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> paramsMap = getModelAndVersionParams(request);
		String json = originalAndCompetitorManager.getBJPGroupByBpSubModelId(paramsMap);
        AppFrameworkUtil.renderJSON(response, json);
	}
	/**
	 * 删除本竞品
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/backstageConfig/deleteOriginalAndCompetitor")
	public void deleteOriginalAndCompetitor(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    Map<String, Object> paramsMap = getPageParams(request);
	    String json = originalAndCompetitorManager.deleteOriginalAndCompetitor(paramsMap);
	    AppFrameworkUtil.renderJSON(response, json);
	}
	
	/**
	 * 获取本竞品数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/backstageConfig/getOriginalAndCompetitor")
	public void getOriginalAndCompetitor(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		String json = originalAndCompetitorManager.getOriginalAndCompetitor(paramsMap);
		AppFrameworkUtil.renderJSON(response, json);
	}
	
	/**
	 * 更新本竞品组的车型排序
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/backstageConfig/updateOriginalAndCompetitorSubModelSort")
	public void updateOriginalAndCompetitor(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> paramsMap = getbjpSubModelSortParams(request);
		originalAndCompetitorManager.updateOriginalAndCompetitorSort(paramsMap);
		response.sendRedirect(request.getContextPath() + "/backstageConfig/originalAndCompetitorMain.do");
	}
	
    /**
   	 * 导出本竞品数据
   	 * 
   	 * @return
   	 * @throws Exception
    */
    @RequestMapping("/backstageConfig/originalAndCompetitorInfoExport")
    @SuppressWarnings("unchecked")
    public void originalAndCompetitorInfoExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Map<String, Object> paramsMap = getParams(request);
  		try {
   		    Workbook wb = originalAndCompetitorManager.exportExcel(request, paramsMap);
   	        String excelName = java.net.URLEncoder.encode("本竞品管理-报告" + AppFrameworkUtil.getSystemDate(), "UTF-8");
   			response.setContentType("application/vnd.ms-excel;charset=utf-8");  
   		    response.setHeader("Content-Disposition", "attachment;filename=" + excelName + ".xls");  
   			ServletOutputStream out = response.getOutputStream(); 
   			wb.write(out);
   			//关闭流
   			out.flush();
   			out.close();
   		} catch (Exception e) {
   			e.printStackTrace();
   		}
    }
    
    /**
	 * 下载常用对象组导入模版文件
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/backstageConfig/downloadOriginalAndCompetitorTemplate")
	public void downloadOriginalAndCompetitorTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		originalAndCompetitorManager.downloadOriginalAndCompetitorTemplate(request, response);
	}
	
	/**
	 * 导入文件数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/backstageConfig/importOriginalAndCompetitorFile")
	public void importOriginalAndCompetitorFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String json = originalAndCompetitorManager.importOriginalAndCompetitorFile(request);
		AppFrameworkUtil.renderJSON(response, json);
	}
    
	 /**
     * 获取页面参数
     * 
     * @param request
     * @return
     */
    private Map<String, Object> getPageParams(HttpServletRequest request) throws Exception {
    	request.setCharacterEncoding("UTF-8");
		Map<String, Object> map = new HashMap<String, Object>();
		//添加记录日志属性
		map.put("userName", AppFrameworkUtil.getUserName(request));//用户名称
		map.put("moduleName", "后台配置-本竞品管理");//模块名称
		map.put("browser", request.getParameter("browser"));//浏览器
		map.put("ipAddress", AppFrameworkUtil.getRemoteHost(request));//IP地址
		map.put("loginId", AppFrameworkUtil.getUserId(request));//登录ID
		String inputType = request.getParameter("inputType");//1：复选，2：单选;默认为1
		request.setAttribute("inputType", inputType);
		map.put("isJp", request.getParameter("isJp"));
		map.put("subModelShowType",request.getParameter("subModelShowType"));//子车型弹出框展示类型 3:品牌,4:厂商
		if("0".equals(map.get("isJp"))) {
			map.put("subModelIds", request.getParameter("subModelIds"));//本品车型ID
		} else {
			map.put("subModelIds", request.getParameter("subModelIds2"));//竞品车型ID
		}
		map.put("bpManfName", request.getParameter("qBpManfName"));//生产商名称
		map.put("bpSubModelId", request.getParameter("bpSubModelId"));//本品车型ID
		map.put("bpSubModelName", request.getParameter("qBpSubModelName"));//本品车型名称
		map.put("bpSubModelSort", request.getParameter("bpSubModelSort"));//本品车型排序
		map.put("jpManfName", request.getParameter("qJpManfName"));//生产商名称
		map.put("jpSubModelName", request.getParameter("qJpSubModelName"));//子车型名称
		return map;
	}
    
    /**
     * 获取车型和型号参数
     * 
     * @param request
     * @return
     */
    private Map<String, Object> getModelAndVersionParams(HttpServletRequest request) throws Exception {
    	request.setCharacterEncoding("UTF-8");
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("saveType", request.getParameter("saveType"));//修改类型
    	map.put("bpSubModelName", request.getParameter("bpSubModelName"));//本品车型名称
    	map.put("bpSubModelId", request.getParameter("bpSubModelId"));//本品车型ID
    	map.put("bpSubModelSort", request.getParameter("bpSubModelSort"));//本品车型排序
    	map.put("oldSort", request.getParameter("oldSort"));//本品原本车型排序
    	map.put("bpSubModelIds", request.getParameterValues("selectedSubModel"));//本品车型ID
		map.put("jpSubModelIds", request.getParameterValues("selectedSubModel2"));//竞品车型ID
		return map;
    }
    
    /**
     * 获取本竞品车型排序数据
     * 
     * @param request
     * @return
     * @throws Exception
     */
    private Map<String, Object> getbjpSubModelSortParams(HttpServletRequest request) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	String bpSort = request.getParameter("bpSort");
    	String jpSort = request.getParameter("jpSort");
    	map.put("bpSort", bpSort);
    	map.put("jpSort", jpSort);
    	return map;
    }
}
