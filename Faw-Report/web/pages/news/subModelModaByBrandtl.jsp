<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
//是否通过父按钮点击选中的标记
var flag = false;
    //设置品牌全选效果
	$(document).on("click","#tabs-brand .selectBrandAll",function(){
		    var modelObj = $(this);
		    flag = true;
		    $(modelObj).closest("td").next().find(".subModelIdInput").each(function(){
		        var subModelObj = $(this);
		        //全选时，把全部没被选中的子车型全部选中，选中的则不操作
		    	if(modelObj.prop("checked")){
		        	if(!subModelObj.prop("checked")){
		    			subModelObj.click();
		        	} 
		    	//取消全选时，把全部被选中的子车型全部取消选中，没被选中的则不操作
		    	}else{
		        	if(subModelObj.prop("checked")){
		    			subModelObj.click();
		        	} 
		    	}
		    });
		    flag = false;
		});
		
		$(document).on("click","#tabs-brand .subModelIdInput",function(e){
		    var checkBox = $(this).closest("td").find(":checkbox");
		    var checkedLength = checkBox.filter(":checked").length;
		    var modelObj = $(this).closest("td").prev().find(".selectBrandAll");
		    if(!flag){
				modelObj.prop("checked", checkBox.length == checkedLength);
		    }
		});
</script>
<div class="letterContainer">
	<div class="form-inline" style="margin-bottom:5px;">
		<ul class="words">
			<c:forEach items="${brandLetterList}" var="brandLetter">
				<li>
					<label rel="word" class="letter" title="${brandLetter.letter}">${brandLetter.letter}</label>
				</li>
			</c:forEach>
		</ul>
		查找：<input type="text" class="input-small locationSearch">
	</div>

	<div class="letterContentContainer" style="height: 250px; overflow: auto; " id="test">

		<table cellpadding="0" cellspacing="0" border="1" width="100%" class="selectorTable" style="margin-bottom: 0px">
			<c:forEach items="${brandLetterList}" var="brandLetter">
				<tr>
					<td colspan="2" class="selectorHeadTd">
						<label rel="findword">${brandLetter.letter}</label>
					</td>
				</tr>
				<c:forEach items="${brandLetter.objList}" var="brand">
					<tr>
					    <td class="selectorTypeTd" style="padding-top: 0px;">
						    <input class="selectBrandAll" type="checkbox" name="autoGroup" style="margin-top: 0px;" value="${brand.brandId }">${brand.brandName}
						</td>
						<td class="selectorContentTd">
							<div class="row-fluid">
								<c:forEach items="${brand.subModelList}" var="subModel">
									<label style="width:120px;padding:0px;margin-left:20px;" class="span3 checkbox subModelIdLabel" letter="${subModel.letter}" ><input  name="subModelIdInputByBrand" type="${inputType == 1?'checkbox':'radio'}" class="subModelIdInput" pooAttributeId="${subModel.pooAttributeId}" objectGroup="${brand.brandId }" value="${subModel.subModelId}">${subModel.subModelName}</label>
								</c:forEach>
							</div>
						</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</table>
	</div>
</div>
		 