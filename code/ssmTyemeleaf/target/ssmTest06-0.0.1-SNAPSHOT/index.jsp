<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<html>
<body>
<h2>Hello World!</h2>
<div>在线人数：${count}</div><br>

<% 
String bathPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
session.invalidate();  
response.sendRedirect(bathPath+"/commonController/test");
%>
</body>
</html>
