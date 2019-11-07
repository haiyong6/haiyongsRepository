package com.zhaohy.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhaohy.app.service.TestService;

@Controller
public class TestController {
	@Autowired
	TestService testService;
	@RequestMapping("/test.do")
	public String test() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = testService.getUser(paramsMap);
		for(int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			System.out.println(map.get("USER_NAME").toString());
		}
		return "pages/hello.html";
	}
	
	@RequestMapping("/")
	public String goPage() {
		
		
		
		return "index.html";
	}
}
