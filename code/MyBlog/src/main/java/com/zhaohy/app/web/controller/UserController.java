package com.zhaohy.app.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhaohy.app.service.UserService;
@Controller
public class UserController {
	@Resource
	public UserService userService;
	@RequestMapping("/user/test")
	public String test() {
		
		return "test";
	}
	
	@RequestMapping("/user/saveUser")
	public void saveUser(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> paramsMap = new HashMap<String ,Object>();
		paramsMap.put("userName", request.getParameter("userName"));
		paramsMap.put("passWord", request.getParameter("passWord"));
        paramsMap.put("email", request.getParameter("email"));
        paramsMap.put("userTypeId", "2");
        userService.saveUser(paramsMap);
	}

	@RequestMapping("/user/login")
	public String login() {
		
		return "user/login";
	}
}
