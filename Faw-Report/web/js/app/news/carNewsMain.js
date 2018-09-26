var dataTable = null;
/**定义子车型LI全局变量*/
var currSubModelLI = null;
/**定义车身形式LI全局变量*/
var currBodyTypeLI = null;

window.getParams = function()
{
	var beginDate = $("#startDate").val();
	var endDate = $("#endDate").val();

//	//数据分析类型默认为销量
//	var analysisContentType = $('#moduleCode').val();
	var paramObj = {};
	var subModelId = $(".subModelModalResultContainer input").map(function(){return $(this).val();}).get().join(",");
	var hatchbackId = $("#model .subModelLIContainer").eq($("#getModelIndexId").val()).find("td:eq(1)").find("li div :input").map(function(){return $(this).val();}).get().join(",");
	paramObj.subModelId = subModelId;
	paramObj.hatchbackId = hatchbackId;
	paramObj.beginDate = beginDate;
	paramObj.endDate = endDate;
//	paramObj.analysisContentType = analysisContentType;
//	getDateGroup(paramObj);
////	timeRange = getTimeRange(paramObj.dateGroup);
////	paramObj.beginDate = timeRange.beginDate;
////	paramObj.endDate = timeRange.endDate;
//	paramObj.browser = navigator.appVersion;
	return paramObj;
};

/**
 * 弹出框
 * @param type
 */
window.showSubModel = function(type)
{
	var inputType = "1"; //弹出框默认多选
	if('1' == type) getModelPage("tabs-competingProducts",type,inputType);
	else if('2' == type) getModelPage("tabs-segment",type,inputType);
	else if('3' == type) getModelPage("tabs-brand",type,inputType);
	else getModelPage("tabs-manf",type,inputType);
};

/**
 * 获取时间组
 * paramObj JSON对象
 */
window.getDateGroup = function(paramObj)
{
	var dateGroup = "";
	var dataLi = $("#dateYear .dateLIContainer");
	$.each(dataLi,function(i,n){
		dateGroup += $(n).find("#startDate-container :input").val() + "," +  $(n).find("#endDate-container :input").val() + "|";
	});
	if(dataLi.length > 1) paramObj.multiType = "1";//证明时间多选
	paramObj.dateGroup = dateGroup;
};

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



/** 改变车型时，清空不相连的型号*/
function clearVersionByModelChange()
{
	//车型名称
	var modelName = $("#subModelModalResultContainer li div").map(function(){return $(this).text();});
	//型号对象
	var versionObjs = $("#versionModalResultContainer li");
	var versionBody = $("#versionModalBody .letterContainer");
	if (0 == modelName.length) {
		$(versionObjs).remove();
		$(versionBody).remove();
	}
	else
	{
		if(0 != versionObjs.length)
		{
			$.each(versionObjs,function(i,n){
				var versionName = $(n).find("div").text();
				var delFlag = true;
				for(var j = 0; j < modelName.length; j++)
				{
					if(-1 != versionName.indexOf(modelName[j]))
					{
						delFlag = false;
						break;
					}
				}
				if(delFlag) $(n).remove();
			});
		}
	}
}


function addResultContainerBySubModel(){
	//显示选中的值	——开始
	//去掉重复选项
	$("#selectorResultContainerBySubModel").html();
	var allSubModelArr = [];
	$(".subModelModalContainer").find('.subModelIdInput:checked').each(function(){
		var obj = {};
		obj.subModelId =  $(this).val();
		obj.subModelName =  $.trim($(this).parent().text());
		obj.letter =  $(this).attr("letter");
		obj.pooAttributeId = $(this).attr("pooAttributeId");
		allSubModelArr[allSubModelArr.length] = obj;
	});
	allSubModelArr = uniqueSubModel(allSubModelArr); 
	var strHTML = '<ul class="inline" >';
	for(var i=0;i<allSubModelArr.length;i++){
		strHTML += '<li style="padding-top:4px;margin-bottom:2px;">';
	  		strHTML += '<div class="removeBtnByResult label label-info" subModelId="'+allSubModelArr[i].subModelId+'"  pooAttributeId="'+allSubModelArr[i].pooAttributeId+'" letter="'+allSubModelArr[i].letter+'" subModelName="'+allSubModelArr[i].subModelName+'" style="cursor:pointer" title="删除：'+allSubModelArr[i].subModelName+'">';
		  		strHTML += '<i class="icon-remove icon-white"></i>'+allSubModelArr[i].subModelName;
	  		strHTML += '</div>';
	 		strHTML += '</li>';
	 }
	 strHTML += '</ul>';
	$("#selectorResultContainerBySubModel").html(strHTML);
	//显示选中的值	——结束
}




