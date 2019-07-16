<%@ page language="java" errorPage="/error.jsp"  pageEncoding="utf-8"%>
<div id="originalAndCompetitorResultPanel" style="width: 800px;height: 650px; margin-left: -400px;" aria-hidden="true" class="modal hide subModelModalContainer" tabindex="-1" role="dialog">
   <div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 id="headTitle">本竞品管理-新增</h4>
        <input type="hidden" id="editObj" />
   </div>
   <div class="modal-body" style="margin: 0px;max-height: 590px;">
      <iframe id="iframe" name="iframe1" style="width: 99%;height: 580px;" frameborder="0" src="${ctx }/pages/backstageConfig/originalAndCompetitorIframe.jsp">
       
      </iframe>
     </div>
</div>