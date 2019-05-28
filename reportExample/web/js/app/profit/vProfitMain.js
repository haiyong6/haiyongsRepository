var dataTable = null;
$(document).ready(function(){
	$('#startDate-container.input-append.date').datetimepicker({
		format: 'yyyy-mm',
        language:  'zh-CN',        
        todayBtn:  0,
		autoclose: 1,				
		startView: 3,		
		maxView:3,
		minView:3,
		startDate:beginDate,		
		endDate:endDate,
        showMeridian: 1
    });
    $('#endDate-container.input-append.date').datetimepicker({
		format: 'yyyy-mm',
        language:  'zh-CN',        
        todayBtn:  0,
		autoclose: 1,				
		startView: 3,		
		maxView:3,
		minView:3,
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
	
	$('input[name="saleType"]').iCheck({
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
	    "bProcessing": true, //当datatable获取数据时候是否显示正在处理提示信息。 
	    "bServerSide": true,
	    "sAjaxSource": ctx+"/profit/vProfitInfo.do?rand="+Math.random(),
		"sServerMethod":"post",
		"fnServerParams": function ( aoData ) {
												var qReportType = $('input[name="reportType"]:checked').val();
												var qDateType = $('input[name="dateType"]:checked').val();
												var qSaleType = $('input[name="saleType"]:checked').val();
												var qBeginDate = $("#startDate").val();
												var qEndDate = $("#endDate").val();
												aoData.push( {"name":"qReportType","value": qReportType});
												aoData.push( {"name":"qDateType","value": qDateType});
												aoData.push( {"name":"qSaleType","value": qSaleType});
												aoData.push( {"name":"qBeginDate","value": qBeginDate});
												aoData.push( {"name":"qEndDate","value": qEndDate});
											  },
        "columns": [
    				{ "data": ""},
    				{ "data": "ym"},
    				{ "data": "week"},
    				{ "data": "manfNameCn"},
    				{ "data": "brandNameCn"},
    				{ "data": "submodelNameCn"},
    				{ "data": "segmentParentName"},
    				{ "data": "segmentNameCn"},
    				{ "data": "bodyTypeNameCn"},
    				{ "data": "versionNameCn"},
    				{ "data": "versionShortNameEn"},
    				{ "data": "versionCode"},
    				{ "data": "msrp"},
    				
    				{ "data": "rebatePrice"},
    				{ "data": "rewardAssessment"},
    				{ "data": "promotionalAllowance"},
    				{ "data": "invoicePrice"},
    				{ "data": "price"},
    				{ "data": "modelProfit"},
    				{ "data": "sale"}
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
	
	$( 'input',$('#searchTr th')).on('keyup', function () {	
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
	var	qYm = $.trim($("#qYm").val());
	var	qWeek = $.trim($("#qWeek").val());
	var	qManfNameCn = $.trim($("#qManfNameCn").val());
	var qBrandNameCn = $.trim($("#qBrandNameCn").val());
	var qSubmodelNameCn = $.trim($("#qSubmodelNameCn").val());
	var	qSegmentParentName = $.trim($("#qSegmentParentName").val());
	var	qSegmentNameCn = $.trim($("#qSegmentNameCn").val());
	var	qBodyTypeNameCn = $.trim($("#qBodyTypeNameCn").val());
	var	qVersionNameCn = $.trim($("#qVersionNameCn").val());
	var	qVersionShortNameEn = $.trim($("#qVersionShortNameEn").val());
	var qVersionCode = $.trim($("#qVersionCode").val());
	var	qMsrp = $.trim($("#qMsrp").val());
	var	qRebatePrice = $.trim($("#qRebatePrice").val());
	var	qRewardAssessment = $.trim($("#qRewardAssessment").val());
	var qPromotionalAllowance = $.trim($("#qPromotionalAllowance").val());
	var	qInvoicePrice = $.trim($("#qInvoicePrice").val());
	var	qPrice = $.trim($("#qPrice").val());
	var	qModelProfit = $.trim($("#qModelProfit").val());
	var	qSale = $.trim($("#qSale").val());
	
	var qReportType = $('input[name="reportType"]:checked').val();
	var qDateType = $('input[name="dateType"]:checked').val();
	var qSaleType = $('input[name="saleType"]:checked').val();
	var qBeginDate = $("#startDate").val();
	var qEndDate = $("#endDate").val();
	
//	weekOrMonth = weekOrMonth.substr(0,weekOrMonth.indexOf("<"));
	dataTable.fnClearTable(0); //清空数据
	var param = dataTable.fnSettings().aoServerParams[0];
	param.fn = function ( aoData ) {
		aoData.push( {"name":"qYm","value": qYm});
		aoData.push( {"name":"qWeek","value": qWeek});
		aoData.push( {"name":"qManfNameCn","value": qManfNameCn});
		aoData.push( {"name":"qBrandNameCn","value": qBrandNameCn});
		aoData.push( {"name":"qSubmodelNameCn","value": qSubmodelNameCn});
		aoData.push( {"name":"qSegmentParentName","value": qSegmentParentName});
		aoData.push( {"name":"qSegmentNameCn","value": qSegmentNameCn});
		aoData.push( {"name":"qBodyTypeNameCn","value": qBodyTypeNameCn});
		aoData.push( {"name":"qVersionNameCn","value": qVersionNameCn});
		aoData.push( {"name":"qVersionShortNameEn","value": qVersionShortNameEn});
		aoData.push( {"name":"qVersionCode","value": qVersionCode});
		aoData.push( {"name":"qMsrp","value": qMsrp});
		aoData.push( {"name":"qRebatePrice","value": qRebatePrice});
		aoData.push( {"name":"qRewardAssessment","value": qRewardAssessment});
		aoData.push( {"name":"qPromotionalAllowance","value": qPromotionalAllowance});
		aoData.push( {"name":"qInvoicePrice","value": qInvoicePrice});
		aoData.push( {"name":"qPrice","value": qPrice});
		aoData.push( {"name":"qModelProfit","value": qModelProfit});
		aoData.push( {"name":"qSale","value": qSale});
		
		aoData.push( {"name":"qReportType","value": qReportType});
		aoData.push( {"name":"qDateType","value": qDateType});
		aoData.push( {"name":"qSaleType","value": qSaleType});
		aoData.push( {"name":"qBeginDate","value": qBeginDate});
		aoData.push( {"name":"qEndDate","value": qEndDate});
	};
	dataTable.fnDraw(); //重新加载数据
}

function exportExcel(){
	$("#exportFormId").submit();
}
