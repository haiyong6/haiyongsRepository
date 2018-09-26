<%@ page language="java" errorPage="/error.jsp"  pageEncoding="utf-8"%>
<div id="commonGroupCityBasePeriodTPImportPanel" style="width: 500px;height:270px;" aria-hidden="true" class="modal hide" tabindex="-1" role="dialog" aria-hidden="true">
   <div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 id="headTitle">常用对象组-城市基期TP导入面板</h4>
   </div>
   <div class="modal-body" style="margin:0px">
      <iframe id="iframe" name="iframe2" style="width:99%;height:200px;" src="${ctx }/pages/basePeriodTPConfig/commonGroupCityBasePeriodTPImport.jsp">
       
      </iframe>
   </div>
</div>