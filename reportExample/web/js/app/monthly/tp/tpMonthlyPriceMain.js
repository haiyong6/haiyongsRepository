var dataTable = null;
$(document).ready(function(){
	
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
	    "sAjaxSource": ctx+"/monthly/tp/findTpMonthlyPriceInfo.do?rand="+Math.random(),
		"sServerMethod":"post",
		"fnServerParams": function ( aoData ) {},
        "columns": [
    				{ "data": ""},
    				{ "data": "year"},
    				{ "data": "month"},
    				{ "data": "price"},
                    { "data": "versioncode"}
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
	    "aLengthMenu":[[10,25,50],[10,25,50]]  //分页数选择     
    });
	
	$('#searchTr th').each( function () {
		var title = $('#searchTr th').eq( $(this).index() ).text();		 
		var colName = $(this).attr("colName");
		if(colName){
			$(this).html( '<input name="'+colName+'" style="width:100%" type="text" id="'+colName+'" placeholder="'+title+'" colName='+colName+' />' );
		}
	} );
	
	$( 'input',$('#searchTr th')).on('keyup change', function () {	
		var val = $(this).val();
		search();
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
	var	qYear = $.trim($("#qYear").val());
	var	qMonth = $.trim($("#qMonth").val());
	var	qPrice = $.trim($("#qPrice").val());
	var	qVersioncode = $.trim($("#qVersioncode").val());
	
	dataTable.fnClearTable(0); //清空数据
	var param = dataTable.fnSettings().aoServerParams[0];
	param.fn = function ( aoData ) {
		aoData.push( {"name":"qYear","value": qYear});
		aoData.push( {"name":"qMonth","value": qMonth});
		aoData.push( {"name":"qPrice","value": qPrice});
		aoData.push( {"name":"qVersioncode","value": qVersioncode});
	};
	dataTable.fnDraw(); //重新加载数据
}

function getDatatablePage(exp1, exp2) {  //整除
    var n1 = Math.round(exp1); //四舍五入   
    var n2 = Math.round(exp2); //四舍五入  

    var rslt = n1 / n2; //除  
    if (rslt >= 0) {
        rslt = Math.floor(rslt); //返回小于等于原rslt的最大整数。   
    }
    else {
        rslt = Math.ceil(rslt); //返回大于等于原rslt的最小整数。   
    }
    return rslt;
}
 
/**
 * 正整数正则表达式
 * @param {Object} str
 */
function positiveInteger(str){
	var s = /^[0-9]*$/;
	return s.test(str);
}