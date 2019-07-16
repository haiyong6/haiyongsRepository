function showLoading(containerId){
	$("#"+containerId).html('<div class="text-center"></div>');
}

//合并行
//$('.tbspan').rowspan(0);
//$('.tbspan').rowspan(1);
//$('.tbspan').colspan(0);
jQuery.fn.rowspan = function(colIdx) {
	return this.each(function() {
         var that;
         $('tr', this).each(function(row) {
             var thisRow = $('td:eq(' + colIdx + '),th:eq(' + colIdx + ')', this);
             if ((that != null) && ($(thisRow).html() == $(that).html())) {
                 rowspan = $(that).attr("rowSpan");
                 if (rowspan == undefined) {
                     $(that).attr("rowSpan", 1);
                     rowspan = $(that).attr("rowSpan");
                 }
                 rowspan = Number(rowspan) + 1;
                 $(that).attr("rowSpan", rowspan);
                 $(thisRow).remove(); ////$(thisRow).hide();  
             } else {
                 that = thisRow;
             }
             that = (that == null) ? thisRow : that;
         });
     });
 }



 ////當指定欄位(colDepend)值相同時，才合併欄位(colIdx)  

 jQuery.fn.rowspan = function(colIdx, colDepend) {
     return this.each(function() {
         var that;
         var depend;
         $('tr', this).each(function(row) {
             var thisRow = $('td:eq(' + colIdx + '),th:eq(' + colIdx + ')', this);
             var dependCol = $('td:eq(' + colDepend + '),th:eq(' + colDepend + ')', this);
             if ((that != null) && (depend != null) && ($(thisRow).html() == $(that).html()) && ($(depend).html() == $(dependCol).html())) {
                 rowspan = $(that).attr("rowSpan");
                 if (rowspan == undefined) {
                     $(that).attr("rowSpan", 1);
                     rowspan = $(that).attr("rowSpan");
                 }
                 rowspan = Number(rowspan) + 1;
                 $(that).attr("rowSpan", rowspan);
                 $(thisRow).remove(); ////$(thisRow).hide();  
             } else {
                 that = thisRow;
                 depend = dependCol;
             }
             that = (that == null) ? thisRow : that;
             depend = (depend == null) ? dependCol : depend;
         });
     });
 }



 ////合併左右欄位  

jQuery.fn.colspan = function(rowIdx) {
	return this.each(function() {
         var that;
         $('tr', this).filter(":eq(" + rowIdx + ")").each(function(row) {
             $(this).find('th,td').each(function(col) {
                 if ((that != null) && ($(this).html() == $(that).html())) {
                     colspan = $(that).attr("colSpan");
                     if (colspan == undefined) {
                         $(that).attr("colSpan", 1);
                         colspan = $(that).attr("colSpan");
                     }
                     colspan = Number(colspan) + 1;
                     $(that).attr("colSpan", colspan);
                     $(this).remove();
                 } else {
                     that = this;
                 }
                 that = (that == null) ? this : that;
             });
         });
     });
 };
 
	/**
	 * 格式化千分号
	 */
window.formatData = function(v)
{
	if(v && "-" != v) 
	{
		v = parseFloat(v).toFixed(0);
		if(4 <= v.length)
		{
			var re=/(\d{1,3})(?=(\d{3})+(?:$|\.))/g;   
			v = v.replace(re,"$1,");
		}
	}
	return v;
};

/**
 * 添加单位，如果小于0，则是红色
 * params
 * v:数值
 * isColor：是否添加颜色 true,false
 * isAddPercentL:是否添加百分号 true false
 */
