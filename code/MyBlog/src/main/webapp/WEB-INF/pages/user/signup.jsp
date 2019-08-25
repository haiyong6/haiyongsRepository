<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册</title>
</head>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.4.1/jquery-3.4.1.js"> </script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/blog/signup.js"></script>
<body style="background: #F3F2EE;">
<h2><center>注册用户</center></h2>
	<form id="checkForm" method="post" style="width:60%;margin-left:45%">
		<left>
			<label>用户名：</label><br>
			<input type="text" id="userName" name="userName">&nbsp;&nbsp;<span style="color:red">*</span>&nbsp;&nbsp;<span id="checkUserName" style="color:red"></span>
			<br><br>
			<label>密码：</label><br>
			<input type="password" id="passWord" name="passWord">&nbsp;&nbsp;<span style="color:red">*</span>&nbsp;&nbsp;<span id="checkPassWord" style="color:red"></span>
			<br><br>
			<label>确认密码：</label><br>
			<input type="password" id="confirmPassWord" name="confirmPassWord">&nbsp;&nbsp;<span style="color:red">*</span>&nbsp;&nbsp;<span id="checkConfirmPassWord" style="color:red"></span>
			<br><br>
			<label>邮箱：</label><br>
			<input type="text" id="email" name="email">&nbsp;&nbsp;
			<br><br>
			<button type="button" style="margin-right:118px;" onclick="submitForm()">注册</button>
		</left>
	</form>
	<input id="path" type="hidden" value='<%=request.getContextPath() %>'>
</body>
</html>