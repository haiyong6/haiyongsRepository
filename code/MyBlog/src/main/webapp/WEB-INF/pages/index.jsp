<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath() %>/image/title.ico" rel="icon" type="image/x-icon" />
<title>More learn more wanted!</title>
<style type="text/css">
body {
	background: #F3F2EE;font-family:"PT Serif", "Times New Roman", Times, serif;
};
h1 {
	
	width:60%
};
</style>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.4.1/jquery-3.4.1.js"> </script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/blog/index.js"></script>
</head>
<body class="body">
	 <input id="path" type="hidden" value='<%=request.getContextPath() %>'>
	<div class="conterner" style="width: 70%;margin-left:15%;color: #1F0909;">
		<h1 style="border-bottom:1px solid #C5C5C5;padding-bottom:0.8125em;">
			<span> <center>Hello! Wellcome to zhaohy's world!</center></span>
		</h1>
		<h6 style="font-size:13px;font-weight: 400; margin-right:15%">
			<center>
				<ul >
					<li style="display:block;">
						<span>注：本网站文章和我的简书主页同步更新：欢迎访问</span>
						<a target="_blank" href="https://www.jianshu.com/u/9769e4af3aa0" style="text-decoration:none;">
						<span style="color:rgb(6, 85, 136);">我的简书主页</span>
						</a>
					</li>
					<li style="display:block;margin-right:13%;">
						<span>联系邮箱：zhaohaiyongde@foxmail.com</span>
					</li>
				</ul>
			</center>
		</h6>
		<div class="tool" style="margin-right:25%">
			<center>
			<input id="search" type="text">&nbsp;&nbsp;<button id="searchButton" type="button" onclick="loadTitle();">搜索</button>
			</center>
		</div>
		<div class="content" style="margin-left:24%;">
			<%-- <c:forEach items="${blogList}" var="blog">
				<h4>
					<ul >
						<li>
								<a target="_blank" 
									href="<%=request.getContextPath() %>/blog/view.do?url=${blog.URLNAME}&blogId=${blog.BLOG_ID}"
									style="text-decoration:none;"
									>
									<span style="color:rgb(6, 85, 136);">${blog.BLOG_NAME}
										&nbsp;&nbsp;&nbsp;</span>
									
								</a>
						</li>
						<li style="display:block;font-size:13px;font-style: oblique;font-weight: 300;"><span>所属文集：${blog.COLLECTION_NAME}&nbsp;&nbsp;&nbsp;更新时间：${blog.UPDATE_TIME}</span></li>
					</ul>
				</h4>
			</c:forEach> --%>
		</div>
		
	</div>
</body>
</html>