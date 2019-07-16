<%@ page language="java" errorPage="/error.jsp" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<!-- Bootrstrap modal form -->
<div id="versionModal2" class="modal hide versionModalContainer2" style="width:700px; height: 560px; margin-left: -350px; margin-top: -50px;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" ng-click="resetVersionFunc()">×</button>
        <h3 id="myModalLabel2">型号选择</h3>
    </div>        
     <div class="modal-body" style="margin:0px;padding-top:0px;line-height: 20px" id="versionModalBody2"></div>
    <div class="modal-footer" style="text-align:center">
		<button class="btn btn-primary confirm" data-dismiss="modal" aria-hidden="true"  relContainer="versionModalResultContainer2" relInputName="selectedVersion2">确认</button>
    </div>
</div>  