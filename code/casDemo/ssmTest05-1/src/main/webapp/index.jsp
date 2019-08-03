<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function backToLogin(){
		window.location="/ssmTest05/user/logout.do";
	}
</script>
<body>
<h2>Hello SpringMVC-222!</h2><br/>
<button id="button" onclick="backToLogin()">退出</button>
</body>
</html>
