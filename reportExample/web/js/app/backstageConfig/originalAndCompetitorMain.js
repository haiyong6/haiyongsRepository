var dataTable = null;
var currSubModelLI = null;
var currSubModelLI2 = null;
var bjpList = null;
$(document).ready(function(){
	$('input[name="reportType"]').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%'
    });	
	
	$('input[name="dateType"]').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%'
    });	
	
	//获取页面参数
	dataTable = $("#dataTable").dataTable({
		"dom": "<rt><iflp>",
		"scrollY": 400,
		"scrollX": true ,
		"bJQueryUI": true,
	    "sort": true,
	    "filter": false,
	    "sPaginationType": "full_numbers",
	    "bPaginate": true,  //是否分页。
	    "bLengthChange ": true,
	    "bServerSide": true,
	    "bProcessing": true, //当datatable获取数据时候是否显示正在处理提示信息。 
	    "sAjaxSource": ctx+"/backstageConfig/getOriginalAndCompetitorData.do?rand="+Math.random(),
		"sServerMethod":"post",
		"fnServerParams": function (aoData) {
											var	qBpManfName = $.trim($("#qBpManfName").val());
											var qBpSubModelName = $.trim($("#qBpSubModelName").val());
											var	qJpManfName = $.trim($("#qJpManfName").val());
											var qJpSubModelName = $.trim($("#qJpSubModelName").val());
											aoData.push( {"name":"qBpManfName", "value": qBpManfName});
											aoData.push( {"name":"qBpSubModelName", "value": qBpSubModelName});
											aoData.push( {"name":"qJpManfName", "value": qJpManfName});
											aoData.push( {"name":"qJpSubModelName", "value": qJpSubModelName});
											},
        "columns": [
    				{ "data": ""},
    				{ "data": "bpManfName"},
    				{ "data": "bpSubModelName"},
    				{ "data": "bpSubModelSort"},
    				{ "data": "jpManfName"},
    				{ "data": "jpSubModelName"},
    				{ "data": "jpSubModelSort"}
        ],
	    "columnDefs": [
	    	{
	            "render": function ( data, type, row ) {
					var str = '<a style="cursor:pointer;" onclick="toEdit(' + row.bpSubModelId +')" title="编辑"> &nbsp;<i class="cus-pencil"></i></a>';
					    str+= '<a style="cursor:pointer;" onclick="toDelete(\'' + row.bpSubModelSort + '\',\'' + row.bpSubModelId + '\',\'' + row.bpSubModelName + '\')"  title="删除"> &nbsp;<i class="cus-basket_delete"></i></a>';
	                return str;
	            },
	            "targets": 0,
	            "bSortable":false
       		}
		],
        "oLanguage": { //多语言配置 
	        "sProcessing": "正在加载中......",
	        "sLengthMenu": "每页显示 _MENU_ 条记录",
	        "sZeroRecords": "对不起，查询不到相关数据！",
	        "sEmptyTable": "表中无数据存在！",
	        "sInfoEmpty": "无记录！", 
	        "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
	        "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
	        "sSearch": "搜索",
	        "oPaginate": {
	            "sFirst": "首页",
	            "sPrevious": "上一页",
	            "sNext": "下一页",
	            "sLast": "末页"
	        }
	    },
	    "aLengthMenu":[[50,100,150],[50,100,150]]  //分页数选择     
    });
	
	$('#searchTr th').each( function () {
		var title = $('#searchTr th').eq( $(this).index() ).text();		 
		var colName = $(this).attr("colName");
		if(colName){
			$(this).html('<input name="' + colName + '" style="width:100%" type="text" id="' + colName + '" placeholder="' + title + '" colName=' + colName + ' />');
		}
	} );
	
	$( 'input',$('#searchTr th')).on('keyup', function (event) {
		if(13 == event.keyCode){
			var val = $(this).val();
			search();
		}
	});
}); 

/**
 *删除本竞品组
 */
function toDelete(bpSubModelSort, bpSubModelId, bpSubModelName) {
	if(confirm("是否要删除" + bpSubModelName + " 整个分组?")) {
		$.post(ctx + "/backstageConfig/deleteOriginalAndCompetitor.do", {bpSubModelSort:bpSubModelSort, bpSubModelId:bpSubModelId}, function(data){
	        if(data.errorMsg) {
	        	alert(data.errorMsg + " ---");
	        } else {
	        	alert(data.result);
	        	$("#dataTable").dataTable().fnDraw();
	        }
		});
	}
}

