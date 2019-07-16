<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title><decorator:title/></title>
    <link href="${ctx}/themes/bootcss/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/themes/bootcss/css/bootstrapmenu.css" rel="stylesheet">	    
    <link href="${ctx}/themes/bootcss/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="${ctx}/themes/bootcss/css/style.css" rel="stylesheet">
    <link href="${ctx}/themes/bootcss/css/cus-icons.css" rel="stylesheet">
    <link href="${ctx}/themes/bootcss/css/main.css" rel="stylesheet">
    <link href="${ctx}/themes/bootcss/css/datepicker.css" rel="stylesheet">
    <script src="${ctx}/include/jquery/jquery.js"></script>
    <script src="${ctx}/include/jqueryForm/jquery.form.js"></script>
    <script src="${ctx}/themes/bootcss/js/bootstrap.min.js"></script>
    <script src="${ctx}/themes/bootcss/js/bootstrap-datepicker.js"></script>
    <script src="${ctx}/themes/bootcss/js/script.js"></script>
    <script src="${ctx}/include/js/general.js"></script>
    <script src="${ctx}/include/js/zh_cn.lang.js"></script>
	
	<link href="${ctx}/include/dataTables/css/jquery.dataTables.min.css" rel="stylesheet">
	<script src="${ctx}/include/dataTables/js/jquery.dataTables.js"></script>
	
	<link href="${ctx}/include/select2/select2.css" rel="stylesheet">
	<script src="${ctx}/include/select2/select2.min.js"></script>
	<script src="${ctx}/include/select2/select2_locale_zh-CN.js"></script>
	
	<link href="${ctx}/include/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
	<script type='text/javascript' src='${ctx}/include/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js'></script>
    <script type='text/javascript' src='${ctx}/include/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js'></script>
    
    
	<script type='text/javascript' src="${ctx}/include/jquery/jquery.validate.js"></script>
	
	<script type='text/javascript' src='${ctx}/include/showLoading/jquery.showLoading.min.js'></script>
	<link href="${ctx}/include/showLoading/showLoading.css" rel="stylesheet" media="screen" /> 
	
	<script src="${ctx}/include/layer/layer.min.js"></script>
	
	<link href="${ctx}/include/icheck/css/_all.css" rel="stylesheet">
	<script src="${ctx}/include/icheck/js/icheck.min.js"></script>
	
	
   <script>
   	var ctx = '${ctx}';
   </script>
    <decorator:head/>
    
</head>
<body<decorator:getProperty property="body.id" writeEntireProperty="true"/><decorator:getProperty property="body.class" writeEntireProperty="true"/>>
	<%@ include file="/common/header.jsp"%>
    <decorator:body/>
    <%@ include file="/common/footer.jsp"%>
</body>
</html>
