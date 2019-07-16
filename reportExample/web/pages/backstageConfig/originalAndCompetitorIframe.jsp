<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" errorPage="/error.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
<title>后台配置 -本竞品管理</title>
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
<style>
.td1{
    width: 15%;
}
.td2{
    width: 35%;
}
th,td {
	white-space: nowrap;
}
</style>
</head>
<body>
<form id="originalAndCompetitorForm" method="post" action="${ctx}/backstageConfig/saveOriginalAndCompetitor.do" style="margin-left: 20px;">
          <div style="margin-top: 0px;margin-bottom: 0px;">
     	     <table class="display cell-border" cellSpacing="0" width="100%" id="originalAndCompetitorTable">
   	          <tbody>
   		       <tr>
   		        <td class="td1">本品车型排序：</td>
   		        <td class="td2"><input type="text" id="bpSubModelSort" name="bpSubModelSort" style="margin-left:20px;"/></td>
   		        <input type="hidden" id="oldSort" name="oldSort"/>
   		       </tr>
   		       <tr>
   		        <td class="td1">
				    <label class="control-label" for="analysisDimension">本品：</label>
				</td>
				<td class="td2">
   		          <div class="control-group" name = "objectType1">
					  <div class="controls">
						<div class="span2" style="width:90px">
					      <a href="#subModelModal" role="button" class="btn btn-small subModelSelector" data-toggle="modal">选择车型</a>
						</div>
							<input type="hidden" value="1,2,3" class="selectedPooAttributeIds" >
						  	  <div class="span4" style="margin-left:0px" id="subModelModalResultContainer"></div>
					  </div>
				  </div>
   		        </td>
   		       </tr>
   		       <tr>
   		         <td class="td1">
				    <label class="control-label" for="analysisDimension">竞品：</label>
				</td>
				<td class="td2">
   		          <div class="control-group" name = "objectType1">
					  <div class="controls">
						<div class="span2" style="width:90px">
					      <a href="#subModelModal2" role="button" class="btn btn-small subModelSelector" data-toggle="modal">选择车型</a>
						</div>
							<input type="hidden" value="1,2,3" class="selectedPooAttributeIds" >
						  	  <div class="span4" style="margin-left:0px" id="subModelModalResultContainer2"></div>
					  </div>
				  </div>
   		        </td>
   		       </tr>
   		      </tbody>
   		    </table>
   		 </div>
   		 </br>
   		 <div>
   		    <input type="hidden" id="saveType" name="saveType"/>
   		   	<button type="button" id="confirmSubmit" onclick="addOriginalAndCompetitor()">确认</button>&nbsp;&nbsp;
   		   	<button type="button" id="resetBtn">重置</button>

   		 </div>
       </form>
       <%@ include file="/common/template/subModelModalTemplate.jsp"%>
       <%@ include file="/common/template/versionModalTemplate.jsp"%>
       <%@ include file="/common/template/jpSubModelModalTemplate.jsp"%>
       <%@ include file="/common/template/jpVersionModalTemplate.jsp"%>
       <script type="text/javascript" src="${ctx}/js/app/backstageConfig/originalAndCompetitorMain.js"></script>
       <script type="text/javascript" src="${ctx}/js/app/common.js"></script>
</body>
</html>