/**
 * 搜索
 */
function search(){
	//获取参数
	var	qBpManfName = $.trim($("#qBpManfName").val());
	var	qBpSubModelName = $.trim($("#qBpSubModelName").val());
	var	qJpManfName = $.trim($("#qJpManfName").val());
	var	qJpSubModelName = $.trim($("#qJpSubModelName").val());
	dataTable.fnClearTable(0); //清空数据
	var param = dataTable.fnSettings().aoServerParams[0];
	param.fn = function ( aoData ) {
		aoData.push( {"name":"qBpManfName", "value": qBpManfName});
		aoData.push( {"name":"qBpSubModelName", "value": qBpSubModelName});
		aoData.push( {"name":"qJpManfName", "value": qJpManfName});
		aoData.push( {"name":"qJpSubModelName", "value": qJpSubModelName});
	};
	dataTable.fnDraw(); //重新加载数据
}

/**本品车型弹出框-开始*/
$("#subModelModal").on("show", function (e) {
	currSubModelLI = $("#originalAndCompetitorTable");
	//保存获取车形弹出框当前车型下标
	//$("#getModelIndexId").val($("#model .subModelULContainer .subModelLIContainer").index(currSubModelLI));
	if(e.relatedTarget)  return; //修复bootstrap的modal引入tabpane时，触发事件问题。
	//加载子车型数据
	showLoading("subModelModalBody");
	$("#subModelModalBody").load(ctx+"/backstageConfig/originalAndCompetitor/getSubModelModal.do",getParams(0),function(){
		//弹出框设置默认选中项结果集		
		var strHTML = '<ul class="inline" >';
		$(currSubModelLI).find("#subModelModalResultContainer input").each(function(){
			var subModelId = $(this).val();
			var subModelName = $(this).attr("subModelName");
			var pooAttributeId = $(this).attr("pooAttributeId");
			var letter = $(this).attr("letter");
			    strHTML += '<li style="padding-top:4px;margin-bottom:2px;">';
		  		strHTML += '<div class="removeBtnByResult label label-info" subModelId="'+subModelId+'"  pooAttributeId="'+pooAttributeId+'" letter="'+letter+'" subModelName="'+subModelName+'" style="cursor:pointer" title="删除：'+subModelName+'">';
			  	strHTML += '<i class="icon-remove icon-white"></i>'+subModelName;
		  		strHTML += '</div>';
		 		strHTML += '</li>';
		 		
	 		$(".subModelModalContainer .subModelIdInput").each(function() {
				if($(this).val() == subModelId){
					$(this).attr("checked",'true');//行全选
				}
			});
		});
		strHTML += '</ul>';
		$("#selectorResultContainerBySubModel").html(strHTML);
	});
});

/**车型确认按钮动作**/
$("#subModelModal").find('.confirm').unbind('click').bind('click',function(e){
	var containerId = $(window.parent.document).find(".subModelModalContainer").attr("id");
	var relInputName = $(this).attr("relInputName");
	var allSubModelArr = [];
	$("#subModelModal").find('.resultShowContent').find('.removeBtnByResult').each(function(){
		var obj = {};
		obj.subModelId =  $(this).attr("subModelId");
		obj.subModelName =  $(this).attr("subModelName");
		obj.letter =  $(this).attr("letter");
		obj.pooAttributeId = $(this).attr("pooAttributeId");
		allSubModelArr[allSubModelArr.length] = obj;
	});
	var strHTML = "";
	strHTML += '<ul class="selectorResultContainer">';
	for(var i=0;i<allSubModelArr.length;i++){
		strHTML += '<li>';
	  	strHTML += '<div class="removeBtn" relContainer="'+containerId+'" value="'+allSubModelArr[i].subModelId+'" style="cursor:pointer" title="删除：'+allSubModelArr[i].subModelName+'">';
		strHTML += '<input type="hidden" letter="'+allSubModelArr[i].letter+'" pooAttributeId="'+allSubModelArr[i].pooAttributeId+'" subModelName="'+allSubModelArr[i].subModelName+'" value="'+allSubModelArr[i].subModelId+'" name="'+relInputName+'">';
		strHTML += allSubModelArr[i].subModelName + '<i class="icon-remove" style="visibility: hidden;"></i>';
	  	strHTML += '</div>';
  		strHTML += '</li>';
	 }
	strHTML += '</ul>';
	$("#subModelModalResultContainer").html(strHTML);
});


