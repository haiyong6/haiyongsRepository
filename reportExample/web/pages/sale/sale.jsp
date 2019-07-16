<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" errorPage="/error.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
<title>销量 - 销量报表-报表</title>
<script type="text/javascript" >
var beginDate = "${beginDate}";
var endDate = "${endDate}";
var defaultBeginDate = "${defaultBeginDate}";
</script>
<script src="${ctx}/js/app/sale/sale.js"></script>
<style>
th,td {
	white-space: nowrap;
}
</style>
</head>
<body>
		<div class="container-fluid"> 
	        <div style="margin-left:0px;margin-right:10px">
          		<div style="margin-top:2px;padding-top:5px;margin-bottom:5px;" >
              		<div class="pull-left" style="margin-bottom:15px;">
                		<div class="form-horizontal" style="margin-top:2px;display: inline;">

                			<label class="control-label" style="width: auto;margin-left: 25px;">销量对应关系:
								<input type="radio" name ="saleType" value = "0" checked>斜对应&nbsp;&nbsp;
	                			<input type="radio" name ="saleType" value = "1">正对应
                			</label>
                			
                			<label class="control-label" style="width: auto;margin-left: 25px;">
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
									   </div>
								    </div>
								</div>
							</label>
							<label class="control-label" style="width: auto;margin-left: 25px;">
								<input type="hidden" id="btnok" name="btnok" />
								<button onclick="search()" class="btn btn-small btn-primary" style="margin-top:2px;margin-left: auto;" >
		                			<i class="icon-ok icon-white"></i>确认
		                		</button>
		                	</label>
							<br>
							
				   		</div>
				   		
				   		<form action="${ctx}/sale/saleInfoExport.do" id="exportFormId" class="form-horizontal" style="margin-top:2px;display: inline;">
	                		<input type="hidden" id="downloadUnitId" name="downloadUnitId" />
							<button onclick="exportExcel()" class="btn btn-small btn-info" style="margin-top:2px;margin-right: 800px;margin-bottom: 1px;" >
		                	<i class="icon-download icon-white"></i>导出
		                	</button>
				   		</form>
	              	</div>
               		<div class="pull-right"></div>
          		</div>
          		<div class="clear"></div> 
           		<div id="ListViewContents" class="small" style="width:100%;position:relative;border-top:2px solid #0088CC;padding-top:10px">
					<form name="massdelete" method="POST" action="index.php">
						<div style="margin-top:0px;margin-bottom:0px;">
   							<table class="display cell-border" cellSpacing="0" width="100%" id="dataTable">
								<thead>
									<tr id="searchTr">
										<th>&nbsp;</th>
										<th colName="qlaunchDate">上市时间</th>
										<th colName="qversionCode">型号编码</th>
										<th colName="qyear">年</th>
										<th colName="qmonth">月</th>
										<th colName="qversionName">型号名称</th>
										<th colName="qsale">型号销量</th>
										<th colName="qmanfName">生产商</th>
										<th colName="qmodelName">子车型</th>										
										<th colName="qbrandName">品牌</th>
										<th colName="qremark">销量说明</th>
										<th colName="qisCustom">是否自加销量</th>
										<th colName="qMSRP">指导价</th>
									</tr>
									
									<tr>
										<th align="left" style="width: 30px;">编辑</th>
										<th align="left">上市时间</th>
										<th align="left">型号编码</th>
										<th align="left">年</th>
										<th align="left">月</th>
										<th align="left">型号名称</th>
										<th align="left">型号销量</th>
										<th align="left">生产商</th>
										<th align="left">子车型</th>										
										<th align="left">品牌</th>
										<th align="left">销量说明</th>
										<th align="left">是否自加销量</th>
										<th align="left">指导价</th>
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
	</body>
</html>

