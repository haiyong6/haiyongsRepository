<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" errorPage="/error.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
<title>成交价 - 成交价-报表</title>
<script type="text/javascript" >
var beginDate = "${beginDate}";
var endDate = "${endDate}";
var defaultBeginDate = "${defaultBeginDate}";

</script>
<script src="${ctx}/js/app/city/tp/tpCityMain.js"></script>

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
                			
                			 <label class="control-label" style="width: auto;margin-left: 15px;">价格类型:
                			 	<input type="radio" id="priceType" class="priceType" name ="priceType" value = "0" >最低参考价
								<input type="radio" id="priceType" class="priceType" name ="priceType" value = "1" >市场成交价
								<input type="radio" id="priceType" class="priceType" name ="priceType" value = "2" checked>一汽大众TP
								
								&nbsp;&nbsp;
                			</label>
                			
                			<label class="control-label" style="width: auto;margin-left: 25px;display:none;">城市Mix年份:
								<c:forEach items="${cityList}" var="city" varStatus="status">
									<input type="radio" name ="dateType" value ="${city.YEAR}" ${status.index == 0?'checked':''}><font title="${city.CITY}">${city.YEAR}年</font>&nbsp;&nbsp;
								</c:forEach>
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
				   		
				   		<form action="${ctx}/city/tp/tpCityInfoExport.do" id="exportFormId" class="form-horizontal" style="margin-top:2px;display: inline;">
	                		<input type="hidden" id="downloadUnitId" name="downloadUnitId" />
	                		<input type="hidden" id="priceTypeId" name="priceTypeId" />
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
										<th colName="qVersionCode">型号编码</th>
										<th colName="qSegmentParentName">细分市场大类</th>
										<th colName="qSegmentNameCn">细分市场</th>
										<th colName="qBrandNameCn">品牌</th>
										<th colName="qManfNameCn">生产商</th>
										<th colName="qSubmodelNameCn">子车型</th>
										<th colName="qBodyTypeNameCn">车身形势</th>
										<th colName="qVersionShortNameCn">型号标识</th>
										<th colName="qLaunchDate">上市日期</th>
										<th colName="qEmissionsName">排量</th>
										<th colName="qTrnsmsNameCn">排挡</th>
										<th colName="qMsrp">指导价</th>
										<th colName="qCityNameCn">城市</th>
										<th colName="qMinPrice">最低价</th>
										<th colName="qMinPrices">市场价</th>
										
										<th colName="qPrice">最低价加权成交价</th>
										<th colName="qPrices">市场价加权成交价</th>
		
										<th colName="qPriceFawvw">一汽大众TP加权成交价</th>
									</tr>
									
									<tr>
										<th align="left" style="width: 30px;">编辑</th>
										<th align="left">年月</th>
										<th align="left">批次</th>
										<th align="left">型号编码</th>
										<th align="left">细分市场大类</th>
										<th align="left">细分市场</th>
										<th align="left">品牌</th>
										<th align="left">生产商</th>
										<th align="left">子车型</th>
										<th align="left">车身形势</th>
										<th align="left">型号标识</th>
										<th align="left">上市日期</th>
										<th align="left">排量</th>
										<th align="left">排挡</th>
										<th align="left">指导价</th>
										<th align="left">城市</th>
										<th align="left">最低价</th>
										<th align="left">市场价</th>
										<th align="left">最低价加权成交价</th>
										<th align="left">市场价加权成交价</th>
					
										<th align="left">一汽大众TP加权成交价</th> 
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