/**竞品车型弹出框-开始*/
$("#subModelModal2").on("show", function (e) {
	currSubModelLI2 = $("#originalAndCompetitorTable");
	if(e.relatedTarget)  return; //修复bootstrap的modal引入tabpane时，触发事件问题。
	//加载子车型数据
	showLoading("subModelModalBody2");
	$("#subModelModalBody2").load(ctx+"/backstageConfig/originalAndCompetitor/getSubModelModal.do",getParams(1),function(){
		//弹出框设置默认选中项结果集	
		var strHTML = '<ul class="inline" >';
		$(currSubModelLI2).find("#subModelModalResultContainer2 input").each(function(){
			var subModelId = $(this).val();
			var subModelName = $(this).attr("subModelName");
			var pooAttributeId = $(this).attr("pooAttributeId");
			var letter = $(this).attr("letter");
			    strHTML += '<li style="padding-top:4px;margin-bottom:2px;">';
		  		strHTML += '<div class="removeBtnByResult2 label label-info" subModelId="'+subModelId+'"  pooAttributeId="'+pooAttributeId+'" letter="'+letter+'" subModelName="'+subModelName+'" style="cursor:pointer" title="删除：'+subModelName+'">';
			  	strHTML += '<i class="icon-remove icon-white"></i>'+subModelName;
		  		strHTML += '</div>';
		 		strHTML += '</li>';
		 		
	 		$(".subModelModalContainer2 .subModelIdInput").each(function() {
				if($(this).val() == subModelId){
					$(this).attr("checked",'true');//行全选
				}
			});
		});
		strHTML += '</ul>';
		$("#selectorResultContainerBySubModel2").html(strHTML);
	});
});

/**竞品车型确认按钮动作**/
$("#subModelModal2").find('.confirm').unbind('click').bind('click',function(e){
	var containerId = $(window.parent.document).find(".subModelModalContainer2").attr("id");
	var relInputName = $(this).attr("relInputName");
	var allSubModelArr = [];
	$("#subModelModal2").find('.resultShowContent').find('.removeBtnByResult2').each(function(){
		var obj = {};
		obj.subModelId =  $(this).attr("subModelId");
		obj.subModelName =  $(this).attr("subModelName");
		obj.letter =  $(this).attr("letter");
		obj.pooAttributeId = $(this).attr("pooAttributeId");
		allSubModelArr[allSubModelArr.length] = obj;
	});
	var strHTML = "";
	strHTML += '<ul class="selectorResultContainer">';
	for(var i=0;i<allSubModelArr.length;i++){
		strHTML += '<li>';
	  	strHTML += '<div class="removeBtn" relContainer="'+containerId+'" value="'+allSubModelArr[i].subModelId+'" style="cursor:pointer" title="删除：'+allSubModelArr[i].subModelName+'">';
		strHTML += '<input type="hidden" letter="'+allSubModelArr[i].letter+'" pooAttributeId="'+allSubModelArr[i].pooAttributeId+'" subModelName="'+allSubModelArr[i].subModelName+'" value="'+allSubModelArr[i].subModelId+'" name="'+relInputName+'">';
		strHTML += allSubModelArr[i].subModelName + '<i class="icon-remove" style="visibility: hidden;"></i>';
	  	strHTML += '</div>';
  		strHTML += '</li>';
	 }
	strHTML += '</ul>';
	$("#subModelModalResultContainer2").html(strHTML);
});

function showLoading(containerId) {
	$("#"+containerId).html('<div class="text-center"></div>');
}

/**提交数据**/
function addOriginalAndCompetitor() {
	if("" == $("#bpSubModelSort").val()) {
		alert("本品车型排序不能为空!");
		return false;
	}  else {
		$("#originalAndCompetitorForm").submit();
	}
}

window.getParams = function(type){
	var paramObj = {};
	var subModelIds = $("#subModelModalResultContainer input").map(function(){return $(this).val();}).get().join(",");
	var subModelIds2 = $("#subModelModalResultContainer2 input").map(function(){return $(this).val();}).get().join(",");
	paramObj.subModelIds = subModelIds;
	paramObj.subModelIds2 = subModelIds2;
	paramObj.browser = navigator.appVersion;
	paramObj.inputType = "1";
	paramObj.isJp = type;
	return paramObj;
}

