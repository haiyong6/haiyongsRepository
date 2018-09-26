<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<div class="resultShowContainer">
		<div class="resultShowContent" style="background-color:#fff;height:50px;overflow: auto;" id="selectorResultContainerBySubModel" >
			
		</div>
	</div>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="#tabs-brand" data-toggle="tab" value="3" onclick="showSubModel('3','0')" >品牌</a>
		</li>
		<li>
			<a href="#tabs-manf" data-toggle="tab" value="4" onclick="showSubModel('4', '0')" >厂商</a>
		</li>
		
		<li class="selectorBodyType">
			<label style="margin-bottom:0px"><input type="checkbox" checked="checked" value="2" class="pooAttributeIdInput">合资</label>
			<label style="margin-bottom:0px"><input type="checkbox" checked="checked" value="1" class="pooAttributeIdInput">进口</label>
			<label style="margin-bottom:0px"><input type="checkbox" checked="checked" value="3"  class="pooAttributeIdInput">自主</label>
		</li>
	</ul>
	
	<div class="tab-content">
		<!-- 品牌——开始 -->
		<div class="tab-pane active" id="tabs-brand" style="height:300px;overflow: auto;">
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
									      <input type="hidden" value="${brand.brandId }"/>${brand.brandName}
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
		</div>
		<!-- 品牌——结束 -->
		<!-- 厂商——开始 -->
		<div class="tab-pane" id="tabs-manf" style="height:300px;overflow: auto;">
			
		</div>
		<!-- 厂商——结束 -->
	</div>
</div>