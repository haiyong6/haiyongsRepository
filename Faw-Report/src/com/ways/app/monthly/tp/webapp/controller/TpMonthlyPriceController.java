package com.ways.app.monthly.tp.webapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ways.app.monthly.tp.service.ITpMonthlyPriceManager;
import com.ways.framework.base.BaseController;
import com.ways.framework.utils.AppFrameworkUtil;
import com.ways.framework.utils.DataTableUntil;

@Controller
public class TpMonthlyPriceController extends BaseController{

protected final Log log = LogFactory.getLog(TpMonthlyPriceController.class);

@Autowired
private ITpMonthlyPriceManager tpMonthlyPriceManager;
	
	
	/**
	 * 进入主页方法
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("/monthly/tp/tpMonthlyPriceMain")
    public ModelAndView tpMonthlyPriceMain(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        Model model = new ExtendedModelMap();
        return new ModelAndView("/monthly/tp/tpMonthlyPriceMain",model.asMap());
    }
    
    /**
	 * 进入主页方法
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("/monthly/tp/findTpMonthlyPriceInfo")
    public void findTpMonthlyPriceInfo(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        Map<String, Object> paramsMap = getPageParams(request);
        DataTableUntil.getParamByPage(request,paramsMap);
        String json = tpMonthlyPriceManager.findTpMonthlyPriceInfo(paramsMap);
        AppFrameworkUtil.renderJSON(response, json);
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
		
		map.put("qYear", request.getParameter("qYear"));
		map.put("qMonth", request.getParameter("qMonth"));
		map.put("qPrice", request.getParameter("qPrice"));
		map.put("qVersioncode", request.getParameter("qVersioncode"));
		return map;
	}
}