/**获取车身形式和车型模板*/
function getSubModelTp(){
	var htmlStr = "";
	htmlStr += '<li style="list-style: none;margin-bottom:10px;" class="subModelLIContainer">';
	htmlStr += '	<table style="width:570px;">';
	htmlStr += '		<tr>';
	htmlStr += '			<td valign="top" nowrap="nowrap" style="width:8%">';
	htmlStr += '				<div>';
	htmlStr += '					<a href="#" role="button" class="btn bodyTypeSelector" data-toggle="modal">车身形式</a>';
 	htmlStr += '				</div>';
	htmlStr += '			</td>';
	htmlStr += '			<td valign="top" nowrap="nowrap" style="width:29%">';
	htmlStr += '				<div  style="margin-left:0px" class="bodyTypeModalResultContainer">';
	htmlStr += '				</div>';
	htmlStr += '			</td>';
	htmlStr += '			<td valign="top" style="width:15%">	';
	htmlStr += '				<div>';
	htmlStr += '					<a href="#" role="button" class="btn subModelSelector" data-toggle="modal">选择车型</a>';
 	htmlStr += '				</div>';
 	htmlStr += '			</td>';
	htmlStr += '			<td valign="top" nowrap="nowrap" style="width:42%">';
	htmlStr += '				<div style="margin-left:0px" class="subModelModalResultContainer">';
	htmlStr += '				</div>';
	htmlStr += '			</td>';
	htmlStr += '		</tr>';
	htmlStr += '	</table>';
	htmlStr += '</li>';
	return htmlStr;
};

/**初始化时，加载对象模板*/
function initPage(){
//	$(".dateULContainer").each(function(){ 
//		$(this).append(getDateTp());
//	});
	$(".subModelULContainer").each(function(){ 
		$(this).append(getSubModelTp());
	});
//	initDate();
};

/**把有全选的选项选上**/
function checkAll(type){
	if("2" == type){
		var allObj = $("#tabs-segment .selectorTypeTd");
		$(allObj).find(".selectSegmentAll").each(function(){
			var flag = true;
			var modelObj = $(this);
			$(modelObj).closest("td").next().find(".subModelIdInput").each(function(){
				if(!$(this).prop("checked")){
					flag = false;
				}
			});
			//如果一个细分市场下的车型被全选，则细分市场也选上
			if(flag == true){
				modelObj.prop("checked", true);
			}
		});
	} else if("3" == type){
		var allObj = $("#tabs-brand .selectorTypeTd");
		$(allObj).find(".selectBrandAll").each(function(){
			var flag = true;
			var modelObj = $(this);
			$(modelObj).closest("td").next().find(".subModelIdInput").each(function(){
				if(!$(this).prop("checked")){
					flag = false;
				}
			});
			//如果一个品牌下的车型被全选，则品牌也选上
			if(flag == true){
				modelObj.prop("checked", true);
			}
		});
	} else if("4" == type){
		var allObj = $("#tabs-manf .selectorTypeTd");
		$(allObj).find(".selectManfAll").each(function(){
			var flag = true;
			var modelObj = $(this);
			$(modelObj).closest("td").next().find(".subModelIdInput").each(function(){
				if(!$(this).prop("checked")){
					flag = false;
				}
			});
			//如果一个厂商下的车型被全选，则厂商也选上
			if(flag == true){
				modelObj.prop("checked", true);
			}
		});
	} 
}


/**
 * 展示页面
 * @param id 展示容器ID
 * @param type 子车型弹出框展示类型2：细分市场,3:品牌,4:厂商，为空则是本品与竟品
 * @param inputType 控件类型1：复选，2：单选;默认为1
 * @param timeType 时间类型1：时间点;2：时间段默认为2
 * 
 */
