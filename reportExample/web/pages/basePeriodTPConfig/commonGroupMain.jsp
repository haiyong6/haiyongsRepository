<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" errorPage="/error.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
<title>后台配置 -常用对象管理</title>

<script type="text/javascript" >
var beginDate = "${beginDate}";
var endDate = "${endDate}";
var defaultBeginDate = "${defaultBeginDate}";
</script>

<style>
th,td {
	white-space: nowrap;
}
</style>
</head>


<body>
	<div class="container-fluid"> 

	
	
	    <div style="margin-left:0px;margin-right:0px">

	
	                		<div class="form-horizontal" style="margin-top:2px;display: inline;">

 
	                			<div class="control-group">
									<input type="hidden" id ="maxDate" value="${endDate}">
									<label class="control-label" for="startDate">时间：</label>
									<div class="controls">
										<div class="form-inline">
						 					<span id="startDate-container" class="input-append date">
												<input type="text" value="${endDate}"  readonly="readonly" class="input-mini white" placeholder="开始日期" id="startDate"><span class="add-on"><i class="icon-th"></i></span>
											</span>
											<span class="2">至</span>
								 			<span id="endDate-container" class="input-append date">
												<input type="text" value="${endDate}" readonly="readonly" class="input-mini white"  placeholder="结束日期" id="endDate"><span class="add-on"><i class="icon-th"></i></span>
											</span>
											
								<input type="hidden" id="btnok" name="btnok" />
								<button onclick="search()" class="btn btn-small btn-primary" style="margin-top:2px;margin-left: auto;" >
		                			<i class="icon-ok icon-white"></i>确认
		                		</button>
											
									   </div>
								</div>
								</div>
							
				   		</div>



			<form action="${ctx}/basePeriodTPConfig/basePeriodTPInfoExport.do" id="exportFormId" class="form-horizontal" style="margin-top:2px;display: inline;">
	       		<input type="hidden" id="downloadUnitId" name="downloadUnitId" />
		    		<button onclick="exportExcel()" class="btn btn-small btn-info" style="margin-top:2px;margin-bottom: 1px;" >
		            	<i class="icon-download icon-white"></i>全国城市基期tp导出
		           	</button>
			</form>
			<button class="btn btn-small btn-info" style="margin-top: 2px;margin-bottom: 1px;margin-left:10px;" data-toggle="modal" data-target="#commonGroupImportPanel">
	           <i class="icon-white"></i>全国城市基期tp导入
	        </button>
	        
	        <form action="${ctx}/basePeriodTPConfig/exportErrorData.do" id="exportFormId" class="form-horizontal" style="margin-top:2px;display: inline;">
	       		<input type="hidden" id="downloadErrorData" name="downloadErrorData" />
		    		<button onclick="exportExcel()" class="btn btn-small btn-info" style="margin-top:2px;margin-bottom: 1px;" >
		            	<i class="icon-download icon-white"></i>查看全国城市基期tp导入失败数据
		     	</button>
			</form>
			
			<form action="${ctx}/basePeriodTPConfig/cityBasePeriodTPInfoExport.do" id="exportFormId" class="form-horizontal" style="margin-top:2px;display: inline;">
	       		<input type="hidden" id="downloadUnitId" name="downloadUnitId" />
		    		<button onclick="exportExcel()" class="btn btn-small btn-info" style="margin-top:2px;margin-bottom: 1px;" >
		            	<i class="icon-download icon-white"></i>城市基期tp导出
		           	</button>
			</form>
			
			<button class="btn btn-small btn-info" style="margin-top: 2px;margin-bottom: 1px;margin-left:10px;" data-toggle="modal" data-target="#commonGroupCityBasePeriodTPImportPanel">
	           <i class="icon-white"></i>城市基期tp导入
	        </button>
			
			<form action="${ctx}/basePeriodTPConfig/exportCityBasePeriodErrorData.do" id="exportFormId" class="form-horizontal" style="margin-top:2px;display: inline;">
	       		<input type="hidden" id="downloadErrorData" name="downloadErrorData" />
		    		<button onclick="exportExcel()" class="btn btn-small btn-info" style="margin-top:2px;margin-bottom: 1px;" >
		            	<i class="icon-download icon-white"></i>查看城市基期tp导入失败数据
		     	</button>
			</form>
	        
	        <div class="clear"></div>
          		<div id="ListViewContents" class="small" style="width:100%;position:relative;border-top:2px solid #0088CC;padding-top:10px">
					<form name="massdelete" method="POST" action="index.php">
						<div style="margin-top:0px;margin-bottom:0px;">
   							<table class="display cell-border" cellSpacing="0" width="100%" id="dataTable">
								<thead>
									<tr id="searchTr">
										<th>&nbsp;</th>
										<th colName="qVersionCode">车型编码</th>
										<th colName="qYear">日期</th>
										<th colName="qManf">厂商名称</th>
										<th colName="qModel">车型</th>
										<th colName="qModelName">车型名称</th>
										<th colName="qMSRP">厂商指导价（元）</th>
										<th colName="qTP">30城市加权均价（元）</th>
									</tr>
									<tr>
										<th align="left" style="width: 30px;">编辑</th>
										<th align="left">车型编码</th>
										<th align="left">日期</th>
										<th align="left">厂商名称</th>
										<th align="left">车型</th>
										<th align="left">车型名称</th>
										<th align="left">厂商指导价（元）</th>
										<th align="left">30城市加权均价（元）</th>
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
<%@ include file="/pages/basePeriodTPConfig/commonGroupImportPanel.jsp"%>
<%@ include file="/pages/basePeriodTPConfig/commonGroupCityBasePeriodTPImportPanel.jsp"%>
<script type="text/javascript" src="${ctx}/js/app/common.js"></script>
<script type="text/javascript" src="${ctx}/js/app/basePeriodTPConfig/basePeriodTPMain.js"></script>
	</body>
</html>

