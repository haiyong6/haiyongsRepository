package com.zhaohy.app.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhaohy.app.service.BlogService;
import com.zhaohy.app.service.UserService;
import com.zhaohy.app.utils.AppFrameworkUtil;

@Controller
public class BlogController {
	
	@Resource
	private BlogService blogService;
	@Resource
	public UserService userService;
	
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
		List<Map<String, Object>> userList = AppFrameworkUtil.getUserInfo(request, userService);//获取用户信息
		AppFrameworkUtil.setUserLoginInfo(request, userList);//储存用户登录
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
		paramsMap.put("loadType", request.getParameter("loadType"));//1为加载全部，2为加载更多
		paramsMap.put("pageNum", request.getParameter("pageNum"));//默认为1
		paramsMap.put("pageCount", 20);
		String json = blogService.getBlogList(request, paramsMap);
		AppFrameworkUtil.renderJSON(response, json);
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
	@RequestMapping("/blog/manager/upload")
	public String upload(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> userList = AppFrameworkUtil.getUserInfo(request, userService);//获取用户信息
		AppFrameworkUtil.setUserLoginInfo(request, userList);
		if(userList.size() > 0) {
			Map<String, Object> userMap = userList.get(0);
			if("1".equals(userMap.get("USER_TYPE_ID").toString())) {//管理员
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("collectionAuthorId", "1");
				List<Map<String, Object>> collectionList = blogService.getCollectionList(request, paramsMap);
				request.setAttribute("collectionList", collectionList);
				return "/blogManagement/uploadBlog";
			}
		}
		return "index";
	}
	
	/**
	 * 发布版本日志
	 * @param request
	 * @param response
	 */
	@RequestMapping("/blog/manager/createVersionLog")
	public void createVersionLog(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        paramsMap.put("sysdate", df.format(new Date()));
        paramsMap.put("versionTag", request.getParameter("versionTag"));
        paramsMap.put("versionDescribe", request.getParameter("versionDescribe"));
		String json = blogService.createVersionLog(request, paramsMap);
		AppFrameworkUtil.renderJSON(response, json);
	}
}