function getModelPage(id,type,inputType,timeType)
{

	//如果内容不为空则触发请求
	if(!$.trim($('#' + id).html())){
		//获取时间
//		var beginDate = $("#startDate").val();
//		var endDate = $("#endDate").val();
//		var beginDate ='2016-01';
//		var endDate ='2016-01';
		var beginDate = $("#startDate").val();
		var endDate = $("#endDate").val();

		var hatchbackId = "";
		var paramObj = {};
		var dateGroup = null;
		var priceType = null;
//		getDateGroup(paramObj)
		
		//数据分析类型如果没有默认为成交价
		var analysisContentType = $('#moduleCode').val();
		//如果数据分析类型是销量校验
//		if("salespriceanaly" == analysisContentType)
//		{	
//			dateGroup = paramObj.dateGroup;
//			var timeRange = getTimeRange(dateGroup);
//			beginDate = timeRange.beginDate;
//			endDate   = timeRange.endDate;
			priceType = $("#priceType").val();
			hatchbackId = $("#model .subModelLIContainer").eq($("#getModelIndexId").val()).find("td:eq(1)").find("li div :input").map(function(){return $(this).val();}).get().join(",");
//		}
		//传递参数
		var params = {inputType:inputType,subModelShowType:type,beginDate:beginDate,endDate:endDate,analysisContentType:analysisContentType,timeType:timeType,hatchbackId:hatchbackId,dateGroup:dateGroup,priceType:priceType};
		//触发请求
//		showLoading(id);
		$("#" + id).load(ctx+"/news/getSubmodelModal.do",params,function(){
			$("#selectorResultContainerBySubModel .removeBtnByResult").each(function(){
				var subModelId = $(this).attr("subModelId");
				$(".subModelModalContainer .subModelIdInput").each(function(){
					if($(this).val() == subModelId) $(this).attr("checked",'true');//行全选
				});
			});
			checkAll(type);
		});
	}
};





