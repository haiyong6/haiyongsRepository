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
	
	$('input[name="priceType"]').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%'	
    });	
	
//	$('table tr').find('th:eq(15)').hide();
//	$('table tr').find('th:eq(17)').hide();
//	$('table tr').find('td:eq(15)').hide();
//	$('table tr').find('td:eq(17)').hide();
    
	var priceTypeVal=$('input[name="priceType"]:checked').val();
	$('#priceTypeId').val(priceTypeVal);
	
    $('.iCheck-helper').on('click',function(){    	
    	var val=$('input[name="priceType"]:checked').val();
    	$('#priceTypeId').val(val);
    	if(val==2){
    		$('table tr').find('th:eq(15)').hide();
    		$('table tr').find('th:eq(17)').hide();
    		$('table tr').find('td:eq(15)').hide();
    		$('table tr').find('td:eq(17)').hide();
    		
    		$('table tr').find('th:eq(20)').show();
    		
    		$('table tr').find('td:eq(20)').show();
    		
    	}
    	if(val==1){
    		$('table tr').find('th:eq(15)').hide();
    		$('table tr').find('th:eq(17)').hide();
    		$('table tr').find('td:eq(15)').hide();
    		$('table tr').find('td:eq(17)').hide();
    		
    		$('table tr').find('th:eq(16)').show();
    		$('table tr').find('th:eq(18)').show();
    		$('table tr').find('td:eq(16)').show();
    		$('table tr').find('td:eq(18)').show();    		
//    		$('.qMinPricesth').show();
//    		$('.qMinPriceth').hide();
    	}
    	if(val==0){
    		$('table tr').find('th:eq(15)').show();
    		$('table tr').find('th:eq(17)').show();
    		$('table tr').find('td:eq(15)').show();
    		$('table tr').find('td:eq(17)').show();
    		
    		$('table tr').find('th:eq(16)').hide();
    		$('table tr').find('th:eq(18)').hide();
    		$('table tr').find('td:eq(16)').hide();
    		$('table tr').find('td:eq(18)').hide();
//    		$('.qMinPricesth').hide();
//    		$('.qMinPriceth').show();
    	}    	
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
	    'bPaginate': false,  //是否分页。
	    "bLengthChange ":true,
	    "bProcessing": true, //当datatable获取数据时候是否显示正在处理提示信息。 
	    "bServerSide": true,
	    "sAjaxSource": ctx+"/city/tp/tpCityInfo.do?rand="+Math.random(),
		"sServerMethod":"post",
		"fnServerParams": function ( aoData ) {
												var qReportType = $('input[name="reportType"]:checked').val();
												var qPriceType = $('input[name="priceType"]:checked').val();
												var qDateType = $('input[name="dateType"]:checked').val();
												var qBeginDate = $("#startDate").val();
												var qEndDate = $("#endDate").val();
												aoData.push( {"name":"qReportType","value": qReportType});
												aoData.push( {"name":"qPriceType","value": qPriceType});
												aoData.push( {"name":"qDateType","value": qDateType});
												aoData.push( {"name":"qBeginDate","value": qBeginDate});
												aoData.push( {"name":"qEndDate","value": qEndDate});
											  },
        "columns": [
    				{ "data": ""},
    				{ "data": "ym"},
    				{ "data": "week"},
    				{ "data": "versionCode"},
    				{ "data": "segmentParentName"},
    				{ "data": "segmentNameCn"},
    				{ "data": "brandNameCn"},
    				{ "data": "manfNameCn"},
    				{ "data": "submodelNameCn"},
    				{ "data": "bodyTypeNameCn"},
    				{ "data": "versionShortNameCn"},
    				{ "data": "launchDate"},
    				{ "data": "emissionsName"},
    				{ "data": "trnsmsNameCn"},
    				{ "data": "msrp"},
    				{ "data": "cityNameCn"},
    				{ "data": "minPrice"},
    				{ "data": "minPrices"},
    				
    				{ "data": "price"},
    				{ "data": "prices"},
    				{ "data":"priceFawvw"}
    				
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
	

//	dataTable.fnDraw(); 
//	$('table tr').find('th:eq(15)').hide();
//	$('table tr').find('th:eq(17)').hide();
//	$('table tr').find('td:eq(15)').hide();
//	$('table tr').find('td:eq(17)').hide();
	
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
	var	qVersionCode = $.trim($("#qVersionCode").val());
	var qSegmentParentName = $.trim($("#qSegmentParentName").val());
	var qSegmentNameCn = $.trim($("#qSegmentNameCn").val());
	var	qBrandNameCn = $.trim($("#qBrandNameCn").val());
	var	qManfNameCn = $.trim($("#qManfNameCn").val());
	var	qSubmodelNameCn = $.trim($("#qSubmodelNameCn").val());
	var qBodyTypeNameCn = $.trim($("#qBodyTypeNameCn").val());
	var	qVersionShortNameCn = $.trim($("#qVersionShortNameCn").val());
	var	qEmissionsName = $.trim($("#qEmissionsName").val());
	var	qTrnsmsNameCn = $.trim($("#qTrnsmsNameCn").val());
	var qMsrp = $.trim($("#qMsrp").val());
	var	qCityNameCn = $.trim($("#qCityNameCn").val());
	var	qMinPrice = $.trim($("#qMinPrice").val());
	var	qMinPrices = $.trim($("#qMinPrices").val());
	var qMinPriceFawvw =$.trim($("#qMinPriceFawvw").val());
	var	qPrice = $.trim($("#qPrice").val());
	var	qPrices = $.trim($("#qPrices").val());
	var qPriceFawvw=$.trim($("#qPriceFawvw").val());
	var qLaunchDate=$.trim($("#qLaunchDate").val());
	
	var qReportType = $('input[name="reportType"]:checked').val();
	var qPriceType = $('input[name="priceType"]:checked').val();
	var qDateType = $('input[name="dateType"]:checked').val();
	var qBeginDate = $("#startDate").val();
	var qEndDate = $("#endDate").val();
	

//	weekOrMonth = weekOrMonth.substr(0,weekOrMonth.indexOf("<"));
	dataTable.fnClearTable(0); //清空数据
	var param = dataTable.fnSettings().aoServerParams[0];
	param.fn = function ( aoData ) {
		aoData.push( {"name":"qYm","value": qYm});
		aoData.push( {"name":"qWeek","value": qWeek});
		aoData.push( {"name":"qVersionCode","value": qVersionCode});
		aoData.push( {"name":"qSegmentParentName","value": qSegmentParentName});
		aoData.push( {"name":"qSegmentNameCn","value": qSegmentNameCn});
		aoData.push( {"name":"qBrandNameCn","value": qBrandNameCn});
		aoData.push( {"name":"qManfNameCn","value": qManfNameCn});
		aoData.push( {"name":"qSubmodelNameCn","value": qSubmodelNameCn});
		aoData.push( {"name":"qBodyTypeNameCn","value": qBodyTypeNameCn});
		aoData.push( {"name":"qVersionShortNameCn","value": qVersionShortNameCn});
		aoData.push( {"name":"qEmissionsName","value": qEmissionsName});
		aoData.push( {"name":"qTrnsmsNameCn","value": qTrnsmsNameCn});
		aoData.push( {"name":"qLaunchDate","value": qLaunchDate});
		aoData.push( {"name":"qMsrp","value": qMsrp});
		aoData.push( {"name":"qCityNameCn","value": qCityNameCn});
		aoData.push( {"name":"qMinPrice","value": qMinPrice});
		aoData.push( {"name":"qMinPrices","value": qMinPrices});
		
		aoData.push( {"name":"qPrice","value": qPrice});
		aoData.push( {"name":"qPrices","value": qPrices});
		aoData.push( {"name":"qPriceFawvw","value": qPriceFawvw});
		aoData.push( {"name":"qReportType","value": qReportType});
		aoData.push( {"name":"qPriceType","value": qPriceType});
		aoData.push( {"name":"qDateType","value": qDateType});
		aoData.push( {"name":"qBeginDate","value": qBeginDate});
		aoData.push( {"name":"qEndDate","value": qEndDate});
		
	};
	dataTable.fnDraw(); //重新加载数据
	
}

function exportExcel(){
	$("#exportFormId").submit();
}