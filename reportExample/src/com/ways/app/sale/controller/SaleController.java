package com.ways.app.sale.controller;

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

import com.ways.app.profit.service.IVProfitManager;
import com.ways.app.sale.service.SaleManager;
import com.ways.framework.base.BaseController;
import com.ways.framework.utils.AppFrameworkUtil;
import com.ways.framework.utils.DataTableUntil;

/**
 * 型号利润controller
 */
@Controller
public class SaleController  extends BaseController{
	protected final Log log = LogFactory.getLog(SaleController.class);
	
	@Autowired
	private IVProfitManager ivProfitManager; 
	@Autowired
	private SaleManager saleManager; 
	/**
	 * 进入主页方法
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sale/saleMain")
	public ModelAndView tpCityMain(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 Model model = new ExtendedModelMap();
		 Map<String, Object> paramsMap = getPageParams(request);
		 saleManager.initData(request, paramsMap);
	     return new ModelAndView("/sale/sale",model.asMap());
	}
	
	 /**
	 * 通过条件加载成交价数据
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("/sale/saleInfo")
    public void findTpCityPriceInfo(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        Map<String, Object> paramsMap = getPageParams(request);
        DataTableUntil.getParamByPage(request,paramsMap);
        String json = saleManager.findSaleInfo(request,paramsMap);
        AppFrameworkUtil.renderJSON(response, json);
    }
	
    /**
   	 * 导出型号销量数据
   	 * @return
   	 * @throws Exception
   	 */
       @RequestMapping("/sale/saleInfoExport")
       public void findTpCityPriceInfoExport(HttpServletRequest request,HttpServletResponse response) throws Exception
       {
	    	@SuppressWarnings("unchecked")
			Map<String, Object> paramsMap = getParams(request);
	   		try {
	   			Workbook wb = saleManager.exportExcel(request, paramsMap);
	   	        String excelName = java.net.URLEncoder.encode("型号销量-报告"+AppFrameworkUtil.getSystemDate(), "UTF-8");
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
		map.put("moduleName", "型号销量");//模块名称
		map.put("browser", request.getParameter("browser"));//浏览器
		map.put("ipAddress", AppFrameworkUtil.getRemoteHost(request));//IP地址
		map.put("loginId", AppFrameworkUtil.getUserId(request));//登录ID
		
		map.put("qlaunchDate", request.getParameter("qlaunchDate"));
		map.put("qversionCode", request.getParameter("qversionCode"));
		map.put("qyear", request.getParameter("qyear"));
		map.put("qmonth", request.getParameter("qmonth"));
		map.put("qversionName", request.getParameter("qversionName"));
		map.put("qsale", request.getParameter("qsale"));
		map.put("qmanfName", request.getParameter("qmanfName"));
		map.put("qmodelName", request.getParameter("qmodelName"));
		map.put("qbrandName", request.getParameter("qbrandName"));
		map.put("qremark", request.getParameter("qremark"));
		map.put("qisCustom", request.getParameter("qisCustom"));
		map.put("qMSRP", request.getParameter("qMSRP"));
		
//		map.put("qReportType", request.getParameter("qReportType"));//报表类型
//		map.put("qDateType", request.getParameter("qDateType"));//成交价城市标准
		map.put("qSaleType", request.getParameter("qSaleType"));//销量对应关系
		
		String qBeginDate = request.getParameter("qBeginDate");//开始日期
		String qEndDate = request.getParameter("qEndDate");//结束日期
		String BeginDate = request.getParameter("beginDate");//开始日期
		String EndDate = request.getParameter("endDate");//结束日期
		
		map.put("qBeginDate", AppFrameworkUtil.getReplaseStr(qBeginDate));
		map.put("qEndDate", AppFrameworkUtil.getReplaseStr(qEndDate));
		return map;
	}
}