$(document).ready(function(){
    //初始化页面
	initPage();

	
	$('#startDate-container.input-append.date').datetimepicker({
		format: 'yyyy-mm-dd',
        language:  'zh-CN',        
        todayBtn:  0,
		autoclose: 1,				
//		startView: 3,		
		maxView:3,
		minView:"month",
		startDate:beginDate,		
		endDate:endDate,
        showMeridian: 1
	});
    $('#endDate-container.input-append.date').datetimepicker({
		format: 'yyyy-mm-dd',
        language:  'zh-CN',        
        todayBtn:  0,
		autoclose: 1,				
//		startView: 3,		
		maxView:3,
		minView:"month",
		startDate:beginDate,
		endDate:endDate,
        showMeridian: 1
    });
    
	//时间改变事件
//	$('#endDate-container.input-append.date').on('changeDate',function(){
	$(document).on('changeDate','#endDate-container.input-append.date',function(){
		var beginDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		if(!beginDate || !endDate){
			alert("请选择时间");
			return false;
		}else if(parseInt(beginDate.replace("-","").replace("-","")) > parseInt(endDate.replace("-","").replace("-","")) ){
			alert("开始时间不能大于结束时间");
			return false;
		}	
		
	});
	
    $('#startDate-container.input-append.date').on('changeDate',function(){
    	var beginDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		if(!beginDate || !endDate){
			alert("请选择时间");
			return;
		}else if(parseInt(beginDate.replace("-","").replace("-","")) > parseInt(endDate.replace("-","").replace("-","")) ){
			alert("开始时间不能大于结束时间");
			return;
		}
    });
	
//	$('input[name="reportType"]').iCheck({
//        checkboxClass: 'icheckbox_square-blue',
//        radioClass: 'iradio_square-blue',
//        increaseArea: '20%'
//    });	
//	
//	$('input[name="dateType"]').iCheck({
//        checkboxClass: 'icheckbox_square-blue',
//        radioClass: 'iradio_square-blue',
//        increaseArea: '20%'
//    });	
	
	
	/**车身形式-开始*/
	$(".bodyTypeModalContainer").find('.bodyTypeModalByAll').on('click',function(){

		if($(this).prop("checked")){
			$(this).parents('.bodyTypeModalContainer').find(".modal-body input").each(function(index){
		 		$(this).prop("checked", true);//全选
		 	});
		}else{
			$(this).parents('.bodyTypeModalContainer').find(".modal-body input").each(function(index){
		 		 $(this).removeAttr("checked");//取消全选
//		 		$(this).attr("checked",'true');//全选
		 	});
		} 
	});
	/**车身形式点击*/
	$(document).on('click','.bodyTypeModalContainer .bodyTypeModal',function(){
		
		var allArr = $(this).parents(".bodyTypeModalContainer").find(".bodyTypeModal");
		var allSelectedArr = $(this).parents(".bodyTypeModalContainer").find(".bodyTypeModal:checked");
		if(allArr.length == allSelectedArr.length){
			$(this).parents('.bodyTypeModalContainer').find('.bodyTypeModalByAll').prop("checked",true);//全部全选		
		}else{
			$(this).parents('.bodyTypeModalContainer').find('.bodyTypeModalByAll').removeAttr("checked");//全部取消
		}
	});
	
	/**点击确定生成内容*/
	$(".bodyTypeModalContainer").find('.confirm').on('click',function(){

		var containerId = $(this).parents(".bodyTypeModalContainer").attr("id");
		var relInputName = $(this).attr("relInputName");
		var strHTML = "";
		strHTML += '<ul class="selectorResultContainer">';
		//如果全部选中，则生成全部
		if($(".bodyTypeModalByAll").prop("checked"))
		{
			strHTML += '<li>';
			strHTML += '<div class="removeBtn" relContainer="bodyTypeModal" value="0" style="cursor:pointer;" title="全部">';
			strHTML += '<input type="hidden" value="0" name="selectedBodyType" />';
			strHTML += '全部<i class="icon-remove" style="visibility: hidden;"></i>';
			strHTML += '</div>';
			strHTML += '</li>';
		}
		else
		{							
			$(this).parents(".bodyTypeModalContainer").find('.bodyTypeModal:checked').each(function(){
				strHTML += '<li>';
				strHTML += '<div class="removeBtn" relContainer="'+containerId+'" value="'+$(this).val()+'" style="cursor:pointer" title="删除：'+$.trim($(this).parent().text())+'">';
				strHTML += '<input type="hidden" value="'+$(this).val()+'" name="'+relInputName+'" levelType="1" />';
				strHTML += $.trim($(this).parent().text()) + '<i class="icon-remove" style="visibility: hidden;"></i>';
				strHTML += '</div>';
				strHTML += '</li>';
			});
		}
		strHTML += '</ul>';
		$(currBodyTypeLI).html(strHTML);
	});
	
	/** 车身形式控件值鼠标经过事件*/
	$(".bodyTypeModalResultContainer ul div").on('mouseover',function(){
		$(this).find(".icon-remove").css({visibility:'visible'});
	});
	
	/** 车身形式控件值鼠标离开事件*/
	$(".bodyTypeModalResultContainer ul div").on('mouseout',function(){
		$(this).find(".icon-remove").css({visibility:'hidden'});
	});
	
	/** 车身形式-结束*/
	
	
	
	
	/** 弹出车身形式对话框*/
	$(".bodyTypeSelector").on('click',function(){
		currBodyTypeLI = $(this).parents('table').find('.bodyTypeModalResultContainer');
//		console.log(currBodyTypeLI);
		$('#bodyTypeModal').modal('show');
	});
	
//	/**展示车身形式弹出框*/
	$('#bodyTypeModal').on('show', function (e) {
		if(e.relatedTarget)  return; //修复bootstrap的modal引入tabpane时，触发事件问题。
		//去掉默认选中的效果
		$('.bodyTypeModalByAll').each(function(){
			$(this).removeAttr("checked");//取消行全选
		});
		/**打开车身形式选择框时，清空车型选择*/
		$(".subModelModalResultContainer").html("");
		//加载子车型数据
//		showLoading("bodyTypeModalBody");
		$('#bodyTypeModalBody').load(ctx+"/news/getBodyType.do",getParams(),function(){
			//弹出框设置默认选中项结果集		
			$(currBodyTypeLI).find('input').each(function(){
				var bodyTypeId = $(this).val();
				if(bodyTypeId == "0"){
					$('.bodyTypeModalByAll').each(function(){
						$(this).prop("checked",true);//全选
					});
					$(".bodyTypeModalContainer .bodyTypeModal").each(function(){
						$(this).prop("checked",true);//行全选
					});
				}else{
				    $(".bodyTypeModalContainer .bodyTypeModal").each(function(){
						if($(this).val() == bodyTypeId){
							$(this).prop("checked",true);//行全选
						}
			        });
				}
			});
		});
	});
	
	
	
	
	
	
	
	/**展示车型弹出框-开始*/
	$(".subModelSelector").on('click',function(){
		currSubModelLI = $(this).parents('.subModelLIContainer');
		//保存获取车形弹出框当前车型下标
		$("#getModelIndexId").val($("#model .subModelULContainer .subModelLIContainer").index(currSubModelLI));
		$('#subModelModal').modal('show');
	});
	
	/** 展示车型弹出框*/
	$('#subModelModal').on('show', function (e) {
		if(e.relatedTarget)  return; //修复bootstrap的modal引入tabpane时，触发事件问题。
		//加载子车型数据
//		showLoading("subModelModalBody");
		
		$('#subModelModalBody').load(ctx+"/news/getSubmodelModal.do",getParams(),function(){
			//弹出框设置默认选中项结果集		
			var strHTML = '<ul class="inline" >';
			$(currSubModelLI).find('.subModelModalResultContainer input').each(function(){
				var subModelId = $(this).val();
				var subModelName = $(this).attr("subModelName");
				var pooAttributeId = $(this).attr("pooAttributeId");
				var letter = $(this).attr("letter");
				strHTML += '<li style="padding-top:4px;margin-bottom:2px;">';
			  		strHTML += '<div class="removeBtnByResult label label-info" subModelId="'+subModelId+'"  pooAttributeId="'+pooAttributeId+'" letter="'+letter+'" subModelName="'+subModelName+'" style="cursor:pointer" title="删除：'+subModelName+'">';
			  		strHTML += '<i class="icon-remove icon-white"></i>'+subModelName;
			  		strHTML += '</div>';
			 		strHTML += '</li>';
		 		$(".subModelModalContainer .subModelIdInput").each(function(){
					if($(this).val() == subModelId){
						$(this).attr("checked",'true');
					}
				});
			});
			strHTML += '</ul>';
			$("#selectorResultContainerBySubModel").html(strHTML);
		});
	});
	
	// 车型确认按钮动作
	$(".subModelModalContainer").find('.confirm').unbind('click').bind('click',function(e){
		var containerId = $(this).parents(".subModelModalContainer").attr("id");
		var relInputName = $(this).attr("relInputName");
		var allSubModelArr = [];
		$(this).parents(".subModelModalContainer").find('.resultShowContent').find('.removeBtnByResult').each(function(){
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
		$(currSubModelLI).find('.subModelModalResultContainer').html(strHTML);
	});
	/**展示车型弹出框-结束*/
	

	/** 车型控件值鼠标经过事件*/
	$(document).on('mouseover', ".removeBtn", function(){
		$(this).find(".icon-remove").css({visibility:'visible'});
	});

	/**车型控件值鼠标离开事件*/
	$(document).on('mouseout',".removeBtn",function(){
		$(this).find(".icon-remove").css({visibility:'hidden'});
	});

	/**删除车身形式时，清空车型选择*/
	$(document).on("click",".bodyTypeModalResultContainer .icon-remove",function(){
		$(".subModelModalResultContainer").html("");
	});
	
	
	/** 删除*/
	$(document).on("click",".selectorResultContainer .icon-remove",function(){
		$(this).parent().remove();
	});
	
	
	/**子车型如化------------开始*/
	$(document).on('click',".subModelModalContainer .subModelIdInput",function(){
		//设置联动选择，当某个车型被选中时，其它标签相关的一样的车型也会选中。 -----开始			
		if($(this).prop("checked")){
			if($(this).attr('type') == 'radio'){
				$(this).parents('.subModelModalContainer').find('.subModelIdInput:checked').each(function(){
					$(this).prop("checked", false);//取消选中
				});
			}
			var currVal = $(this).val();
			$(this).parents('.subModelModalContainer').find('.subModelIdInput').filter(function(){
				return $(this).val() == currVal;
			}).each(function(){
				$(this).prop("checked", true);
			});
		}else{
			var currVal = $(this).val();
			$(this).parents('.subModelModalContainer').find('.subModelIdInput').filter(function(){
				return $(this).val() == currVal;
			}).each(function(){
				$(this).prop("checked", false);//取消选中
			});;		
		}
		//设置联动选择，当某个车型被选中时，其它标签相关的一样的车型也会选中。 -----结束
		
		addResultContainerBySubModel();
	});
	
//	$(".subModelModalContainer .resultShowContent .removeBtnByResult").on('click',function(){
		$(document).on('click',".subModelModalContainer .resultShowContent .removeBtnByResult",function(){
//		var currVal = $(this).attr("subModelId");
		var currVal = $(this).prop("subModelId");
		$(this).parents('.subModelModalContainer').find('.subModelIdInput').filter(function(){
			return $(this).val() == currVal;
		}).each(function(){
			$(this).removeAttr("checked");//取消选中
		});;	
		$(this).parent().remove();
	});
	
	/**子车型选择确定按扭---开始*/
	$(".subModelModalContainer").find('.confirm').bind('click',function(event){
		//event.stopPropagation();
		var containerId = $(this).parents(".subModelModalContainer").attr("id");
		var relContainer = $(this).attr("relContainer");
		var relInputName = $(this).attr("relInputName");
		/**
		 * --start常用对象组互斥
		 * 部分模块存在除车型,型号对象之外还存在常用组对象
		 * 常用组与车型,型号对象互斥,只能存在一个对象有值
		 */
		var subModelLength = $(this).parents(".subModelModalContainer").find('.resultShowContent').find('.removeBtnByResult').length;
		if(subModelLength > 0){
			var autoVersionResult = $("#autoVersionModalResultContainer");
			if(autoVersionResult.length > 0)$("#autoVersionModalResultContainer").html("");
		}
		/**--end常用对象组互斥**/
		var allSubModelArr = [];
		$(this).parents(".subModelModalContainer").find('.resultShowContent').find('.removeBtnByResult').each(function(){
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
		  		strHTML += '<div class="removeBtn" relContainer="'+containerId+'" value="'+allSubModelArr[i].subModelId+'" style="padding-top: 6px;cursor:pointer" title="删除：'+allSubModelArr[i].subModelName+'">';
			  		strHTML += '<input type="hidden" letter="'+allSubModelArr[i].letter+'" pooAttributeId="'+allSubModelArr[i].pooAttributeId+'" subModelName="'+allSubModelArr[i].subModelName+'" value="'+allSubModelArr[i].subModelId+'" name="'+relInputName+'" />';
			  		strHTML += allSubModelArr[i].subModelName + '<i class="icon-remove" style="visibility: hidden;"></i>';
		  		strHTML += '</div>';
	  		strHTML += '</li>';
		 }
		strHTML += '<ul>';
		$("#"+relContainer).html(strHTML);
		//清空型号
		clearVersionByModelChange();
		 
	});
	
	/** 子车型控件值鼠标经过事件*/
	$("#subModelModalResultContainer ul div").on('mouseover',function(){
		$(this).find(".icon-remove").css({visibility:'visible'});
	});
	
	/**子车型控件值鼠标离开事件*/
	$("#subModelModalResultContainer ul div").on('mouseout',function(){
		$(this).find(".icon-remove").css({visibility:'hidden'});
	});
	/**子车型选择确定按扭---结束*/
	

	
	
	
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
	    "bProcessing": true, //当datatable获取数据时候是否显示正在处理提示信息。 
	    "bServerSide": true,
	    "sAjaxSource": ctx+"/news/newsCarsInfo.do?rand="+Math.random(),
		"sServerMethod":"post",
		"fnServerParams": function ( aoData ) {
												var qReportType = $('input[name="reportType"]:checked').val();
												var qDateType = $('input[name="dateType"]:checked').val();
												var qBeginDate = $("#startDate").val();
												var qEndDate = $("#endDate").val();
												aoData.push( {"name":"qReportType","value": qReportType});
												aoData.push( {"name":"qDateType","value": qDateType});
												aoData.push( {"name":"beginDate","value": qBeginDate});
												aoData.push( {"name":"endDate","value": qEndDate});
											  },
        "columns": [
    				{ "data": ""},
    				{ "data": "launchDate"},
    				{ "data": "versionCode"},
    				{ "data": "brandName"},
    				{ "data": "brandNameEn"},
    				{ "data": "manfName"},
    				{ "data": "manfNameEn"},
    				{ "data": "segment"},
    				{ "data": "modelName"},
    				{ "data": "modelNameEn"},
    				{ "data": "trim"},
    				{ "data": "trimEn"},
    				{ "data": "versionName"},
    				{ "data": "versionNameEn"},
    				{ "data": "MSRP"},
    				{ "data": "preVersionName"},
    				{ "data": "preMSRP"},										
    				{ "data": "bodyType"},
    				{ "data": "bodyTypeEn"},
    				{ "data": "driveType"},
    				{ "data": "driveTypeEn"},
    				{ "data": "carIn"},										
    				{ "data": "carInEn"},
    				{ "data": "changeDescription"},
    				{ "data": "changeDescriptionEn"},
    				{ "data": "LWH"},
    				{ "data": "wheelbase"},										
    				{ "data": "PL"},
    				{ "data": "transmission"},
    				{ "data": "maximumPower"},						
					{ "data": "maximumTorque"},										
    				{ "data": "KWL"},
    				{ "data": "sellingPoints"}
        ],
	    "columnDefs": [
	    	{
	            "render": function ( data, type, row ) {
					var str = '<a  style="cursor:pointer;" onclick="toEdit()"  title="编辑"> &nbsp;<i class="cus-pencil"></i><input type="hidden" name="idInput" value="" /> </a>';
					    str+= '<a  style="cursor:pointer;" onclick="toDelete()"  title="删除"> &nbsp;<i class="cus-basket_delete"></i></a>';
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
			$(this).html( '<input name="'+colName+'" style="width:100%" type="text" id="'+colName+'" placeholder="'+title+'" colName='+colName+' />' );
		}
	} );
	
	$( 'input',$('#searchTr th')).on('keyup', function (event) {
		if(13 == event.keyCode){
			var val = $(this).val();
			search();
		}
	});
	
	
	
	/**
	//双击进编辑页面
	$('#dataTable tbody').on( 'dblclick', 'tr', function () {
		$("input[name='idInput']",this).each(function(){
    		toEdit($(this).val());
    	});
    });
	*/
}); 



/**
 * 搜索
 */
function search(){
	//获取参数
	var qlaunchDate=$.trim($("#qlaunchDate").val());
	var qversionCode=$.trim($("#qversionCode").val());
	var qbrandName=$.trim($("#qbrandName").val());
	var qbrandNameEn=$.trim($("#qbrandNameEn").val());
	var qmanfName=$.trim($("#qmanfName").val());
	var qmanfNameEn=$.trim($("#qmanfNameEn").val());
	var qsegment=$.trim($("#qsegment").val());
	var qmodelName=$.trim($("#qmodelName").val());
	var qmodelNameEn=$.trim($("#qmodelNameEn").val());
	var qtrim=$.trim($("#qtrim").val());
	var qtrimEn=$.trim($("#qtrimEn").val());
	var qversionName=$.trim($("#qversionName").val());
	var qversionNameEn=$.trim($("#qversionNameEn").val());
	var qMSRP=$.trim($("#qMSRP").val());
	var qpreVersionName=$.trim($("#qpreVersionName").val());
	var qpreMSRP=$.trim($("#qpreMSRP").val());				
	var qbodyType=$.trim($("#qbodyType").val());
	var qbodyTypeEn=$.trim($("#qbodyTypeEn").val());
	var qdriveType=$.trim($("#qdriveType").val());
	var qdriveTypeEn=$.trim($("#qdriveTypeEn").val());
	var qcarIn=$.trim($("#qcarIn").val());				
	var qcarInEn=$.trim($("#qcarInEn").val());
	var qchangeDescription=$.trim($("#qchangeDescription").val());
	var qchangeDescriptionEn=$.trim($("#qchangeDescriptionEn").val());
	var qLWH=$.trim($("#qLWH").val());
	var qwheelbase=$.trim($("#qwheelbase").val());			
	var qPL=$.trim($("#qPL").val());
	var qtransmission=$.trim($("#qtransmission").val());
	var qmaximumPower=$.trim($("#qmaximumPower").val());			
	var qmaximumTorque=$.trim($("#qmaximumTorque").val());		
	var qKWL=$.trim($("#qKWL").val());
	var qsellingPoints=$.trim($("#qsellingPoints").val());
	
	var qReportType = $('input[name="reportType"]:checked').val();
	var qDateType = $('input[name="dateType"]:checked').val();
	var qBeginDate = $("#startDate").val();
	var qEndDate = $("#endDate").val();
	
	var subModelId = $(".subModelModalResultContainer input").map(function(){return $(this).val();}).get().join(",");
//	var hatchbackId = $("#model .subModelLIContainer").eq($("#getModelIndexId").val()).find("td:eq(1)").find("li div :input").map(function(){return $(this).val();}).get().join(",");
	var hatchbackId = $("#model .subModelLIContainer").eq($("#getModelIndexId").val()).find("td:eq(1)").find("li div :input").map(function(){return $(this).val();}).get().join(",");
	var beginDate = $("#startDate").val();
	var endDate = $("#endDate").val();

	dataTable.fnClearTable(0); //清空数据
	var param = dataTable.fnSettings().aoServerParams[0];
	param.fn = function ( aoData ) {
		aoData.push( {"name":"qlaunchDate","value":qlaunchDate});
		aoData.push( {"name":"qversionCode","value":qversionCode});
		aoData.push( {"name":"qbrandName","value":qbrandName});
		aoData.push( {"name":"qbrandNameEn","value":qbrandNameEn});
		aoData.push( {"name":"qmanfName","value":qmanfName});
		aoData.push( {"name":"qmanfNameEn","value":qmanfNameEn});
		aoData.push( {"name":"qsegment","value":qsegment});
		aoData.push( {"name":"qmodelName","value":qmodelName});
		aoData.push( {"name":"qmodelNameEn","value":qmodelNameEn});
		aoData.push( {"name":"qtrim","value":qtrim});
		aoData.push( {"name":"qtrimEn","value":qtrimEn});
		aoData.push( {"name":"qversionName","value":qversionName});
		aoData.push( {"name":"qversionNameEn","value":qversionNameEn});
		aoData.push( {"name":"qMSRP","value":qMSRP});
		aoData.push( {"name":"qpreVersionName","value":qpreVersionName});
		aoData.push( {"name":"qpreMSRP","value":qpreMSRP});
		aoData.push( {"name":"qbodyType","value":qbodyType});
		aoData.push( {"name":"qbodyTypeEn","value":qbodyTypeEn});
		aoData.push( {"name":"qdriveType","value":qdriveType});
		aoData.push( {"name":"qdriveTypeEn","value":qdriveTypeEn});
		aoData.push( {"name":"qcarIn","value":qcarIn});
		aoData.push( {"name":"qcarInEn","value":qcarInEn});
		aoData.push( {"name":"qchangeDescription","value":qchangeDescription});
		aoData.push( {"name":"qchangeDescriptionEn","value":qchangeDescriptionEn});
		aoData.push( {"name":"qLWH","value":qLWH});
		aoData.push( {"name":"qwheelbase","value":qwheelbase});
		aoData.push( {"name":"qPL","value":qPL});
		aoData.push( {"name":"qtransmission","value":qtransmission});
		aoData.push( {"name":"qmaximumPower","value":qmaximumPower});
		aoData.push( {"name":"qmaximumTorque","value":qmaximumTorque});
		aoData.push( {"name":"qKWL","value":qKWL});
		aoData.push( {"name":"qsellingPoints","value":qsellingPoints});
		aoData.push( {"name":"qReportType","value": qReportType});
		aoData.push( {"name":"qDateType","value": qDateType});
		aoData.push( {"name":"qBeginDate","value": qBeginDate});
		aoData.push( {"name":"qEndDate","value": qEndDate});
		
		aoData.push( {"name":"subModelId","value": subModelId});
		aoData.push( {"name":"hatchbackId","value": hatchbackId});
		aoData.push( {"name":"beginDate","value": beginDate});
		aoData.push( {"name":"endDate","value": endDate});
	};
	dataTable.fnDraw(); //重新加载数据
}					

function exportExcel(){
	$("#exportFormId").submit();
}