<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>blog view</title>
</head>
<body>
	<h2><a href="<%=request.getContextPath() %>/blog/blogIndex.do">返回首页</a></h2>
	<jsp:include page="${blogUrl}"/>
	<footer></footer>
</body>
</html>