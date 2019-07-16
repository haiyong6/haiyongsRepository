package com.ways.auth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ways.auth.dao.ILoginDao;
import com.ways.auth.model.MyUserDetails;
import com.ways.framework.base.BaseController;
import com.ways.framework.utils.AppFrameworkUtil;
import com.ways.framework.utils.EncryptionService;
@Controller
public class LoginController extends BaseController{
	@Autowired
	private ILoginDao iLoginDao;
	
	/**
	 * 登录验证
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("/auth/login.do")
    public ModelAndView policyMonthAnalyMain(HttpServletRequest request) throws Exception
    {
    	
    	String loginId = request.getParameter("username");
    	String password = request.getParameter("password");
    	password = EncryptionService.getInstance().encrypt(password);
    	Map<String, String> paramsMap =  new HashMap<String, String>();
    	paramsMap.put("loginId", loginId);
    	paramsMap.put("password", password);
    	MyUserDetails userDetails = iLoginDao.getUserInfo(paramsMap);
    	if(userDetails != null){
    		request.getSession().setAttribute("userDetails", userDetails);
    		request.getSession().setAttribute("ipAddress", AppFrameworkUtil.getRemoteHost(request));
    		request.getSession().setAttribute("browser", request.getParameter("browser"));
    		return new ModelAndView("redirect:/city/tp/tpCityMain.do");
    	}else{
    		return new ModelAndView("redirect:/index.jsp");
    	}
        
    }
}
