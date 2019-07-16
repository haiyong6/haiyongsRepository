package com.ways.app.carNews.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.ways.app.carNews.service.CarNewsManager;
import com.ways.app.monthly.tp.webapp.controller.TpMonthlyPriceController;
import com.ways.framework.base.BaseController;
import com.ways.framework.utils.AppFrameworkUtil;
import com.ways.framework.utils.DataTableUntil;

/**
 * 新车快讯报表功能Controller
 */
@Controller
public class CarNewsController extends BaseController{
	protected final Log log = LogFactory.getLog(TpMonthlyPriceController.class);
	
	@Autowired
	private CarNewsManager carNewsManager; 
	

	/**
	 * 进入主页方法
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/news/newsCarsMain")
	public ModelAndView tpCityMain(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 Model model = new ExtendedModelMap();
		 Map<String, Object> paramsMap = getPageParams(request);
		 carNewsManager.initData(request, paramsMap);
	     return new ModelAndView("/news/carNewsMain",model.asMap());
	}
	
	/**
	 * 进入车身形式
	 * */
	@RequestMapping("/news/getBodyType")
	public ModelAndView tpTestCityMain(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 Model model = new ExtendedModelMap();
		 Map<String, Object> paramsMap = getPageParams(request);
		 carNewsManager.getBodyTypeList(request, paramsMap);
	     return new ModelAndView("/common2/bodyTypeModal",model.asMap());
	}
	
	 /**
	 * 通过条件加载成交价数据
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("/news/newsCarsInfo")
    public void findTpCityPriceInfo(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        Map<String, Object> paramsMap = getPageParams(request);
        DataTableUntil.getParamByPage(request,paramsMap);
        String json = carNewsManager.findNewCarsInfo(request,paramsMap);
        AppFrameworkUtil.renderJSON(response, json);
    }
    
    /**
     * 获取初始化子车型控件值
     * @return
     * @throws Exception
     */
    @RequestMapping("/news/getSubmodelModal")
    public ModelAndView getSubmodelModal(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
    	Map<String, Object> map = getPageParams(request);
    	Object subModelShowType = map.get("subModelShowType");
    	carNewsManager.getSubmodelModal(request, map);
    	if("2".equals(subModelShowType)){
    		//返回细分市场页
    		return new ModelAndView("/news/subModelModaBySegmentl", new ExtendedModelMap().asMap());
    	}else if("3".equals(subModelShowType)){
    		//返回品牌页
    		return new ModelAndView("/news/subModelModaByBrandtl", new ExtendedModelMap().asMap());
    	}else if("4".equals(subModelShowType)){
    		//返回厂商页
    		return new ModelAndView("/news/subModelModaByManftl", new ExtendedModelMap().asMap());
    	}else{
    		// 返回本品及竞品
    		return new ModelAndView("/news/subModelModal", new ExtendedModelMap().asMap());
    	}
    	
    }
    	
    /**
   	 * 导出成交价数据
   	 * @return
   	 * @throws Exception
   	 */
       @RequestMapping("/news/newsCarsInfoExport")
       public void exportExcel(HttpServletRequest request,HttpServletResponse response){

   		Map<String, Object> paramsMap = getPageParams(request);
   		try {
   			Workbook wb = carNewsManager.exportExcel(request, paramsMap);
   			String excelName = java.net.URLEncoder.encode("新车快讯报告", "UTF-8");
   			response.setContentType("application/vnd.ms-excel;charset=utf-8");
   			SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMddHHmmss");
   			Date date=new Date();
   		    response.setHeader("Content-Disposition", "attachment;filename="+excelName+dateFormater.format(date)+".xls" );  
   			ServletOutputStream out = response.getOutputStream();  
   			wb.write(out);
   			out.flush();
   			out.close();
   		} catch (Exception e) {
   			e.printStackTrace();
   		}
       }
       
