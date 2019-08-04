package com.zhaohy.app.dao;

import java.util.List;
import java.util.Map;

public interface BlogMapper {

	/**
	 * 上传文件
	 * @param paramsMap
	 */
	void uploadBlog(Map<String, Object> paramsMap);

	/**
	 * 获取文集数据
	 * @param paramsMap
	 * @return
	 */
	List<Map<String, Object>> getCollectionList(Map<String, Object> paramsMap);

	/**
	 * 获取文章
	 * @param paramsMap
	 * @return
	 */
	List<Map<String, Object>> getBlogList(Map<String, Object> paramsMap);

	/**
	 * 根据名字查询文章
	 * @param paramsMap
	 * @return
	 */
	List<Map<String, Object>> getBlogListByName(Map<String, Object> paramsMap);

}
