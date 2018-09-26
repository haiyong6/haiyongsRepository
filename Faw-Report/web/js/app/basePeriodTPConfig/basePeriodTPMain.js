var dataTable = null;
/**定义本品车型全局变量*/
var currSubModelLI = null;
/**定义竞品车型全局标量*/
var currSubModelLI2 = null;
var groupList = null;
$(document).ready(function() {
	
	$('#startDate-container.input-append.date').datetimepicker({
		format: 'yyyy',
        language:  'zh-CN',        
        todayBtn:  0,
		autoclose: 1,				
		startView: 4,		
		maxView:4,
		minView:4,
		startDate:beginDate,		
		endDate:endDate,
        showMeridian: 1
    });
    $('#endDate-container.input-append.date').datetimepicker({
		format: 'yyyy',
        language:  'zh-CN',        
        todayBtn:  0,
		autoclose: 1,				
		startView: 4,		
		maxView:4,
		minView:4,
		startDate:beginDate,
		endDate:endDate,
        showMeridian: 1
    });
    
	//时间改变事件
	$('#endDate-container.input-append.date').on('changeDate',function(){
		var beginDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		if(!beginDate || !endDate){
			alert("请选择时间");
			return false;
		}else if(parseInt(beginDate.replace("-","")) > parseInt(endDate.replace("-","")) ){
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
		}else if(parseInt(beginDate.replace("-","")) > parseInt(endDate.replace("-","")) ){
			alert("开始时间不能大于结束时间");
			return;
		}
    });
	
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
	    "sAjaxSource": ctx+"/basePeriodTPConfig/getBasePeriodTPInfo.do?rand="+Math.random(),
		"sServerMethod":"post",
		"fnServerParams": function (aoData) {
											var	qYear = $.trim($("#qYear").val());
//											var qManfName = $.trim($("#qManfName").val());
//											var qSubModelName = $.trim($("#qSubModelName").val());
//											var	qVersionName = $.trim($("#qVersionName").val());
//											var qMSRP = $.trim($("#qMSRP").val());
											var qBeginDate = $("#startDate").val();
											var qEndDate = $("#endDate").val();
											aoData.push( {"name":"qBeginDate","value": qBeginDate});
											aoData.push( {"name":"qEndDate","value": qEndDate});
											aoData.push( {"name":"qYear", "value": qYear});
//											aoData.push( {"name":"qManfName", "value": qManfName});
//											aoData.push( {"name":"qSubModelName", "value": qSubModelName});
//											aoData.push( {"name":"qVersionName", "value": qVersionName});
//											aoData.push( {"name":"qMSRP", "value": qMSRP});
											},
        "columns": [
    				{ "data": ""},
    				{ "data": "versionCode"},
    				{ "data": "year"},
    				{ "data": "manf"},
    				{ "data": "model"},
    				{ "data": "modelName"},
    				{ "data": "MSRP"},
    				{ "data": "TP"}
        ],
	    "columnDefs": [
	    	{
	            "render": function ( data, type, row ) {
					var str = '<a style="cursor:pointer;" onclick="#" title="编辑"> &nbsp;<i class="cus-pencil"></i></a>';
					    str += '<a style="cursor:pointer;" onclick="#"  title="删除"> &nbsp;<i class="cus-basket_delete"></i></a>';
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
	var	qVersionCode = $.trim($("#qVersionCode").val());
	var	qYear = $.trim($("#qYear").val());
	var	qManf = $.trim($("#qManf").val());
	var	qModel = $.trim($("#qModel").val());
	var	qModelName = $.trim($("#qModelName").val());
	var	qTP = $.trim($("#qTP").val());
	var	qMSRP = $.trim($("#qMSRP").val());
	var qBeginDate = $("#startDate").val();
	var qEndDate = $("#endDate").val();
	
	dataTable.fnClearTable(0); //清空数据
	var param = dataTable.fnSettings().aoServerParams[0];
	param.fn = function ( aoData ) {
		aoData.push( {"name":"qVersionCode", "value": qVersionCode});
		aoData.push( {"name":"qYear", "value": qYear});
		aoData.push( {"name":"qManf", "value": qManf});
		aoData.push( {"name":"qModel", "value": qModel});
		aoData.push( {"name":"qModelName", "value": qModelName});
		aoData.push( {"name":"qTP", "value": qTP});
		aoData.push( {"name":"qMSRP", "value": qMSRP});
		aoData.push( {"name":"qBeginDate","value": qBeginDate});
		aoData.push( {"name":"qEndDate","value": qEndDate});
	};
	dataTable.fnDraw(); //重新加载数据
}

function showLoading(containerId) {
	$("#"+containerId).html('<div class="text-center"></div>');
}

/**
 * 导出
 */
function exportExcel() {
	$("#exportFormId").submit();
}

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