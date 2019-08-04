package com.ways.app.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
	public void saveUser(Map<String, Object> paramsMap);

	/**
	 * 获取用户
	 * @param request
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, Object>> getUserListByName(HttpServletRequest request, Map<String, Object> paramsMap);
}
