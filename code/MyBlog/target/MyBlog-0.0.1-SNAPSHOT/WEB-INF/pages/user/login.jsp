<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
</head>
<body>
	<form action="<%=request.getContextPath() %>/user/login.do">
		用户名：<input type="text" name="userName"/><br>
		密码：<input type="text" name="passWord"><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>