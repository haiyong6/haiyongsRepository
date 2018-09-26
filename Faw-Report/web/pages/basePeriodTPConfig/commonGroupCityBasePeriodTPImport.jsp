<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" errorPage="/error.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="${ctx}/include/jquery/jquery.js"></script>
<script>
    var ctx = '${ctx}';
    /**下载模版文件**/
	function downloadCommonGroupTemplate() {
        $("#templateFormId").submit(); 
   	}
   	
   	/**导入文件**/
   	function importFile() {
   	}
   	
   	/**校验文件类型**/
   	function checkFileType() {
   	    var file = $("#importFile");
   	    if(file.val() == "") {
   	        alert("请选择要上传的文件");
   	    } else {
   	    	if(file.val().indexOf("xls") == -1 || file.val().indexOf("xlsx") == -1) {
   	        	alert("错误的文件类型，请上传Excel文件！");
   	        	file.val("");
   	    	} else {
   	    	    $("#importFormId").submit();
   	    	}
   	    }
   	}
</script>
</head>
<body>
<div id="commonGroupCityBasePeriodTPImport" style="width: 100%;height:180px;" aria-hidden="true" class="hide" tabindex="-1" role="dialog" aria-hidden="true">
   <div class="modal-body" style="margin:0px" id="subModelModalBody">
      <form action="${ctx}/basePeriodTPConfig/downloadCommonGroupCityBasePeriodTemplate.do" id="templateFormId" class="form-horizontal" style="margin-top:0px;display: inline;">
	      <label style="font-size:4px;">请选择模版文件类型：</label>
	      <input type="radio" name="fileType" value="xls" />xls
	      <input type="radio" name="fileType" value="xlsx" checked/> xlsx&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  	<button onclick="downloadCommonGroupTemplate()" class="btn btn-small btn-info" style="margin-top:2px;margin-bottom: 1px;" >
		       <i class="icon-download icon-white"></i>下载模版文件
		   	</button>
	  </form>
	      </br>
	  <form action="${ctx}/basePeriodTPConfig/importCommonGroupCityBasePeriodTPFile.do" id="importFormId" class="form-horizontal" style="margin-top:0px;display: inline;"
	       enctype="multipart/form-data" method="post">
	      <label style="font-size:4px;">选择导入的文件(少于10M)：</label>
	      <input type="file" id="importFile" name="importFile" style="width:250px;" 
	               accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
	      <input type="button" onclick="checkFileType()" value="导入文件" />
	  </form>
   </div>
</div>
</body>
</html>