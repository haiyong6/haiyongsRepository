<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" errorPage="/error.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
<title>成交价 - 成交价-月报</title>
<script src="${ctx}/js/app/monthly/tp/tpMonthlyPriceMain.js">
	
</script>
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
                		<form action="#" style="margin-top:2px;display: inline;">
	                		<input type="hidden" id="downloadUnitId" name="downloadUnitId" />
							<button type="submit" class="btn btn-small btn-info" style="margin-top:2px;margin-right: 1500px;" >
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
										<th colName="qYear">年</th>
										<th colName="qMonth">月</th>
										<th colName="qPrice">价格</th>
										<th colName="qVersioncode">型号编码</th>
									</tr>
									<tr>
										<th align="left" style="width: 30px;">编辑</th>
										<th align="left">年</th>
										<th align="left">月</th>
										<th align="left">价格</th>
										<th align="left">型号编码</th>
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

