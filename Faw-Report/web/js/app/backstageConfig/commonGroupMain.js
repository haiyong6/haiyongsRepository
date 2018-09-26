var dataTable = null;
/**定义本品车型全局变量*/
var currSubModelLI = null;
/**定义竞品车型全局标量*/
var currSubModelLI2 = null;
var groupList = null;
$(document).ready(function() {
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
	dataTable = $('#dataTable').dataTable({
		"dom": '<rt><iflp>',
		"scrollY": 400,
		"scrollX": true ,
		"bJQueryUI": true,
	    "sort": true,
	    "filter":false,
	    "sPaginationType": "full_numbers",
	    'bPaginate': true,  //是否分页。
	    "bLengthChange ":true,
	    "bServerSide": true,
	    "bProcessing": true, //当datatable获取数据时候是否显示正在处理提示信息。 
	    "sAjaxSource": ctx+"/backstageConfig/getCommonGroupInfo.do?rand="+Math.random(),
		"sServerMethod":"post",
		"fnServerParams": function (aoData) {
											var	qGroupName = $.trim($("#qGroupName").val());
											var qManfName = $.trim($("#qManfName").val());
											var qSubModelName = $.trim($("#qSubModelName").val());
											var	qVersionName = $.trim($("#qVersionName").val());
											var qMSRP = $.trim($("#qMSRP").val());
											aoData.push( {"name":"qGroupName", "value": qGroupName});
											aoData.push( {"name":"qManfName", "value": qManfName});
											aoData.push( {"name":"qSubModelName", "value": qSubModelName});
											aoData.push( {"name":"qVersionName", "value": qVersionName});
											aoData.push( {"name":"qMSRP", "value": qMSRP});
											},
        "columns": [
    				{ "data": ""},
    				{ "data": "groupName"},
    				{ "data": "groupSort"},
    				{ "data": "manfName"},
    				{ "data": "subModelName"},
    				{ "data": "versionName"},
    				{ "data": "MSRP"},
    				{ "data": "versionSort"}
        ],
	    "columnDefs": [
	    	{
	            "render": function ( data, type, row ) {
					var str = '<a style="cursor:pointer;" onclick="toEdit(\'' + row.groupId + '\',\'' + row.groupName + '\')" title="编辑"> &nbsp;<i class="cus-pencil"></i></a>';
					    str += '<a style="cursor:pointer;" onclick="toDelete(\'' + row.groupName + '\',' + row.groupSort +')"  title="删除"> &nbsp;<i class="cus-basket_delete"></i></a>';
					    str += '<input type="hidden" value="" />';
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
 * 搜索
 */
function search() {
	//获取参数
	var	qGroupName = $.trim($("#qGroupName").val());
	var	qManfName = $.trim($("#qManfName").val());
	var	qSubModelName = $.trim($("#qSubModelName").val());
	var	qVersionName = $.trim($("#qVersionName").val());
	var	qMSRP = $.trim($("#qMSRP").val());
	dataTable.fnClearTable(0); //清空数据
	var param = dataTable.fnSettings().aoServerParams[0];
	param.fn = function ( aoData ) {
		aoData.push( {"name":"qGroupName", "value": qGroupName});
		aoData.push( {"name":"qManfName", "value": qManfName});
		aoData.push( {"name":"qSubModelName", "value": qSubModelName});
		aoData.push( {"name":"qVersionName", "value": qVersionName});
		aoData.push( {"name":"qMSRP", "value": qMSRP});
	};
	dataTable.fnDraw(); //重新加载数据
}

/**本品车型弹出框-开始*/
$("#subModelModal").on("show", function (e) {
	currSubModelLI = $("#commonGroupTable");
	//保存获取车形弹出框当前车型下标
	//$("#getModelIndexId").val($("#model .subModelULContainer .subModelLIContainer").index(currSubModelLI));
	if(e.relatedTarget)  return; //修复bootstrap的modal引入tabpane时，触发事件问题。
	//加载子车型数据
	showLoading("subModelModalBody");
	$("#subModelModalBody").load(ctx+"/backstageConfig/commonGroup/getSubModelModal.do",getParams(0),function(){
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
	 				$(this).prop("checked",true);//行全选
	 			}
	 		});
		});
		strHTML += '</ul>';
		$("#selectorResultContainerBySubModel").html(strHTML);
	});
});

/**车型确认按钮动作**/
$("#subModelModal").find(".confirm").unbind("click").bind("click",function(e){
	var containerId = $("#subModelModal").attr("id");
	var relInputName = $(this).attr("relInputName");
	var allSubModelArr = [];
	$("#subModelModal").find(".resultShowContent").find(".removeBtnByResult").each(function(){
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

/**本品型号弹出框**/
$("#versionModal").on("show", function (e) {	
	//修复bootstrap的modal引入tabpane时，触发事件问题。 或者没有选择车型
	if(e.relatedTarget || 0 == $("#subModelModalResultContainer input[name='selectedSubModel']").length)  return; 
	//加载子车型下型号数据
	showLoading("versionModalBody");
	$("#versionModalBody").load(ctx+"/backstageConfig/commonGroup/getVersionModal.do",getParams(0), function(){
		//设置默认选中项
		$("#versionModalResultContainer input").each(function(){
			var selectedId = $(this).val();
			$(".versionModalContainer .versionIdInput").each(function(){
				if($(this).val() == selectedId){
					$(this).prop("checked", true);//行全选
				}
			});
		});
		
		//设置全选效果---开始
		$(".versionModalContainer").find('.selectVersionAll').each(function(){
			var selectedCount = 0;
			var totalCount = 0;
			var subModelId = $(this).val();
			$(".versionModalContainer .versionIdInput").each(function(){
				if(	subModelId == $(this).attr("subModelId")){
					totalCount++;
					if($(this).attr("checked")){
						selectedCount++;
					}
				}
			});
			if(selectedCount == totalCount){
				$(this).attr("checked", "true");
			}else{
				$(this).removeAttr("checked");//取消选中
			}
		});
		//设置全选效果---结束
		
	});
});

/**本品型号选择确定按扭---开始*/
$(".versionModalContainer").find(".confirm").on("click",function(event){
	var containerId = $(this).parents(".versionModalContainer").attr("id");
	var relContainer = $(this).attr("relContainer");
	var relInputName = $(this).attr("relInputName");
	$("#"+relContainer).html("");
	var strHTML = "";
	strHTML += '<ul class="selectorResultContainer">';
	$(this).parents(".versionModalContainer").find(".versionIdInput:checked").each(function(){
		strHTML += "<li>";
	  		strHTML += '<div class="removeBtn" relContainer="'+containerId+'" value="'+$(this).val()+'" style="padding-top: 6px;cursor:pointer" title="删除：'+$.trim($(this).parent().text())+'">';
	  		strHTML += '<input type="hidden" value="'+$(this).val()+'" name="'+relInputName+'" />';
	  		strHTML += $.trim($(this).parent().text()) + '<i class="icon-remove" style="visibility: hidden;"></i>';
  			strHTML += '</div>';
  		strHTML += '</li>';
	});
	strHTML += '</ul>';
	$("#"+relContainer).html(strHTML);
});

/**竞品车型弹出框-开始*/
$("#subModelModal2").on("show", function (e) {
	currSubModelLI2 = $("#commonGroupTable");
	//保存获取车形弹出框当前车型下标
//	$("#getModelIndexId2").val($("#model .subModelULContainer .subModelLIContainer").index(currSubModelLI2));
	if(e.relatedTarget)  return; //修复bootstrap的modal引入tabpane时，触发事件问题。
	//加载子车型数据
	showLoading("subModelModalBody2");
	$("#subModelModalBody2").load(ctx+"/backstageConfig/commonGroup/getSubModelModal.do",getParams(1),function(){
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
$("#subModelModal2").find(".confirm").unbind("click").bind("click",function(e){
	var containerId = $("#subModelModal2").attr("id");
	var relInputName = $(this).attr("relInputName");
	var allSubModelArr = [];
	$("#subModelModal2").find(".resultShowContent").find(".removeBtnByResult2").each(function(){
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

/**竞品型号弹出框**/
$("#versionModal2").on("show", function (e) {	
	//修复bootstrap的modal引入tabpane时，触发事件问题。 或者没有选择车型
	if(e.relatedTarget || 0 == $("#subModelModalResultContainer2 input[name='selectedSubModel2']").length)  return; 
	//加载子车型下型号数据
	showLoading("versionModalBody2");
	$("#versionModalBody2").load(ctx+"/backstageConfig/commonGroup/getVersionModal.do",getParams(1), function(){
		//设置默认选中项
		$("#versionModalResultContainer2 input").each(function(){
			var selectedId = $(this).val();
			$(".versionModalContainer2 .versionIdInput").each(function(){
				if($(this).val() == selectedId){
					$(this).attr("checked","true");//行全选
				}
			});
		});
		
		//设置全选效果---开始
		$(".versionModalContainer2").find('.selectVersionAll').each(function(){
			var selectedCount = 0;
			var totalCount = 0;
			var subModelId = $(this).val();
			$(".versionModalContainer2 .versionIdInput").each(function(){
				if(	subModelId == $(this).attr("subModelId")){
					totalCount++;
					if($(this).attr("checked")){
						selectedCount++;
					}
				}
			});
			if(selectedCount == totalCount){
				$(this).attr("checked", "true");
			}else{
				$(this).removeAttr("checked");//取消选中
			}
		});
		//设置全选效果---结束
	});
});

/**竞品型号选择确定按扭---开始*/
$(".versionModalContainer2").find(".confirm").on("click",function(event){
	var containerId = $(this).parents(".versionModalContainer2").attr("id");
	var relContainer = $(this).attr("relContainer");
	var relInputName = $(this).attr("relInputName");
	$("#"+relContainer).html("");
	var strHTML = "";
	strHTML += '<ul class="selectorResultContainer">';
	$(this).parents(".versionModalContainer2").find(".versionIdInput:checked").each(function(){
		strHTML += "<li>";
	  		strHTML += '<div class="removeBtn" relContainer="'+containerId+'" value="'+$(this).val()+'" style="padding-top: 6px;cursor:pointer" title="删除：'+$.trim($(this).parent().text())+'">';
	  		strHTML += '<input type="hidden" value="'+$(this).val()+'" name="'+relInputName+'" />';
	  		strHTML += $.trim($(this).parent().text()) + '<i class="icon-remove" style="visibility: hidden;"></i>';
  			strHTML += '</div>';
  		strHTML += '</li>';
	});
	strHTML += '</ul>';
	$("#"+relContainer).html(strHTML);
});

function showLoading(containerId) {
	$("#"+containerId).html('<div class="text-center"></div>');
}

/**提交数据**/
function addCommonGroup() {
	var bpVersionIdLength = $("#versionModalResultContainer input").length;
	var jpVersionIdLength = $("#versionModalResultContainer2 input").length;
	if("" == $("#commonGroupName").val() || "" == $("#commonGroupSort").val()) {
		alert("分组名称或分组排序不能为空!");
		return false;
	} else if(bpVersionIdLength == 0 || jpVersionIdLength == 0) {
		alert("本品型号或竞品型号不能为空!");
		return false;
	}  else {
		$("#commonGroupForm").submit();
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
			$("#" + id).load(ctx+"/backstageConfig/commonGroup/getSubModelModal.do",params,function(){
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
			$("#" + id).load(ctx+"/backstageConfig/commonGroup/getSubModelModal.do",params,function(){
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
 
/**点击后显示车型在空白DIV上 本品**/
$(document).on("click", ".subModelModalContainer .subModelIdInput",function(){
	$("#selectorResultContainerBySubModel").html();
	var bpAllSubModelArr = [];
	var table = $(this).parent().parent().parent().parent().parent().parent().parent();
	$(".subModelModalContainer").find(".subModelIdInput:checked").each(function(){
		var obj = {};
		obj.subModelId = $(this).val();
		obj.subModelName = $.trim($(this).parent().text());
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
});

/**点击后显示车型在空白DIV上 竞品**/
$(document).on("click", ".subModelModalContainer2 .subModelIdInput",function(){
	// 竞品
	$("#selectorResultContainerBySubModel2").html();
	var jpAllSubModelArr = [];
	$(".subModelModalContainer2").find(".subModelIdInput:checked").each(function(){
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

/**
 *删除常用对象组
 */
function toDelete(groupName, groupSort) {
	if(confirm("是否要删除" + groupName + " 整个分组?")) {
		$.post(ctx + "/backstageConfig/deleteCommonGroup.do", {qGroupName: groupName, qGroupSort: groupSort}, function(data){
	        if(data.errorMsg) {
	        	alert(data.errorMsg);
	        } else {
	        	alert(data.result);
	        	$("#dataTable").dataTable().fnDraw();
	        }
		});
	}
}

function uniqueSubModel(array){  
    for(var i = 0; i < array.length; i++) {  
        for(var j = i + 1; j <array.length; j++) {  
            //注意 === 
            if(array[i].subModelId === array[j].subModelId) { 
            	array.splice(j,1);
                j--;  
            }  
        }
    }  
  	return array;  
}  

/**
 * 导出
 */
function exportExcel() {
	$("#exportFormId").submit();
}

/**获取常用对象组数据**/
function getData(editObj) {
    var iframe = window.frames["iframe1"].document;
    var groupName = $(iframe).find("#commonGroupName");
	var groupSort = $(iframe).find("#commonGroupSort");
	var bpSubModelDiv = $(iframe).find("#subModelModalResultContainer");
	var jpSubModelDiv = $(iframe).find("#subModelModalResultContainer2");
	var bpVersionDiv = $(iframe).find("#versionModalResultContainer");
	var jpVersionDiv = $(iframe).find("#versionModalResultContainer2");
	var oldGroupName = $(iframe).find("#oldGroupName");
    $.ajax({
 	    type: "post",
  		url: ctx + "/backstageConfig/getCommonGroupByGroupName.do",
  		data: {qGroupName: editObj},
     	success: function(data) {
     		var result = data.bpInfo;
     		var jpResult = data.groupList;
     		groupName.val(result.groupName);
     		groupSort.val(result.groupSort);
     		oldGroupName.val(result.groupName);
     		// 渲染本品车型和型号
     		var bpSubModelHTML = "";
     		var bpVersionHTML = "";
     		bpSubModelHTML += '<ul class="selectorResultContainer">';
 			bpSubModelHTML += '<li>';
 		  	bpSubModelHTML += '<div class="removeBtn" relContainer="subModelModal" value="' + result.subModelId + '" style="cursor:pointer" title="删除：' + result.subModelName + '">';
 			bpSubModelHTML += '<input type="hidden" letter="' + result.letter + '" pooAttributeId="' + result.pooAttributeId + '" subModelName="' + result.subModelName + '" value="' + result.subModelId + '" name="selectedSubModel">';
 			bpSubModelHTML += result.subModelName + '<i class="icon-remove" style="visibility: hidden;"></i>';
 		  	bpSubModelHTML += '</div>';
 	  		bpSubModelHTML += '</li>';
 	  		bpSubModelHTML += '</ul>';
 	  		bpSubModelDiv.html(bpSubModelHTML);
 	  		
 	  		bpVersionHTML += '<ul class="selectorResultContainer">';
 	  		bpVersionHTML += "<li>";
 	  		bpVersionHTML += '<div class="removeBtn" relContainer="versionModal" value="' + result.versionId + '" style="padding-top: 6px;cursor:pointer" title="删除：' + result.versionName + '">';
 	  		bpVersionHTML += '<input type="hidden" value="' + result.versionId + '" name="selectedVersion" />';
 	  		bpVersionHTML += result.versionName + '<i class="icon-remove" style="visibility: hidden;"></i>';
 	  		bpVersionHTML += '</div>';
 	  		bpVersionHTML += '</li>';
 	  		bpVersionHTML += '<ul>';
 	  		bpVersionDiv.html(bpVersionHTML);
 	  		
 	  		// 渲染竞品车型和型号
 	  		var jpSubModelHTML = "";
 	  		var jpVersionHTML = "";
     		jpSubModelHTML += '<ul class="selectorResultContainer">';
     		jpVersionHTML += '<ul class="selectorResultContainer">';
     		$.each(jpResult, function(i, item) {
     	  		jpSubModelHTML += '<li>';
     	  		jpSubModelHTML += '<div class="removeBtn" relContainer="subModelModal2" value="'+jpResult[i].subModelId+'" style="cursor:pointer" title="删除：'+jpResult[i].subModelName+'">';
     	  		jpSubModelHTML += '<input type="hidden" letter="'+jpResult[i].letter+'" pooAttributeId="'+jpResult[i].pooAttributeId+'" subModelName="'+jpResult[i].subModelName+'" value="'+jpResult[i].subModelId+'" name="selectedSubModel2">';
     	  		jpSubModelHTML += jpResult[i].subModelName + '<i class="icon-remove" style="visibility: hidden;"></i>';
     	  		jpSubModelHTML += '</div>';
     	  		jpSubModelHTML += '</li>';
     	  		
     	  		jpVersionHTML += "<li>";
     	  		jpVersionHTML += '<div class="removeBtn" relContainer="versionModal2" value="' + jpResult[i].versionId + '" style="padding-top: 6px;cursor:pointer" title="删除：' + jpResult[i].versionName + '">';
     	  		jpVersionHTML += '<input type="hidden" value="' + jpResult[i].versionId + '" name="selectedVersion2" />';
     	  		jpVersionHTML += jpResult[i].versionName + '<i class="icon-remove" style="visibility: hidden;"></i>';
     	  		jpVersionHTML += '</div>';
     	  		jpVersionHTML += '</li>';
     		});
     		jpSubModelHTML += '</ul>';
     		jpSubModelDiv.html(jpSubModelHTML);
     		jpVersionHTML += '</ul>';
     		jpVersionDiv.html(jpVersionHTML);
        }
    });
}

/**编辑常用对象组**/
function toEdit(groupId, groupName) {
	// 获取分组排序
	$("#editObj").val(groupName);
	var iframe = window.frames["iframe1"].document;
	$(iframe).find("#groupId").val(groupId);
	$("#commonGroupResultPanel").modal("show");
}

/**新增常用对象组**/
function toAdd() {
	// 获取分组排序
	$("#editObj").val("");
	$("#commonGroupResultPanel").modal("show");
}

/**新增窗口打开**/
$("#commonGroupResultPanel").on("show", function() {
	var editObj = $("#editObj").val();
	//找DIV下找iframe下的对象
	var iframe = window.frames["iframe1"].document
	$(iframe).find("#resetBtn").click();
	$(iframe).find("#saveType").val("add");
	if("" != editObj) {
		$("#headTitle").html("常用对象管理-修改");
		$(iframe).find("#saveType").val("update");
		getData(editObj);
	} else {
		$("#headTitle").html("常用对象管理-新增");
	}
});

/**新增窗口关闭时刷新**/
$("#commonGroupResultPanel").on("hide", function() {
	var iframe = window.frames["iframe1"].document;
	var groupName = $(iframe).find("#commonGroupName").val();
	var groupSort = $(iframe).find("#commonGroupSort").val();
	if(groupName != "" && groupSort != "") {
		location.reload();
	}
});

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
	//车型容器
	$("#subModelModalResultContainer2").html("");
	//型号容器
	$("#versionModalResultContainer").html("");
	//型号容器
	$("#versionModalResultContainer2").html("");
	$("#commonGroupName").val("");
	$("#commonGroupSort").val("");
});

/**设置排序窗口打开时重置数据**/
$("#commonGroupSortPanel").on("show", function() {
	$.ajax({
		type: "post",
  		url: ctx + "/backstageConfig/getCommonGroup.do",
     	success: function(data) {
     	    var selectGroup = $("#commonGroupName");
     	    var result = data.groupList;
     	    groupList = data.groupList;
     	    selectGroup.empty();
         	var title = "<option value='0'>请选择分组</option>";
         	$("#commonGroupName").append(title);
         	var groupName = "";
         	$.each(result, function(i, item) {
                 if(result[i].groupName != "" && groupName != result[i].groupName) {
         		    var op = "<option value='" + result[i].groupId + "'>" + result[i].groupName + "</option>"
         		   selectGroup.append(op);
             		groupName = result[i].groupName;
                 }
             });
         }
     });
    $("#commonGroupName").val("0");
    $("#sortForm").empty();
});

/**导入窗口打开时重置数据**/
$("#commonGroupImportPanel").on("show", function() {
	//找DIV下找iframe下的对象
	var iframe = window.frames["iframe2"].document;
    $(iframe).find("#importFile").val("");
});

/**导入窗口关闭时刷新**/
$("#commonGroupImportPanel").on("hide", function() {
	var iframe = window.frames["iframe2"].document;
	var importFile = $(iframe).find("#importFile").val();
	if(importFile != "") {
		location.reload();
	}
});