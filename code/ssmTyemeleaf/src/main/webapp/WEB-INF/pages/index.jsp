<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<html>
<body>
<h2>Hello World!</h2>
<div>在线人数：${count}</div><br>
<div><a href="<%=request.getContextPath() %>/commonController/logOut.do">退出系统</a></div>
</body>
</html>
