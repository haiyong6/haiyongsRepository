<%@ page language="java" errorPage="/error.jsp"  pageEncoding="utf-8"%>
<div id="commonGroupSortPanel" style="width: 500px;height: 450px; margin-left: -250px;" aria-hidden="true" class="modal hide subModelModalContainer" tabindex="-1" role="dialog">
   <div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 id="headTitle">常用对象管理-设置排序</h4>
   </div>
   <div class="modal-body">
      <select id="commonGroupName" onchange="showSortDiv()">
      </select>
      </br>
      <div class="groupSortDiv hide">
        <form action="${ctx}/backstageConfig/updateCommonGroupVersionSort.do" id="sortForm" class="form-horizontal" 
           style="margin-top:2px;display: inline;" method="post">
        </form>
        <button class="btn btn-small btn-primary" style="margin-left: 200px;margin-top: 10px;" onclick="updateSort()">修改排序</button>
      </div>
   </div>
</div>
<script language="javascript">
/**显示分组目前排序情况**/
function showSortDiv() {
    var selectGroup = $("#commonGroupName option:selected").val();
    var div = $(".groupSortDiv");
    var form = $("#sortForm");
    var strHTML = "";
    var bp = null;
    var bpHTML = "";
    strHTML += '<table><thead><tr>';
    strHTML += '<th>分组型号信息</th><th>目前排序</th><th>设定排序</th>';
    strHTML += '</tr></thead><tbody>';
    $.each(groupList, function(i, item) {
        if(groupList[i].groupId == selectGroup) {
            bp = groupList[i];
            strHTML += '<tr><td>';
	        strHTML += '<input type="text" style="width: 320px;" readonly value="' + groupList[i].versionName + '"/></td>';
	        strHTML += '&nbsp;&nbsp;<td><input type="text" style="width: 50px;" readonly value="' + groupList[i].versionSort + '"/></td>';
	        strHTML += '&nbsp;&nbsp;<td><input type="text" style="width: 50px;" class="versionSort" ';
	        strHTML += 'versionValue="' + groupList[i].groupId + ',' + groupList[i].versionId + '" value="' + groupList[i].versionSort + '" /></td>';
	        strHTML += '</tr>';
        }
    });
    strHTML += '<tr><td><input type="hidden" id="versionSort" name="versionSort" /></td></tr>';
    strHTML += '</tbody></table>';
    
    if(null != bp) {
		bpHTML += '<table><thead><tr>';
	    bpHTML += '<th>本品型号信息</th><th>目前排序</th><th>设定排序</th>';
	    bpHTML += '</tr></thead><tbody>';
	    bpHTML += '<tr><td>';
		bpHTML += '<input type="text" style="width: 160px;" readonly value="' + bp.groupName + '" /></td>';
		bpHTML += '&nbsp;&nbsp;<td><input type="text" style="width: 60px;" readonly value="' + bp.groupSort + '"/></td>';
		bpHTML += '&nbsp;&nbsp;<td><input type="text" style="width: 60px;" class="groupSort" ';
		bpHTML += 'groupValue="' + bp.groupId + '" value="' + bp.groupSort + '" /></td></tr>';
		bpHTML += '<tr><td><input type="hidden" id="groupSort" name="groupSort" /></td></tr>';
	    bpHTML += '</tbody></table>';
    }
    form.html(bpHTML + strHTML);
    div.css("display", "inline");
}

/**修改排序**/
function updateSort() {
    var groupStr = "";
    var versionStr = "";
    $("#sortForm").find(".groupSort").each(function() {
        groupStr = $(this).attr("groupValue") + "," + $(this).val();
    });
    $("#sortForm").find(".versionSort").each(function() {
        var versionValue = $(this).attr("versionValue") + "," + $(this).val();
        versionStr += versionValue + "||";
    });
    $("#groupSort").val(groupStr);
    $("#versionSort").val(versionStr);
    $("#sortForm").submit();
}
</script>