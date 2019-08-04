<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>首页</title>
<style type="text/css">
body{background:#363B40} 
h1{ color:#E0E0E0}
a{color:#E0E0E0}
</style>
</head>
<body class="body">
	<h1>Welcome to zhaohy's Blog</h1>
	<c:forEach items="${blogList}" var="blog">
		<h3>
			<a href="<%=request.getContextPath() %>/blog/view.do?url=${blog.BLOGURLNAME}&blogId=${blog.BLOG_ID}">${blog.BLOG_NAME}
				&nbsp;&nbsp;&nbsp;所属文集：${blog.COLLECTION_NAME}&nbsp;&nbsp;&nbsp;更新时间：${blog.UPDATE_TIME}</a>
		</h3>
	</c:forEach>
</body>
</html>