package com.ways.app.cityPrice.controller;

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

import com.ways.app.cityPrice.service.ITpCityManager;
import com.ways.app.monthly.tp.webapp.controller.TpMonthlyPriceController;
import com.ways.framework.base.BaseController;
import com.ways.framework.utils.AppFrameworkUtil;
import com.ways.framework.utils.Constant;
import com.ways.framework.utils.DataTableUntil;
/**
 * 成交价报表功能Controller
 */
@Controller
public class TpCityController extends BaseController{
	protected final Log log = LogFactory.getLog(TpMonthlyPriceController.class);
	
	@Autowired
	private ITpCityManager tpCityManager; 
	/**
	 * 进入主页方法
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/city/tp/tpCityMain")
	public ModelAndView tpCityMain(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 Model model = new ExtendedModelMap();
		 Map<String, Object> paramsMap = getPageParams(request);
		 tpCityManager.initData(request, paramsMap);
	     return new ModelAndView("/city/tp/tpCityMain",model.asMap());
	}
	
	 /**
	 * 通过条件加载成交价数据
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("/city/tp/tpCityInfo")
    public void findTpCityPriceInfo(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        Map<String, Object> paramsMap = getPageParams(request);
        request.getSession().setAttribute(Constant.getExportExcelCityTpParams, paramsMap);
        
        if("false".equals(request.getParameter("flag"))){
//        	
//        	Map<String, Object> resultMap = new HashMap<String, Object>();
//        	resultMap.put("iTotalRecords", 0);
//			resultMap.put("iTotalDisplayRecords", 0);
//			resultMap.put("sEcho", paramsMap.get("sEcho"));
//			resultMap.put("aaData", null);
//			String jsons = AppFrameworkUtil.serializableJSONData(resultMap);
//			
//        	AppFrameworkUtil.renderJSON(response, jsons);
 //       	return;
        }
        DataTableUntil.getParamByPage(request,paramsMap);
        
        String json = tpCityManager.findFawCityTp(request,paramsMap);
  //      return;
        AppFrameworkUtil.renderJSON(response, json);
    }
	
    /**
   	 * 导出成交价数据
   	 * @return
   	 * @throws Exception
   	 */
       @RequestMapping("/city/tp/tpCityInfoExport")
       public void findTpCityPriceInfoExport(HttpServletRequest request,HttpServletResponse response) throws Exception
       {
    	@SuppressWarnings("unchecked")
		Map<String, Object> paramsMap = getParams(request);
 //   	 Map<String, Object> paramsMap = getPageParams(request);
   		try {
   			Workbook wb = tpCityManager.exportExcel(request, paramsMap);
   	        String excelName = java.net.URLEncoder.encode("成交价-报告"+AppFrameworkUtil.getSystemDate(), "UTF-8");
   			response.setContentType("application/vnd.ms-excel;charset=utf-8");  
   		    response.setHeader("Content-Disposition", "attachment;filename="+excelName+".xls" );  
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
     * 获取页面参数
     * @param request
     * @return
     */
    public Map<String, Object> getPageParams(HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		//添加记录日志属性
		map.put("userName", AppFrameworkUtil.getUserName(request));//用户名称
		map.put("moduleName", "成交价-月报");//模块名称
		map.put("browser", request.getParameter("browser"));//浏览器
		map.put("ipAddress", AppFrameworkUtil.getRemoteHost(request));//IP地址
		map.put("loginId", AppFrameworkUtil.getUserId(request));//登录ID
		map.put("qPriceType", request.getParameter("qPriceType"));
		
		map.put("qYm", request.getParameter("qYm"));//年月
		map.put("qWeek",request.getParameter("qWeek"));//批次
		map.put("qVersionCode", request.getParameter("qVersionCode"));//型号编码
		map.put("qSegmentParentName", request.getParameter("qSegmentParentName"));//细分市场大类
		map.put("qSegmentNameCn", request.getParameter("qSegmentNameCn"));//细分市场
		
		map.put("qBrandNameCn", request.getParameter("qBrandNameCn"));//品牌名称
		map.put("qManfNameCn", request.getParameter("qManfNameCn"));//生产商名称
		map.put("qSubmodelNameCn", request.getParameter("qSubmodelNameCn"));//子车型名称
		map.put("qBodyTypeNameCn", request.getParameter("qBodyTypeNameCn"));//车上形式名称
		
		map.put("qVersionShortNameCn", request.getParameter("qVersionShortNameCn"));//型号标识
		map.put("qEmissionsName", request.getParameter("qEmissionsName"));//排量
		map.put("qTrnsmsNameCn", request.getParameter("qTrnsmsNameCn"));//排挡名称
		map.put("qMsrp", request.getParameter("qMsrp"));//指导价
		map.put("qCityNameCn", request.getParameter("qCityNameCn"));//城市名称
		map.put("qMinPrice", request.getParameter("qMinPrice"));//最低价
		map.put("qMinPrices", request.getParameter("qMinPrices"));//最低价
		map.put("qPrice", request.getParameter("qPrice"));//加权价
		map.put("qPrices", request.getParameter("qPrices"));//市场价加权价
		
		map.put("qReportType", request.getParameter("qReportType"));//报表类型
		map.put("qDateType", request.getParameter("qDateType"));//成交价城市标准
		
		String qBeginDate = request.getParameter("qBeginDate");//成交价开始日期
		String qEndDate = request.getParameter("qEndDate");//成交价结束日期
		
		System.out.println(">>>>>>>qBeginDate>>>>>>>"+qBeginDate);
		
		map.put("qBeginDate", AppFrameworkUtil.getReplaseStr(qBeginDate));
		map.put("qEndDate", AppFrameworkUtil.getReplaseStr(qEndDate));
		return map;
	}
}