/**
 * 弹出框
 * @param type
 */
window.showSubModel = function(type,isJp)  {
	var inputType = "1"; //弹出框复选
	if('3' == type) {
		getModelPage("tabs-brand",type,inputType,isJp);
	}
	else {
		getModelPage("tabs-manf",type,inputType,isJp);
	}
};

/**
 * 弹出框
 * @param type
 */
window.showSubModel2 = function(type,isJp)  {
	var inputType = "1"; //弹出框复选
	if('3' == type) {
		getModelPage("tabs-brand2",type,inputType,isJp);
	}
	else {
		getModelPage("tabs-manf2",type,inputType,isJp);
	}
};

/**
 * 展示页面
 * @param id 展示容器ID
 * @param type 子车型弹出框展示类型2：细分市场,3:品牌,4:厂商，为空则是本品与竟品
 * @param inputType 控件类型1：复选，2：单选;默认为1
 * @param isJp 是否竞品
 * 
 */
function getModelPage(id,type,inputType,isJp) {
	//如果内容不为空则触发请求
	if(!$.trim($('#' + id).html())){
		var params = {inputType:inputType,subModelShowType:type,isJp:isJp};
		//触发请求
		showLoading(id);
		if(id.indexOf("2") != -1) {
			$("#" + id).load(ctx+"/backstageConfig/originalAndCompetitor/getSubModelModal.do",params,function(){
				$("#selectorResultContainerBySubModel2 .removeBtnByResult2").each(function(){
					var subModelId = $(this).attr("subModelId");
					$(".subModelModalContainer2 .subModelIdInput").each(function(){
						if($(this).val() == subModelId) {
							$(this).prop("checked", true);//行全选
						}
					});
				});
			});
		} else {
			$("#" + id).load(ctx+"/backstageConfig/originalAndCompetitor/getSubModelModal.do",params,function(){
				$("#selectorResultContainerBySubModel .removeBtnByResult").each(function(){
					var subModelId = $(this).attr("subModelId");
					$(".subModelModalContainer .subModelIdInput").each(function(){
						if($(this).val() == subModelId) {
							$(this).prop("checked", true);//行全选
						}
					});
				});
			});
		}
	}
};

/**点击后显示车型在空白DIV上**/
$(document).on("click", ".selectorTable .subModelIdInput", function(){
	// 本品
	$("#selectorResultContainerBySubModel").html();
	var bpAllSubModelArr = [];
	$(".subModelModalContainer").find('.subModelIdInput:checked').each(function(){
		var obj = {};
		obj.subModelId =  $(this).val();
		obj.subModelName =  $.trim($(this).parent().text());
		obj.letter =  $(this).attr("letter");
		obj.pooAttributeId = $(this).attr("pooAttributeId");
		bpAllSubModelArr[bpAllSubModelArr.length] = obj;
	});
	bpAllSubModelArr = uniqueSubModel(bpAllSubModelArr); 
	var strHTML = '<ul class="inline" >';
	for(var i=0;i<bpAllSubModelArr.length;i++){
		strHTML += '<li style="padding-top:4px;margin-bottom:2px;">';
	  		strHTML += '<div class="removeBtnByResult label label-info" subModelId="'+bpAllSubModelArr[i].subModelId+'"  pooAttributeId="'+bpAllSubModelArr[i].pooAttributeId+'" letter="'+bpAllSubModelArr[i].letter+'" subModelName="'+bpAllSubModelArr[i].subModelName+'" style="cursor:pointer" title="删除：'+bpAllSubModelArr[i].subModelName+'">';
		  		strHTML += '<i class="icon-remove icon-white"></i>'+bpAllSubModelArr[i].subModelName;
	  		strHTML += '</div>';
	 		strHTML += '</li>';
	 }
	 strHTML += '</ul>';
	$("#selectorResultContainerBySubModel").html(strHTML);
	
	// 竞品
	$("#selectorResultContainerBySubModel2").html();
	var jpAllSubModelArr = [];
	$(".subModelModalContainer2").find('.subModelIdInput:checked').each(function(){
		var obj = {};
		obj.subModelId =  $(this).val();
		obj.subModelName =  $.trim($(this).parent().text());
		obj.letter =  $(this).attr("letter");
		obj.pooAttributeId = $(this).attr("pooAttributeId");
		jpAllSubModelArr[jpAllSubModelArr.length] = obj;
	});
	jpAllSubModelArr = uniqueSubModel(jpAllSubModelArr); 
	var strHTML = '<ul class="inline" >';
	for(var i=0;i<jpAllSubModelArr.length;i++){
		strHTML += '<li style="padding-top:4px;margin-bottom:2px;">';
	  	strHTML += '<div class="removeBtnByResult2 label label-info" subModelId="'+jpAllSubModelArr[i].subModelId+'"  pooAttributeId="'+jpAllSubModelArr[i].pooAttributeId+'" letter="'+jpAllSubModelArr[i].letter+'" subModelName="'+jpAllSubModelArr[i].subModelName+'" style="cursor:pointer" title="删除：'+jpAllSubModelArr[i].subModelName+'">';
		strHTML += '<i class="icon-remove icon-white"></i>'+jpAllSubModelArr[i].subModelName;
	  	strHTML += '</div>';
	 	strHTML += '</li>';
	 }
	 strHTML += '</ul>';
	$("#selectorResultContainerBySubModel2").html(strHTML);
});

