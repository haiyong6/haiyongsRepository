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
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("url", url);
		List<Map<String, Object>> blogInfo = blogService.getBlogInfoByUrl(request, paramsMap);
		Map<String, Object> blogMap = blogInfo.get(0);
		request.setAttribute("blogUrl", "/blog/" + url);
		request.setAttribute("blogName", blogMap.get("BLOGNAME"));
		request.setAttribute("blogAuthorName", blogMap.get("AUTHORNAME"));
		request.setAttribute("createTime", blogMap.get("CREATETIME"));
		request.setAttribute("updateTime", blogMap.get("UPDATETIME"));
		return "view";
	}
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/blog/blogIndex")
	public String blogIndex(HttpServletRequest request, HttpServletResponse response) {
		return "index";
	}
	
	
	/**
	 * 获取文章标题列表
	 * @return
	 */
	@RequestMapping("/blog/getBlogTitleInfo")
	public void getBlogTitleInfo(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("searchData", request.getParameter("searchData"));
		List<Map<String, Object>> blogList = blogService.getBlogList(request, paramsMap);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("blogList", blogList);
		AppFrameworkUtil.renderJSON(response, AppFrameworkUtil.structureConfigParamsGroupJSONData(resultMap));
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
