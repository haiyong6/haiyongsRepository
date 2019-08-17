$(document).ready(function(){
	//加载标题
	loadTitle(2);
});
function loadTitle(num){//num为1加载全部，2为加载更多
	var path = document.getElementById("path").value;
	var height = $(window).height();
	var width = $(window).width();
	var htmlValue = '<div class="loading_masker" style="height:'+height+'px;width:'+width+'px;position:fixed;top:0px;left:0px;text-align:center;verticle-align:middle;"><img style="margin-top:400px;" src="' + path + '/image/gif/loading.gif"/><div>';
	$("html").append(htmlValue);
	//请求页面数据遍历标题
	var searchData = $("#search").val();
	var loadType = num;
	var pageNum = $("#pageNum").val();
    var url = path + "/blog/getBlogTitleInfo.do?searchData=" + searchData + "&pageNum=" + pageNum + "&loadType=" + loadType;    //这里的“项目访问路径”要改为你自己的路径
	$.ajax({
        url : url,
        contentType: "application/json",
        type : 'post',
        dataType:"json",
        processData:false,
        contentType:false,
        async:true,
        success : function(data){
        	$(".content").html("");
            var blogList = data.blogList;
            var all = data.showAll;//是否已是全部 
            if(true == all){
            	$("#loadMoreSpan").css("display","none");
            	$("#loadAllSpan").css("display","none");
            } else{
            	$("#loadMoreSpan").css("display","inline");
            	$("#loadAllSpan").css("display","inline");
            }
            var html = "";
            if(blogList.length > 0){
            	for(var i = 0; i < blogList.length; i++) {
    				var blog = blogList[i];
    				html += "<h3>";
    	        		html += " <ul>";
    	        			html += " <li>";
    	        				html += "<a target='_blank' href='" + path + "/blog/view.do?url=" + blog.URLNAME + "&blogId=" + blog.BLOG_ID + "'";
    	        				html +=  " style='text-decoration:none;'>";
    	        				html +=  "<span style='color:rgb(6, 85, 136);'>" + blog.BLOG_NAME + "&nbsp;&nbsp;&nbsp;</span>";
    	        				html += "</a>";
    	        			html += " </li>";
    	        			html += "<li style='display:block;font-size:13px;font-style: oblique;font-weight: 300;'><span>所属文集：" + blog.COLLECTION_NAME + "&nbsp;&nbsp;&nbsp;更新时间：" + blog.UPDATE_TIME + "</span></li>";
    	        		html += "</ul>";
            		html += "</h3>";
    			}
            } else{
            	html = "暂无数据.."
            }
           $(".content").html(html);
        },
        error : function(data){
        	alert(data);
        },
        complete:function (){  //在数据加载完成之后，要删除掉 正在加载的dom
            $(".loading_masker").remove();
        }
    });
};

function loadMore(){
	var pageNum = $("#pageNum").val();
	pageNum++;
	$("#pageNum").val(pageNum);
	loadTitle(2);
};

function loadAll(){
	loadTitle(1)
};
function searchData(){
	$("#pageNum").val(1);
	loadTitle(2);
};