/**页面上点击删除*/
$(document).on("click", ".selectorResultContainer .removeBtn", function(){
	$(this).remove();
});

/**车型选择页面上点击删除 本品**/
$(document).on("click", ".subModelModalContainer .resultShowContent .removeBtnByResult", function(){
    var currVal = $(this).attr("subModelId");
	$(this).parents(".subModelModalContainer").find(".subModelIdInput").filter(function(){
		return $(this).val() == currVal;
	}).each(function(){
		$(this).removeAttr("checked");//取消选中
	});
	$(this).parent().remove();
});

/**车型选择页面上点击删除 竞品**/
$(document).on("click", ".subModelModalContainer2 .resultShowContent .removeBtnByResult2", function(){
	var currVal = $(this).attr("subModelId");
	$(this).parents(".subModelModalContainer2").find(".subModelIdInput").filter(function(){
		return $(this).val() == currVal;
	}).each(function(){
		$(this).removeAttr("checked");//取消选中
	});
	$(this).parent().remove();
});

function uniqueSubModel(array){  
    for(var i=0;i<array.length;i++) {  
        for(var j=i+1;j<array.length;j++) {  
            //注意 ===  
            if(array[i].subModelId===array[j].subModelId) {  
                array.splice(j,1);  
                j--;  
            }  
        }  
    }  
  	return array;  
}  

/**获取本竞品组数据**/
function getData(editObj) {
    var iframe = window.frames["iframe1"].document;
	var bpSubModelSort = $(iframe).find("#bpSubModelSort");
	var bpDiv = $(iframe).find("#subModelModalResultContainer");
	var jpDiv = $(iframe).find("#subModelModalResultContainer2");
	var oldSort = $(iframe).find("#oldSort");
    $.ajax({
 	    type: "post",
  		url: ctx + "/backstageConfig/getBJPGroupByBpSubModelId.do",
  		data: {bpSubModelId: editObj},
     	success: function(data) {
     		var result = data.bjpList;
     		bpSubModelSort.val(result[0].bpSubModelSort);
     		var bpHTML = "";
     		bpHTML += '<ul class="selectorResultContainer">';
 			bpHTML += '<li>';
 		  	bpHTML += '<div class="removeBtn" relContainer="originalAndCompetitorResultPanel" value="'+result[0].bpSubModelId+'" style="cursor:pointer" title="删除：'+result[0].bpSubModelName+'">';
 			bpHTML += '<input type="hidden" letter="'+result[0].letter+'" pooAttributeId="'+result[0].pooAttributeId+'" subModelName="'+result[0].bpSubModelName+'" value="'+result[0].bpSubModelId+'" name="selectedSubModel">';
 			bpHTML += result[0].bpSubModelName + '<i class="icon-remove" style="visibility: hidden;"></i>';
 		  	bpHTML += '</div>';
 	  		bpHTML += '</li>';
 	  		bpHTML += '</ul>';
 	  		bpDiv.html(bpHTML);
 	  		
 	  		var jpHTML = "";
     		jpHTML += '<ul class="selectorResultContainer">';
     		$.each(result, function(i, item) {
     	  		jpHTML += '<li>';
     	  		jpHTML += '<div class="removeBtn" relContainer="originalAndCompetitorResultPanel" value="'+result[i].jpSubModelId+'" style="cursor:pointer" title="删除：'+result[i].jpSubModelName+'">';
     	  		jpHTML += '<input type="hidden" letter="'+result[i].letter+'" pooAttributeId="'+result[i].pooAttributeId+'" subModelName="'+result[i].jpSubModelName+'" value="'+result[i].jpSubModelId+'" name="selectedSubModel2">';
     	  		jpHTML += result[i].jpSubModelName + '<i class="icon-remove" style="visibility: hidden;"></i>';
     	  		jpHTML += '</div>';
     	  		jpHTML += '</li>';
     		});
     		jpHTML += '</ul>';
     		jpDiv.html(jpHTML);
     		oldSort.val(result[0].bpSubModelSort);
        }
    });
}

