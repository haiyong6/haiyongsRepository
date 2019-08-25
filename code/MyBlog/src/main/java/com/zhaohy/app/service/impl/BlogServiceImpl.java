package com.zhaohy.app.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.zhaohy.app.dao.BlogMapper;
import com.zhaohy.app.service.BlogService;
import com.zhaohy.app.utils.AppFrameworkUtil;

@Service("blogService")
@Transactional
public class BlogServiceImpl implements BlogService {
	@Resource
	private BlogMapper blogMapper;

	/**
	 * 上传文件
	 */
	@Override
	public String uploadBlog(HttpServletRequest request, Map<String, Object> paramsMap) {
		//MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        List<MultipartFile> fileList = multipartRequest.getFiles("file");
        String path = request.getSession().getServletContext().getRealPath("/blog");
        int t = 0;
        int x = 0;
        String notTypeError = "";
        for(int i = 0; i < fileList.size(); i++) {
            MultipartFile file = fileList.get(i);
            String fileName = file.getOriginalFilename();
            if(fileName.indexOf(".htm") > -1 || fileName.indexOf(".html") > -1) {
            	paramsMap.put("blogName", fileName.split("\\.")[0]);
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                paramsMap.put("blogUrlName", "blog_" + df.format(new Date()) + "." + fileName.split("\\.")[1]);
                paramsMap.put("sysdate", df.format(new Date()));
                paramsMap.put("blogAuthorId", "1");
        		paramsMap.put("blogCollectionId", multipartRequest.getParameter("collectionId"));
        		paramsMap.put("updateAuthorId", "1");
        		paramsMap.put("describe", "");
        		paramsMap.put("url", "");
        		//检查数据库中是否已存在文章 如果存在则更新
        		List<Map<String, Object>> blogList = blogMapper.getBlogListByName(paramsMap);
                if(blogList.size() > 0) {
                	Map<String, Object> blogMap = blogList.get(0);
                	paramsMap.put("existType", "1");
                	paramsMap.put("blogUrlName", blogMap.get("URLNAME"));
                } else {
                	paramsMap.put("existType", "0");
                }

                File dirFile = new File(path, paramsMap.get("blogUrlName").toString());
                //System.out.println("dir.exists()>>"+dirFile.exists());
                try {
                    if(!dirFile.exists()){
                        dirFile.createNewFile();
                    } else {
                    	dirFile.delete();
                    	dirFile.createNewFile();
                    }
                    //System.out.println("dir.exists()>>"+dirFile.exists());
                    //          MultipartFile自带的解析方法
                    //file.get(i).transferTo(dirFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                InputStream input = null;
                FileOutputStream output = null;
                try {
                    input = file.getInputStream();
                    output = new FileOutputStream(dirFile);
                    byte[] bt = new byte[1024*1024];
                    int len = 0;
                    while((len = input.read(bt)) != -1) {
                        output.write(bt, 0, len);
                    }
                    input.close();
                    output.flush();
                    output.close();
                    blogMapper.uploadBlog(paramsMap);
                    t++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                x++;
                notTypeError = x + "条不是htm或html文件，导入失败！";
            }
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data", t + "条导入成功， " + (fileList.size() - t) + "条导入失败！" + notTypeError);
        if("1".equals(paramsMap.get("existType"))) {
        	resultMap.put("data", t + "条更新成功， " + (fileList.size() - t) + "条更新失败！" + notTypeError);
        }
		return AppFrameworkUtil.structureConfigParamsGroupJSONData(resultMap);
	}

	/**
	 * 获取文集数据
	 */
	@Override
	public List<Map<String, Object>> getCollectionList(HttpServletRequest request, Map<String, Object> paramsMap) {
		
		return blogMapper.getCollectionList(paramsMap);
	}

	/**
	 * 获取文章
	 */
	@Override
	public String getBlogList(HttpServletRequest request, Map<String, Object> paramsMap) {
		String loadType = paramsMap.get("loadType").toString();
		int pageNum = Integer.parseInt(paramsMap.get("pageNum").toString());
		int pageCount = Integer.parseInt(paramsMap.get("pageCount").toString());
		int showNum = pageNum*pageCount;
		paramsMap.put("showNum", showNum);
		List<Map<String, Object>> list = blogMapper.getBlogList(paramsMap);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("showAll", true);//是否显示全部
		if(list.size() > 0) {
			int totalCount = Integer.parseInt(list.get(0).get("TOTALCOUNT").toString());
			if(showNum < totalCount) {
				resultMap.put("showAll", false);
			} else {
				resultMap.put("showAll", true);
			}
		}
		if("1".equals(loadType)) {
			resultMap.put("showAll", true);
		}
		resultMap.put("blogList", list);
		
		return AppFrameworkUtil.structureConfigParamsGroupJSONData(resultMap);
	}

	/**
	 * 根据url获取文章详情
	 */
	@Override
	public List<Map<String, Object>> getBlogInfoByUrl(HttpServletRequest request, Map<String, Object> paramsMap) {
		return blogMapper.getBlogInfoByUrl(paramsMap);
	}

	/**
	 * 发布版本日志
	 */
	@Override
	public String createVersionLog(HttpServletRequest request, Map<String, Object> paramsMap) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean success = false;
		String info = "发布失败！";
		int rt = 0;
		rt += blogMapper.createVersionLog(paramsMap);
		if(rt > 0) {
			success = true;
			info = "发布成功！";
		}
		resultMap.put("info", info);
		resultMap.put("success", success);
		return AppFrameworkUtil.structureConfigParamsGroupJSONData(resultMap);
	}
	
}
