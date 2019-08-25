package com.zhaohy.app.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
	public String saveUser(Map<String, Object> paramsMap);

	/**
	 * 根据用户名获取用户信息
	 * @param request
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, Object>> getUserInfoByUserName(HttpServletRequest request, Map<String, Object> paramsMap);
}
