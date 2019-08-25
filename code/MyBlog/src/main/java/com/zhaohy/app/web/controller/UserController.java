package com.zhaohy.app.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.zhaohy.app.service.UserService;
import com.zhaohy.app.utils.AppFrameworkUtil;
import com.zhaohy.app.utils.MD5Util;
@Controller
public class UserController {
	@Resource
	public UserService userService;
	@RequestMapping("/user/test")
	public String test() {
		
		return "test";
	}
	
	@RequestMapping("/user/createUser")
	public void createUser(HttpServletRequest request, HttpServletResponse response) {
		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        
		Map<String, Object> paramsMap = new HashMap<String ,Object>();
		paramsMap.put("userName", multipartRequest.getParameter("userName"));
		String passWord = multipartRequest.getParameter("passWord");
		if(null != passWord) {
			passWord = MD5Util.string2MD5(passWord);
		}
		paramsMap.put("passWord", passWord);
        paramsMap.put("email", multipartRequest.getParameter("email"));
        paramsMap.put("userTypeId", "2");
        String json = userService.saveUser(paramsMap);
        AppFrameworkUtil.renderJSON(response, json);
	}
	
	@RequestMapping("/user/checkUserNameByUserName")
	public void getUserInfoByUserName(HttpServletRequest request, HttpServletResponse response) {
		boolean flag = false;
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("userName", request.getParameter("userName"));
		List<Map<String, Object>> userList = userService.getUserInfoByUserName(request, paramsMap);
		if(userList.size() > 0) {
			flag = true;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("flag", flag);
		AppFrameworkUtil.renderJSON(response, AppFrameworkUtil.structureConfigParamsGroupJSONData(resultMap));
	}

	@RequestMapping("/user/login")
	public String login() {
		
		return "user/login";
	}
	
	@RequestMapping("/user/logout")
	public void logout(HttpServletResponse response) {
	  try {
		response.sendRedirect("http://127.0.0.1:8082/cas/logout?service=http://127.0.0.1:8081/MyBlog/logout.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/user/signup")
	public String signup() {
		
		return "user/signup";
	}
}
