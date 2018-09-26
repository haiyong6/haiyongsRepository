<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" errorPage="/error.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
<title>后台配置 -常用对象管理</title>
<style>
th,td {
	white-space: nowrap;
}
</style>
</head>
<body>
	<div class="container-fluid"> 
	    <div style="margin-left:0px;margin-right:0px">
	        <button class="btn btn-small btn-primary" onclick="toAdd()" style="margin-top: 2px;margin-bottom: 1px;margin-left:20px;">
	           <i class="icon-plus icon-white"></i>新增
	        </button>
	        <button class="btn btn-small btn-info" style="margin-top: 2px;margin-bottom: 1px;margin-left:0px;" data-toggle="modal" data-target="#commonGroupSortPanel">
	           <i class="icon-resize-vertical icon-white"></i>设置排序
	        </button>
			<form action="${ctx}/backstageConfig/commonGroupInfoExport.do" id="exportFormId" class="form-horizontal" style="margin-top:2px;display: inline;">
	       		<input type="hidden" id="downloadUnitId" name="downloadUnitId" />
		    		<button onclick="exportExcel()" class="btn btn-small btn-info" style="margin-top:2px;margin-bottom: 1px;" >
		            	<i class="icon-download icon-white"></i>导出
		           	</button>
			</form>
			<button class="btn btn-small btn-info" style="margin-top: 2px;margin-bottom: 1px;margin-left:10px;" data-toggle="modal" data-target="#commonGroupImportPanel">
	           <i class="icon-white"></i>导入
	        </button>
	        <div class="clear"></div>
          		<div id="ListViewContents" class="small" style="width:100%;position:relative;border-top:2px solid #0088CC;padding-top:10px">
					<form name="massdelete" method="POST" action="index.php">
						<div style="margin-top:0px;margin-bottom:0px;">
   							<table class="display cell-border" cellSpacing="0" width="100%" id="dataTable">
								<thead>
									<tr id="searchTr">
										<th>&nbsp;</th>
										<th colName="qGroupName">分组名</th>
										<th>&nbsp;</th>
										<th colName="qManfName">厂商</th>
										<th colName="qSubModelName">车型</th>
										<th colName="qVersionName">型号</th>
										<th colName="qMSRP">指导价</th>
										<th>&nbsp;</th>
									</tr>
									<tr>
										<th align="left" style="width: 30px;">编辑</th>
										<th align="left">分组名</th>
										<th align="left">分组排序</th>
										<th align="left">厂商</th>
										<th align="left">车型</th>
										<th align="left">型号</th>
										<th align="left">指导价</th>
										<th align="left">组型号排序</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</form>
        		</div>
      		</div>
    	</div>
<%@ include file="/pages/backstageConfig/commonGroupPanel.jsp"%>
<%@ include file="/pages/backstageConfig/commonGroupImportPanel.jsp"%>
<%@ include file="/pages/backstageConfig/commonGroupSortPanel.jsp"%>
<script type="text/javascript" src="${ctx}/js/app/common.js"></script>
<script type="text/javascript" src="${ctx}/js/app/backstageConfig/commonGroupMain.js"></script>
	</body>
</html>