window.addUnit = function(v,isColor,isAddPercent)
{
	if(!v || "-" == v) return "-";
	
	//如果是添加百分号，则保留一位小数
	if(isAddPercent) v = parseFloat(v).toFixed(1);
	else v = parseFloat(v).toFixed(0);
	
	//添加颜色判断
	if(isColor)
	{
		if(0 > v)//大于0
		{
			if(isAddPercent) v = v + "%";
			else v = window.formatData(v);
			return "<span style='color:#E63110'>" + v+ "</span>";
		}
		else//小于0
		{
			if(isAddPercent) v = v + "%";
			else v = window.formatData(v);
			return "<span style='color:#009C0E'>" + v+ "</span>";
		}
	}
	else
	{
		if(isAddPercent) return v+"%";
		else return window.formatData(v);
	}
};


	$(document).on('click',".letterContainer .words label[rel='word']",function(){
//	 $(".letterContainer .words").find('label[rel="word"]').on('click',function(){
			var wordText = $(this).text();
			var container =  $(this).parents('.letterContainer').find('.letterContentContainer');
		  	var scrollTo = $(this).parents('.letterContainer').find('.letterContentContainer').find('label[rel="findword"]').filter(function(){
				return $.trim($(this).text()) == $.trim(wordText);
			}).parent().parent();
		  	console.log(scrollTo);
			if(scrollTo && scrollTo.offset())
				container.scrollTop(scrollTo.offset().top-container.offset().top+container.scrollTop());
		});
	
	
	$(document).on('keyup',".letterContainer .locationSearch",function(){
//	 $(".letterContainer").find('.locationSearch').on('keyup',function(){
		 
		 	var wordText = $.trim($(this).val().toUpperCase());
		 	var container =  $(this).parents('.letterContainer').find('.letterContentContainer');
		 	
		 	
		 	$(this).parents('.letterContainer').find('.letterContentContainer').find('.subModelIdLabel').each(function(){
		 		$(this).removeClass("label label-info");
		 	});
			
		 	if(wordText != ""){
			  	var scrollToAll = $(this).parents('.letterContainer').find('.letterContentContainer').find('.subModelIdLabel').filter(function(){
			  		var text = $.trim($(this).text()).toUpperCase();
			  		var letter = $.trim($(this).attr("letter")).toUpperCase();
			  		if(text.indexOf(wordText) != -1 || letter == wordText){
			  			$(this).addClass("label label-info");
			  			return true;
			  		}else{
			  			return false;
			  		}			
				});
				if(scrollToAll.length>0){
					var scrollTo = scrollToAll.get(0);
					scrollTo = $(scrollTo);//.parent();
					if(scrollTo)			
						container.scrollTop(scrollTo.offset().top-container.offset().top+container.scrollTop());
				}
			}else{
				container.scrollTop(0);
			}
		});
	
	$(document).on('click',".subModelModalContainer .pooAttributeIdInput",function(event){	
		var pooAttributeIdArr = [];
		$(".subModelModalContainer").find('.pooAttributeIdInput').each(function(){
			if($(this).prop("checked")){		
				pooAttributeIdArr[pooAttributeIdArr.length] = $(this).val();
			}
		});	
		$(this).parents(".subModelModalContainer").find(".subModelIdInput").each(function(){
			var flag = false;
			for(var i=0;i<pooAttributeIdArr.length;i++){
				if(pooAttributeIdArr[i] == $(this).attr("pooAttributeId")){
					flag = true;
					break;
				}
			}
			if(flag){
				$(this).parent().show();
			}else{
				$(this).parent().hide();
			}
		});
	});
	
	$(document).on('click',".subModelModalContainer2 .pooAttributeIdInput",function(event){	
		var pooAttributeIdArr = [];
		$(".subModelModalContainer2").find('.pooAttributeIdInput').each(function(){
			if($(this).prop("checked")){		
				pooAttributeIdArr[pooAttributeIdArr.length] = $(this).val();
			}
		});	
		$(this).parents(".subModelModalContainer2").find(".subModelIdInput").each(function(){
			var flag = false;
			for(var i=0;i<pooAttributeIdArr.length;i++){
				if(pooAttributeIdArr[i] == $(this).attr("pooAttributeId")){
					flag = true;
					break;
				}
			}
			if(flag){
				$(this).parent().show();
			}else{
				$(this).parent().hide();
			}
		});
	});
