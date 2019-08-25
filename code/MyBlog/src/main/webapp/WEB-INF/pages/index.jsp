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
	<div class="conterner" style="width: 50%;margin-left:15%;color: #1F0909;float:left;">
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
			<input placeholder="请输入搜索标题..." id="search" type="text">&nbsp;&nbsp;<button id="searchButton" type="button" onclick="searchData()">搜索</button>
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
		<div id="moreTool">
			<center>
				<a href="#" onclick="loadMore()" style="text-decoration:none;"><span id="loadMoreSpan" style="color:rgb(6, 85, 136);display:inline;">加载更多</span></a>
				 &nbsp;&nbsp;&nbsp;
				 <a href="#" onclick="loadAll()" style="text-decoration:none;"><span id="loadAllSpan" style="color:rgb(6, 85, 136);display:inline;">加载全部</span></a>
			</center>
		</div>
	</div>
	
	<div class="conterner1" style="width: 20%;margin-left:15%;color: #1F0909;float:right;">
		<h3>
				<div id="loginButton" style="display:block;">
					<a href="<%=request.getContextPath() %>/blog/manager/upload.do" style="text-decoration:none;">
						<span style="color:rgb(6, 85, 136);display:inline;">登录</span>
						<span style="color:black;font-size:10px;font-weight:400;display:inline;">可留言</span>
					</a>
					&nbsp;&nbsp;
					<a href="#" onclick="signUp()" style="text-decoration:none;">
						<span style="color:rgb(6, 85, 136);">注册</span>
					</a>	
				</div>
				<div id="userHello" style="display:none;">
					<span>hi,${userName}</span> &nbsp;&nbsp;
					<a href="#" id="backSystem" onclick="backToLogin()" style="text-decoration:none;">
						<span style="color:rgb(6, 85, 136);display:inline;font-size:12px;">退出登录</span>
					</a href="#">
				</div>
			</a>
		</h3>
		<!-- <h5>
			<span>版本更新信息：</span>
			<ul>
				<li>josjosj</li>
				<li>josjosj</li>
			</ul>
		</h5> -->
	</div>
	<input id="pageNum" type="hidden" value=1>
	<input id="logined" type="hidden" value=${logined}>
	<input id="userName" type="hidden" value=${userName}>
	<input id="userId" type="hidden" value=${userId}>
	<input id="userType" type="hidden" value=${userType}>
	<input id="path" type="hidden" value='<%=request.getContextPath() %>'>
</body>
</html>