/**
 * 导出
 */
function exportExcel() {
	$("#exportFormId").submit();
}

/** 车型控件值鼠标经过事件*/
$(document).on('mouseover', ".removeBtn", function() {
	$(this).find(".icon-remove").css({visibility:'visible'});
});

/**车型控件值鼠标离开事件*/
$(document).on('mouseout', ".removeBtn", function(){
	$(this).find(".icon-remove").css({visibility:'hidden'});
});

$("#resetBtn").on("click", function (e) {
	//车型容器
	$("#subModelModalResultContainer").html("");
	$("#subModelModalResultContainer2").html("");
	$("#bpSubModelSort").val("");
});

/**设置排序窗口打开时重置数据**/
$("#originalAndCompetitorSortPanel").on("show", function() {
    $.ajax({
 	    type: "post",
  		url: ctx + "/backstageConfig/getOriginalAndCompetitor.do",
     	success: function(data) {
     	    var result = data.bjpList;
     	    var bjpName = $("#bjpName");
     	    bjpList = data.bjpList;
         	$("#bjpName").empty();
         	var title = "<option value='0'>请选择本竞品组</option>";
         	bjpName.append(title);
         	var bpSubModelName = "";
         	$.each(result, function(i, item) {
                 if(result[i].bpSubModelName != "" && bpSubModelName != result[i].bpSubModelName) {
         		    var op = "<option value='" + result[i].bpSubModelId + "'>" + result[i].bpSubModelName + "</option>"
         		   bjpName.append(op);
             		bpSubModelName = result[i].bpSubModelName;
                 }
             });
         }
     });
    $("#bjpName").val("0");
    $("#sortForm").empty();
});

/**编辑本竞品组**/
function toEdit(bpSubModelId) {
	$("#editObj").val(bpSubModelId);
	$("#originalAndCompetitorResultPanel").modal("show");
}

/**新增本竞品组**/
function toAdd() {
	// 获取分组排序
	$("#editObj").val("");
	$("#originalAndCompetitorResultPanel").modal("show");
}

/**新增窗口打开**/
$("#originalAndCompetitorResultPanel").on("show", function() {
	var editObj = $("#editObj").val();
	//找DIV下找iframe下的对象
	var iframe = window.frames["iframe1"].document
	$(iframe).find("#resetBtn").click();
	$(iframe).find("#saveType").val("add");
	if("" != editObj) {
		$("#headTitle").html("本竞品管理-修改");
		$(iframe).find("#saveType").val("update");
		getData(editObj);
	} else {
		$("#headTitle").html("本竞品管理-新增");
	}
});

/**新增窗口关闭时刷新**/
$("#originalAndCompetitorResultPanel").on("hide", function() {
	var iframe = window.frames["iframe1"].document;
	var bpSubModelSort = $(iframe).find("#bpSubModelSort").val();
	if(bpSubModelSort != "") {
		location.reload();
	}
});

/**导入窗口打开时重置数据**/
$("#originalAndCompetitorImportPanel").on("show", function() {
	//找DIV下找iframe下的对象
	var iframe = window.frames["iframe2"].document;
    $(iframe).find("#importFile").val("");
});

/**导入窗口关闭时刷新**/
$("#originalAndCompetitorImportPanel").on("hide", function() {
	var iframe = window.frames["iframe2"].document;
	var importFile = $(iframe).find("#importFile").val();
	if(importFile != "") {
		location.reload();
	}
});