package com.ways.app.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ways.app.service.UserService;
@Controller
public class UserController {
	@Resource
	public UserService userService;
	@RequestMapping("/user/test")
	public String test() {
		
		return "test";
	}
	
	@RequestMapping("/user/saveUser")
	public void saveUser() {
		Map<String, Object> paramsMap = new HashMap<String ,Object>();
		paramsMap.put("userName", "zhaohy4");
        paramsMap.put("sex", 1);
        paramsMap.put("job", "java软件工程师");
        paramsMap.put("tel", "189xxxx0598");
        paramsMap.put("email", "1025XXXX40@qq.com");
        paramsMap.put("hobby", "编程，运动");
        userService.saveUser(paramsMap);
	}

  @RequestMapping("/user/logout")
  public void logout(HttpServletResponse response) {
	  try {
		response.sendRedirect("http://127.0.0.1:8082/cas/logout?service=http://127.0.0.1:8081/ssmTest05/logout.jsp");
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
	 
}
