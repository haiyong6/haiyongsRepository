package com.ways.app.profit.controller;

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
import com.ways.framework.base.BaseController;
import com.ways.framework.utils.AppFrameworkUtil;
import com.ways.framework.utils.DataTableUntil;

/**
 * 型号利润controller
 */
@Controller
public class VProfitController  extends BaseController{
	protected final Log log = LogFactory.getLog(VProfitController.class);
	
	@Autowired
	private IVProfitManager ivProfitManager; 
	/**
	 * 进入主页方法
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/profit/vProfitMain")
	public ModelAndView tpCityMain(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 Model model = new ExtendedModelMap();
		 Map<String, Object> paramsMap = getPageParams(request);
		 ivProfitManager.initData(request, paramsMap);
	     return new ModelAndView("/profit/vProfitMain",model.asMap());
	}
	
	 /**
	 * 通过条件加载成交价数据
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("/profit/vProfitInfo")
    public void findTpCityPriceInfo(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        Map<String, Object> paramsMap = getPageParams(request);
        DataTableUntil.getParamByPage(request,paramsMap);
        String json = ivProfitManager.findFawVProfit(request,paramsMap);
        AppFrameworkUtil.renderJSON(response, json);
    }
	
    /**
   	 * 导出型号利润数据
   	 * @return
   	 * @throws Exception
   	 */
       @RequestMapping("/profit/vProfitInfoExport")
       public void findTpCityPriceInfoExport(HttpServletRequest request,HttpServletResponse response) throws Exception
       {
	    	@SuppressWarnings("unchecked")
			Map<String, Object> paramsMap = getParams(request);
	   		try {
	   			Workbook wb = ivProfitManager.exportExcel(request, paramsMap);
	   	        String excelName = java.net.URLEncoder.encode("型号利润-报告"+AppFrameworkUtil.getSystemDate(), "UTF-8");
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
		map.put("moduleName", "型号利润");//模块名称
		map.put("browser", request.getParameter("browser"));//浏览器
		map.put("ipAddress", AppFrameworkUtil.getRemoteHost(request));//IP地址
		map.put("loginId", AppFrameworkUtil.getUserId(request));//登录ID
		
		map.put("qYm", request.getParameter("qYm"));//年月
		map.put("qWeek",request.getParameter("qWeek"));//批次
		map.put("qManfNameCn", request.getParameter("qManfNameCn"));//生产商
		map.put("qBrandNameCn", request.getParameter("qBrandNameCn"));//品牌
		map.put("qSubmodelNameCn", request.getParameter("qSubmodelNameCn"));//子车型
		map.put("qSegmentParentName", request.getParameter("qSegmentParentName"));//细分市场大类
		map.put("qSegmentNameCn", request.getParameter("qSegmentNameCn"));//细分市场
		map.put("qBodyTypeNameCn", request.getParameter("qBodyTypeNameCn"));//车身形势
		map.put("qVersionNameCn", request.getParameter("qVersionNameCn"));//型号名称
		map.put("qVersionShortNameEn", request.getParameter("qVersionShortNameEn"));//型号标识
		map.put("qVersionCode", request.getParameter("qVersionCode"));//型号编码
		map.put("qMsrp", request.getParameter("qMsrp"));//指导价
		map.put("qRebatePrice", request.getParameter("qRebatePrice"));//返利金额
		map.put("qRewardAssessment", request.getParameter("qRewardAssessment"));//考核奖励
		map.put("qPromotionalAllowance", request.getParameter("qPromotionalAllowance"));//促销补贴
		map.put("qInvoicePrice", request.getParameter("qInvoicePrice"));//开票价
		map.put("qPrice", request.getParameter("qPrice"));//加权成交价
		map.put("qModelProfit", request.getParameter("qModelProfit"));//车型利润
		map.put("qSale", request.getParameter("qSale"));//销量
		
		map.put("qReportType", request.getParameter("qReportType"));//报表类型
		map.put("qDateType", request.getParameter("qDateType"));//成交价城市标准
		map.put("qSaleType", request.getParameter("qSaleType"));//销量对应关系
		
		String qBeginDate = request.getParameter("qBeginDate");//开始日期
		String qEndDate = request.getParameter("qEndDate");//结束日期
		
		map.put("qBeginDate", AppFrameworkUtil.getReplaseStr(qBeginDate));
		map.put("qEndDate", AppFrameworkUtil.getReplaseStr(qEndDate));
		return map;
	}
}
