package com.zhaohy.app.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhaohy.app.service.BlogService;
import com.zhaohy.app.utils.AppFrameworkUtil;

@Controller
public class BlogController {
	
	@Resource
	private BlogService blogService;
	
	/**
	 * 详情页
	 * @return
	 */
	@RequestMapping("/blog/view")
	public String view(HttpServletRequest request, HttpServletResponse response) {
		String url = request.getParameter("url");
		request.setAttribute("blogUrl", "/blog/" + url);
		return "view";
	}
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/blog/blogIndex")
	public String blogIndex(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		List<Map<String, Object>> blogList = blogService.getBlogList(request, paramsMap);
		request.setAttribute("blogList", blogList);
		return "index";
	}
	
	/**
	 * 上传文件
	 * @param request
	 * @param response
	 */
	@RequestMapping("/upload/uploadBlog")
	public void uploadBlog(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		String json = blogService.uploadBlog(request, paramsMap);
		AppFrameworkUtil.renderJSON(response, json);
	}
	
	/**
	 * 跳转到上传页
	 * @return
	 */
	@RequestMapping("/blog/upload")
	public String upload(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("collectionAuthorId", "1");
		List<Map<String, Object>> collectionList = blogService.getCollectionList(request, paramsMap);
		request.setAttribute("collectionList", collectionList);
		
		return "blogManagement/uploadBlog";
	}
}
