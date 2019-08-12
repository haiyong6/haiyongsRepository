$(document).ready(function(){
	//加载标题
	loadTitle();
});
function loadTitle(){
	//请求页面数据遍历标题
	var searchData = $("#search").val();
	var path = document.getElementById("path").value;
    var url = path + "/blog/getBlogTitleInfo.do?searchData=" + searchData;    //这里的“项目访问路径”要改为你自己的路径
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
        }
    });
};

