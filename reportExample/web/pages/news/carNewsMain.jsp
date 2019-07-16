<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" errorPage="/error.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
<title>新车快讯 - 新车快讯 -报表</title>
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
	        <div style="margin-left:0px;margin-right:10px">
          		<div style="margin-top:2px;padding-top:5px;margin-bottom:5px;" >
              		<div class="pull-left" style="margin-bottom:15px;">
                		<div class="form-horizontal" style="margin-top:2px;display: inline;">
                		
                		    <label class="control-label" style="width: auto;margin-left: 15px;">
	                			<div class="control-group">
									<input type="hidden" id ="maxDate" value="${endDate}">
									<label class="control-label" for="startDate" style="width: 100px">上市日期：</label>
									<div class="controls" style="margin-left: 100px;">
										<div class="form-inline" >
						 					<span id="startDate-container" class="input-append date" >
												<input type="text" value="${endDate}"  readonly="readonly" class="input-mini white" placeholder="开始日期" id="startDate" style="width:80px;"><span class="add-on"><i class="icon-th"></i></span>
											</span>
											<span class="2">至</span>
								 			<span id="endDate-container" class="input-append date">
												<input type="text" value="${endDate}" readonly="readonly" class="input-mini white"  placeholder="结束日期" id="endDate" style="width:80px;"><span class="add-on"><i class="icon-th"></i></span>
											</span>
									   </div>
								    </div>
								</div>
							</label>
                		
                			<label class="control-label" style="width: auto;margin-left: 15px;">
                			   <input type="hidden" id="getModelIndexId" />         				
				    		
					    		<div id="model" style="display:block;">
					  				<div class="control-group">
					    				<div class="controls">
					    					<ul style="margin:0px;" class="subModelULContainer"></ul>
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
				   		
	                	<form action="${ctx}/news/newsCarsInfoExport.do" id="exportFormId" class="form-horizontal" style="margin-top:2px;display: inline;">
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
										<th colName="qbrandName">品牌</th>
										<th colName="qbrandNameEn">Brand</th>
										<th colName="qmanfName">生产商</th>
										<th colName="qmanfNameEn">OEM</th>
										<th colName="qsegment">级别</th>
										<th colName="qmodelName">车型</th>
										<th colName="qmodelNameEn">Model</th>
										<th colName="qtrim">型号标识</th>
										<th colName="qtrimEn">Trim</th>
										<th colName="qversionName">型号全称</th>
										<th colName="qversionNameEn">Version Name</th>
										<th colName="qMSRP">厂商指导价(万元)</th>
										<th colName="qpreVersionName">上代型号全称</th>
										<th colName="qpreMSRP">上代指导价(万元)</th>										
										<th colName="qbodyType">车身形式</th>
										<th colName="qbodyTypeEn">Body Type</th>
										<th colName="qdriveType">驱动形式</th>
										<th colName="qdriveTypeEn">Drive Type</th>
										<th colName="qcarIn">国产/进口</th>										
										<th colName="qcarInEn">Domestic/Imported</th>
										<th colName="qchangeDescription"变化描述</th>
										<th colName="qchangeDescriptionEn">Change description</th>
										<th colName="qLWH">长/宽/高(mm)</th>
										<th colName="qwheelbase">轴距(mm)</th>										
										<th colName="qPL">排量</th>
										<th colName="qtransmission">变速箱</th>
										<th colName="qmaximumPower">最大功率/转速</th>						
										<th colName="qmaximumTorque">最大扭矩/转速</th>										
										<th colName="qKWL">升功率</th>
										<th colName="qsellingPoints">产品卖点</th>
									</tr>
									
									<tr>
										<th align="left" style="width: 30px;">编辑</th>
										<th align="left">上市时间</th>
										<th align="left">型号编码</th>
										<th align="left">品牌</th>
										<th align="left">Brand</th>
										<th align="left">生产商</th>
										<th align="left">OEM</th>
										<th align="left">级别</th>
										<th align="left">车型</th>
										<th align="left">Model</th>
										<th align="left">型号标识</th>
										<th align="left">Trim</th>
										<th align="left">型号全称</th>
										<th align="left">Version Name</th>
										<th align="left">厂商指导价(万元)</th>
										<th align="left">上代型号全称</th>
										<th align="left">上代指导价(万元)</th>
										<th align="left">车身形式</th>
										<th align="left">Body Type</th>
										<th align="left">驱动形式</th>
										<th align="left">Drive Type</th>										
										<th align="left">国产/进口</th>
										<th align="left">Domestic/Imported</th>										
										<th align="left">变化描述</th>
										<th align="left">Change description</th>
										<th align="left">长/宽/高(mm)</th>
										<th align="left">轴距(mm)</th>
										<th align="left">排量</th>										
										<th align="left">变速箱</th>
										<th align="left">最大功率/转速</th>
										<th align="left">最大扭矩/转速</th>
										<th align="left">升功率</th>
										<th align="left">产品卖点</th>
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
<%@ include file="/pages/common2/subModelModalTemplate.jsp"%>
<%@ include file="/pages/common2/bodyTypeModalTemplate.jsp"%>
<%@ include file="/pages/common2/autoVersinoModalTemplate.jsp"%>
<script src="${ctx}/js/app/news/carNewsMain.js"></script>
<script type="text/javascript" src="${ctx}/js/app/common.js"></script>
	</body>
</html>

