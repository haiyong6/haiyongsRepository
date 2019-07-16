<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" errorPage="/error.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
<title>返点返利 - 型号利润-报表</title>
<script type="text/javascript" >
var beginDate = "${beginDate}";
var endDate = "${endDate}";
var defaultBeginDate = "${defaultBeginDate}";
</script>
<script src="${ctx}/js/app/profit/vProfitMain.js"></script>
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
                			<label class="control-label" style="width: auto;margin-left: 15px;">报表类型:
								<input type="radio" name ="reportType" value = "0" checked>周报&nbsp;&nbsp;
	                			<input type="radio" name ="reportType" value = "1">月报
                			</label>
                			<label class="control-label" style="width: auto;margin-left: 25px;">城市Mix年份:
								<c:forEach items="${cityList}" var="city" varStatus="status">
									<input type="radio" name ="dateType" value ="${city.YEAR}" ${status.index == 0?'checked':''}><font title="${city.CITY}">${city.YEAR}年</font>&nbsp;&nbsp;
								</c:forEach>
                			</label>
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
				   		</div>
				   		
				   		<form action="${ctx}/profit/vProfitInfoExport.do" id="exportFormId" class="form-horizontal" style="margin-top:2px;display: inline;">
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
										<th colName="qYm">年月</th>
										<th colName="qWeek">批次</th>
										<th colName="qManfNameCn">生产商</th>
										<th colName="qBrandNameCn">品牌</th>
										<th colName="qSubmodelNameCn">子车型</th>
										<th colName="qSegmentParentName">细分市场大类</th>
										<th colName="qSegmentNameCn">细分市场</th>
										<th colName="qBodyTypeNameCn">车身形势</th>
										<th colName="qVersionNameCn">型号</th>
										<th colName="qVersionShortNameEn">型号标识</th>
										<th colName="qVersionCode">型号编码</th>	
										<th colName="qMsrp">指导价</th>
										<th colName="qRebatePrice">返利</th>
										<th colName="qRewardAssessment">考核奖励</th>
										<th colName="qPromotionalAllowance">促销</th>
										<th colName="qInvoicePrice">经销商开票价</th>
										<th colName="qPrice">加权成交价</th>
										<th colName="qModelProfit">经销商利润</th>
										<th colName="qSale">销量</th>
									</tr>
									
									<tr>
										<th align="left" style="width: 30px;">编辑</th>
										<th align="left">年月</th>
										<th align="left">批次</th>
										<th align="left">生产商</th>
										<th align="left">品牌</th>
										<th align="left">子车型</th>
										<th align="left">细分市场大类</th>
										<th align="left">细分市场</th>
										<th align="left">车身形势</th>
										<th align="left">型号</th>
										<th align="left">型号标识</th>
										<th align="left">型号编码</th>
										<th align="left">指导价</th>
										
										<th align="left">返利</th>
										<th align="left">考核奖励</th>
										<th align="left">促销</th>
										<th align="left">经销商开票价</th>
										<th align="left">加权成交价</th>
										<th align="left">经销商利润</th>
										<th align="left">销量</th>
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