	 /**
     * 获取页面参数
     * @param request
     * @return
     */
    public Map<String, Object> getPageParams(HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subModelShowType",request.getParameter("subModelShowType"));//子车型弹出框展示类型2：细分市场,3:品牌,4:厂商，为空则是本品与竟品		
		String inputType = request.getParameter("inputType");//1：复选，2：单选;默认为1
    	if(inputType == null) inputType ="1";  
    	request.setAttribute("inputType",inputType);
    	
		map.put("userId", 2);//用户组ID
		if(null != request.getParameter("subModelId")){
		map.put("subModelId",request.getParameter("subModelId").replaceAll(",", "','"));//车型ID
		}
		
		map.put("bodyTypeId", request.getParameter("hatchbackId"));//车身形式ID
		String beginDate=request.getParameter("beginDate");
		String endDate=request.getParameter("endDate");
		map.put("beginDate", beginDate);//开始时间
		map.put("endDate", endDate);//结束时间
		
		map.put("userName", AppFrameworkUtil.getUserName(request));//用户名称
		map.put("moduleName", "成交价-月报");//模块名称
		map.put("browser", request.getParameter("browser"));//浏览器
		map.put("ipAddress", AppFrameworkUtil.getRemoteHost(request));//IP地址
		map.put("loginId", AppFrameworkUtil.getUserId(request));//登录ID
		
		map.put("qlaunchDate", request.getParameter("qlaunchDate"));//年月
		
		map.put("qlaunchDate",request.getParameter("qlaunchDate"));
		map.put("qversionCode",request.getParameter("qversionCode"));
		map.put("qbrandName",request.getParameter("qbrandName"));
		map.put("qbrandNameEn",request.getParameter("qbrandNameEn"));
		map.put("qmanfName",request.getParameter("qmanfName"));
		map.put("qmanfNameEn",request.getParameter("qmanfNameEn"));
		map.put("qsegment",request.getParameter("qsegment"));
		map.put("qmodelName",request.getParameter("qmodelName"));
		map.put("qmodelNameEn",request.getParameter("qmodelNameEn"));
		map.put("qtrim",request.getParameter("qtrim"));
		map.put("qtrimEn",request.getParameter("qtrimEn"));
		map.put("qversionName",request.getParameter("qversionName"));
		map.put("qversionNameEn",request.getParameter("qversionNameEn"));
		map.put("qMSRP",request.getParameter("qMSRP"));
		map.put("qpreVersionName",request.getParameter("qpreVersionName"));
		map.put("qpreMSRP",request.getParameter("qpreMSRP"));
		map.put("qbodyType",request.getParameter("qbodyType"));
		map.put("qbodyTypeEn",request.getParameter("qbodyTypeEn"));
		map.put("qdriveType",request.getParameter("qdriveType"));
		map.put("qdriveTypeEn",request.getParameter("qdriveTypeEn"));
		map.put("qcarIn",request.getParameter("qcarIn"));
		map.put("qcarInEn",request.getParameter("qcarInEn"));
		map.put("qchangeDescription",request.getParameter("qchangeDescription"));
		map.put("qchangeDescriptionEn",request.getParameter("qchangeDescriptionEn"));
		map.put("qLWH",request.getParameter("qLWH"));
		map.put("qwheelbase",request.getParameter("qwheelbase"));
		map.put("qPL",request.getParameter("qPL"));
		map.put("qtransmission",request.getParameter("qtransmission"));
		map.put("qmaximumPower",request.getParameter("qmaximumPower"));
		map.put("qmaximumTorque",request.getParameter("qmaximumTorque"));
		map.put("qKWL",request.getParameter("qKWL"));
		map.put("qsellingPoints",request.getParameter("qsellingPoints"));
		
		map.put("qReportType", request.getParameter("qReportType"));//报表类型
		map.put("qDateType", request.getParameter("qDateType"));//成交价城市标准
		
		return map;
	}
}
