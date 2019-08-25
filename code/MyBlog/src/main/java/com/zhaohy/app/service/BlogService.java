package com.zhaohy.app.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface BlogService {

	/**
	 * 上传文件
	 * @param request
	 * @param paramsMap
	 * @return
	 */
	String uploadBlog(HttpServletRequest request, Map<String, Object> paramsMap);

	/**
	 * 获取文集数据
	 * @param request
	 * @param paramsMap
	 * @return
	 */
	List<Map<String, Object>> getCollectionList(HttpServletRequest request, Map<String, Object> paramsMap);

	/**
	 * 获取文章
	 * @param request
	 * @param paramsMap
	 * @return
	 */
	String getBlogList(HttpServletRequest request, Map<String, Object> paramsMap);

	/**
	 * 根据url获取文章详情信息
	 * @param request
	 * @param paramsMap
	 * @return
	 */
	List<Map<String, Object>> getBlogInfoByUrl(HttpServletRequest request, Map<String, Object> paramsMap);

	/**
	 * 发布版本日志
	 * @param request
	 * @param paramsMap
	 * @return
	 */
	String createVersionLog(HttpServletRequest request, Map<String, Object> paramsMap);

}
