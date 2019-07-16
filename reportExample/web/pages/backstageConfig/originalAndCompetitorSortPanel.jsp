<%@ page language="java" errorPage="/error.jsp"  pageEncoding="utf-8"%>
<div id="originalAndCompetitorSortPanel" style="width: 500px;height: 450px; margin-left: -250px;" aria-hidden="true" class="modal hide subModelModalContainer" tabindex="-1" role="dialog">
   <div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 id="headTitle">本竞品管理-设置排序</h4>
   </div>
   <div class="modal-body">
      <select id="bjpName" onchange="showSortDiv()">
      </select>
      </br>
      <div class="groupSortDiv hide">
        <form action="${ctx}/backstageConfig/updateOriginalAndCompetitorSubModelSort.do" id="sortForm" class="form-horizontal" 
             style="margin-top:2px;display: inline;" method="post">
        </form>
        <button class="btn btn-small btn-primary" style="margin-left: 200px;margin-top: 10px;" onclick="updateSort()">修改排序</button>
      </div>
   </div>
</div>
<script language="javascript">
/**显示分组目前排序情况**/
function showSortDiv() {
    var selectId = $("#bjpName option:selected").val();
    var div = $(".groupSortDiv");
    var form = $("#sortForm");
    var strHTML = "";
    var bjp = null;
    var bpHTML = "";
    strHTML += '<table><thead><tr>';
    strHTML += '<th>竞品车型信息</th><th>目前排序</th><th>设定排序</th>';
    strHTML += '</tr></thead><tbody>';
    $.each(bjpList, function(i, item) {
        if(bjpList[i].bpSubModelId == selectId) {
            bjp = bjpList[i];
	        strHTML += '<tr>';
		    strHTML += '<td><input type="text" style="width: 160px;" readonly value="' + bjpList[i].jpSubModelName + '"/></td>';
		    strHTML += '&nbsp;&nbsp;<td><input type="text" style="width: 60px;" readonly value="' + bjpList[i].jpSubModelSort + '"/></td>';
		    strHTML += '&nbsp;&nbsp;<td><input type="text" style="width: 60px;" class="jpSort" ';
		    strHTML += 'jpValue="' + bjpList[i].bpSubModelId + ',' + bjpList[i].jpSubModelId + '" value="' + bjpList[i].jpSubModelSort + '" /></td>';
            strHTML += '</tr>';
        }
        
    });
    strHTML += '<tr><td><input type="hidden" id="jpSort" name="jpSort" /></td></tr>';
    strHTML += '</tbody></table>';
    
    if(null != bjp) {
		bpHTML += '<table><thead><tr>';
	    bpHTML += '<th>本品车型信息</th><th>目前排序</th><th>设定排序</th>';
	    bpHTML += '</tr></thead><tbody>';
	    bpHTML += '<tr><td>';
		bpHTML += '<input type="text" style="width: 160px;" readonly value="' + bjp.bpSubModelName + '"/></td>';
		bpHTML += '&nbsp;&nbsp;<td><input type="text" style="width: 60px;" readonly value="' + bjp.bpSubModelSort + '"/></td>';
		bpHTML += '&nbsp;&nbsp;<td><input type="text" style="width: 60px;" class="bpSort" ';
		bpHTML += 'bpValue="' + bjp.bpSubModelId + ',' + bjp.bpSubModelSort + '" value="' + bjp.bpSubModelSort + '" /></td></tr>';
		bpHTML += '<tr><td><input type="hidden" id="bpSort" name="bpSort" /></td></tr>';
	    bpHTML += '</tbody></table>';
    }
    form.html(bpHTML + strHTML);
    div.css("display", "inline");
}

/**修改排序**/
function updateSort() {
    var bpStr = "";
    var jpStr = "";
    $("#sortForm").find(".bpSort").each(function() {
        bpStr = $(this).attr("bpValue") + "," + $(this).val();
    });
    $("#sortForm").find(".jpSort").each(function() {
        var jpValue = $(this).attr("jpValue") + "," + $(this).val();
        jpStr += jpValue + "||";
    });
    $("#bpSort").val(bpStr);
    $("#jpSort").val(jpStr);
    $("#sortForm").submit();
}
</script>