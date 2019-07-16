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

import com.ways.app.backstageConfig.service.ICommonGroupManager;
import com.ways.framework.base.BaseController;
import com.ways.framework.utils.AppFrameworkUtil;
import com.ways.framework.utils.DataTableUntil;

/**
 * 常用对象组管理Controller
 * 
 * @author songguobiao
 * 20160401
 */
@Controller
public class CommonGroupController extends BaseController {
	protected final Log log = LogFactory.getLog(CommonGroupController.class);
	
	@Autowired
	private ICommonGroupManager commonGroupManager; 
	
	/**
	 * 进入主页方法
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/backstageConfig/commonGroupMain")
	public ModelAndView commonGroupMain(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    Model model = new ExtendedModelMap();
	    return new ModelAndView("/backstageConfig/commonGroupMain", model.asMap());
	}
	
	/**
	 * 通过条件加载常用对象组
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/backstageConfig/getCommonGroupInfo")
	public void getCommonGroupInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map<String, Object> paramsMap = getPageParams(request);
        DataTableUntil.getParamByPage(request, paramsMap);
        String json = commonGroupManager.getCommonGroupData(request, paramsMap);
        AppFrameworkUtil.renderJSON(response, json);
    }
	
	/**
	 * 获取常用对象组
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/backstageConfig/getCommonGroup")
	public void getCommonGroup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		String json = commonGroupManager.getCommonGroup(paramsMap);
		AppFrameworkUtil.renderJSON(response, json);
	}

	/**
     * 获取初始化子车型控件值
     * @return
     * @throws Exception
     */
    @RequestMapping("/backstageConfig/commonGroup/getSubModelModal")
    public ModelAndView getSubModelModal(HttpServletRequest request,HttpServletResponse response) throws Exception {
    	Map<String, Object> map = getPageParams(request);
    	String subModelShowType = (String)map.get("subModelShowType");
    	String isJp = (String)map.get("isJp");
    	commonGroupManager.getSubModelModal(request, map);
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
     * 获取初始化型号控件值
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/backstageConfig/commonGroup/getVersionModal")
    public ModelAndView getVersionModal(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Map<String, Object> map = getPageParams(request);
    	commonGroupManager.getVersionModal(request, map);
    	String isJp = (String)map.get("isJp");
    	if("0".equals(isJp)) {
    		return new ModelAndView("/backstageConfig/versionModal", new ExtendedModelMap().asMap());
    	} else {
    		return new ModelAndView("/backstageConfig/jpVersionModal", new ExtendedModelMap().asMap());
    	}
    }
    
    /**
     * 保存常用对象组
     * 
     * @param request
     * @param response
     * @throws Excpetion
     */
	@RequestMapping("/backstageConfig/saveCommonGroup")
    public void saveCommonGroup(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Map<String, Object> paramsMap = getModelAndVersionParams(request);
    	String type = (String)paramsMap.get("saveType");
    	String json = "";
    	if("add".equals(type)) {
    		json = commonGroupManager.addCommonGroup(paramsMap);
    	} else {
    		json = commonGroupManager.updateCommonGroup(paramsMap);
    	}
        AppFrameworkUtil.renderJSON(response, json);
	}
    
	/**
	 * 根据分组名称获取分组信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/backstageConfig/getCommonGroupByGroupName")
	public void getCommonGroupByGroupName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> paramsMap = getPageParams(request);
		String json = commonGroupManager.getCommonGroupByGroupName(paramsMap);
		AppFrameworkUtil.renderJSON(response, json);
	}
	/**
	 * 更新常用对象组的型号排序
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/backstageConfig/updateCommonGroupVersionSort")
	public void updateCommonGroupVersionSort(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    Map<String, Object> paramsMap = getVersionSortParams(request);
	    commonGroupManager.updateCommonGroupVersionSort(paramsMap);
	    response.sendRedirect(request.getContextPath() + "/backstageConfig/commonGroupMain.do");
	}
	
    /**
     * 删除常用对象组
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/backstageConfig/deleteCommonGroup")
    public void deleteCommonGroup(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Map<String, Object> map = getPageParams(request);
    	String json = commonGroupManager.deleteCommonGroup(map);
    	AppFrameworkUtil.renderJSON(response, json);
    }
    
	/**
   	 * 导出常用对象数据
   	 * 
   	 * @return
   	 * @throws Exception
    */
	@SuppressWarnings("unchecked")
    @RequestMapping("/backstageConfig/commonGroupInfoExport")
    public void commonGroupInfoExport(HttpServletRequest request,HttpServletResponse response) throws Exception {
    	Map<String, Object> paramsMap = getParams(request);
   	    Workbook wb = commonGroupManager.exportExcel(request, paramsMap);
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
	 * 下载常用对象组导入模版文件
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/backstageConfig/downloadCommonGroupTemplate")
	public void downloadCommonGroupTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    commonGroupManager.downloadCommonGroupTemplate(request, response);
	}
	
	/**
	 * 导入文件数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/backstageConfig/importCommonGroupFile")
	public void importCommonGroupFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String json = commonGroupManager.importCommonGroupFile(request);
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
		map.put("moduleName", "后台配置-常用对象管理");//模块名称
		map.put("browser", request.getParameter("browser"));//浏览器
		map.put("ipAddress", AppFrameworkUtil.getRemoteHost(request));//IP地址
		String inputType = request.getParameter("inputType");//1：复选，2：单选;默认为1
		request.setAttribute("inputType", inputType);
		map.put("isJp", request.getParameter("isJp"));
		if("0".equals(map.get("isJp"))) {
			map.put("subModelIds", request.getParameter("subModelIds"));//本品车型ID
		} else {
			map.put("subModelIds", request.getParameter("subModelIds2"));//竞品车型ID
		}
		map.put("subModelShowType",request.getParameter("subModelShowType"));//子车型弹出框展示类型 3:品牌,4:厂商
		map.put("loginId", AppFrameworkUtil.getUserId(request));//登录ID
    	map.put("groupName", request.getParameter("qGroupName"));//分组名称
    	map.put("groupSort", request.getParameter("qGroupSort"));//分组排序
		map.put("manfName", request.getParameter("qManfName"));//厂商
		map.put("subModelName", request.getParameter("qSubModelName"));//车型名称
		map.put("versionName", request.getParameter("qVersionName"));//型号名称
		map.put("MSRP", request.getParameter("qMSRP"));//指导价
		return map;
	}
    
    /**
     * 获取车型和型号参数
     * 
     * @param request
     * @throws Exception
     * @return
     */
    private Map<String, Object> getModelAndVersionParams(HttpServletRequest request) throws Exception {
    	request.setCharacterEncoding("UTF-8");
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("saveType", request.getParameter("saveType"));//修改类型
    	map.put("groupName", request.getParameter("groupName"));//分组名称
    	map.put("groupSort", request.getParameter("groupSort"));//分组排序
    	map.put("groupId", request.getParameter("groupId"));//分组ID
    	map.put("oldGroupName", request.getParameter("oldGroupName"));// 原本的分组名称
    	map.put("bpSubModelIds", request.getParameterValues("selectedSubModel"));//本品车型ID
		map.put("bpVersionIds", request.getParameterValues("selectedVersion"));//本品型号ID
		map.put("jpVersionIds", request.getParameterValues("selectedVersion2"));//本品型号ID
		map.put("subModelIds", request.getParameter("subModelIds"));//参照品ID
		map.put("versionIds", request.getParameter("versionIds"));//参照品型号ID
		return map;
    }
    
    /**
     * 获取常用对象组型号排序数据
     * 
     * @param request
     * @return
     * @throws Exception
     */
    private Map<String, Object> getVersionSortParams(HttpServletRequest request) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	String groupSort = request.getParameter("groupSort");
    	String versinSort = request.getParameter("versionSort");
    	map.put("groupSort", groupSort);
    	map.put("versionSort", versinSort);
    	return map;
    }
}
