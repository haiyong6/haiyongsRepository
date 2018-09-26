<%@ page language="java" errorPage="/error.jsp" pageEncoding="utf-8"%>
<!-- Bootrstrap modal form -->
<div id="subModelModal2" style="width:700px; height: 560px; margin-left: -350px; margin-top: -50px;" class="modal hide subModelModalContainer2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
	<div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true" ng-click="resetModelFunc()">×</button>
        <h3 id="myModalLabel2">车型选择</h3>
    </div>       
    <div class="modal-body" style="margin:0px;" id="subModelModalBody2"></div>           
	<div class="modal-footer" style="text-align:center">
      	<button class="btn btn-primary confirm" data-dismiss="modal" aria-hidden="true"  relContainer="subModelModalResultContainer2" relInputName="selectedSubModel2">确认</button>
	</div>
</div>
<script type="text/javascript" src="${ctx}/js/app/backstageConfig/commonGroupMain.js"></script>   