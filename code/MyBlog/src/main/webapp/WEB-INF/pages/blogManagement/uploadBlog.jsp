<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload My Blog</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.4.1/jquery-3.4.1.js"> </script>
</head>
<script type="text/javascript">
$(document).ready(function(){
	  $("#uploadButton").click(function(){
		    var form = new FormData(document.getElementById("uploadForm"));
		    for(var i = 0; i < form.length; i++){
		    	console.log(form[i]);
		    }
		    var path = document.getElementById("path").value;
		    var url = path + "/upload/uploadBlog.do";    //这里的“项目访问路径”要改为你自己的路径
		    $.ajax({
		        url : url,
		        data : form,
		        type : 'post',
		        dataType:"json",
		        processData:false,
		        contentType:false,
		        async:true,
		        success : function(data){
		            alert(data.data);
		            window.location.reload();
		        },
		        error : function(data){
		        	alert(data);
		        }
		    }); 
	  });
	  
	  
	});
	
function backToLogin(){
	window.location="/MyBlog/user/logout.do";
};
function submitVersionLog(){
	var versionTag = $("#versionTag").val();
	var versionDescribe = $("#versionDescribe").val();
	if(versionTag == undefined || $.trim(versionTag) == "" || $.trim(versionTag) == null){
		alert('请填写正确的版本号！');
		return
	} 
	if(versionDescribe == undefined || $.trim(versionDescribe) == "" || $.trim(versionDescribe) == null){
		alert('请填写版本描述！');
		return
	}
	var path = document.getElementById("path").value;
	var url = path + "/blog/manager/createVersionLog.do?versionTag=" + versionTag + "&versionDescribe=" + versionDescribe;
	$.ajax({
        url : url,
        type : 'post',
        dataType:"json",
        processData:false,
        contentType:false,
        async:true,
        success : function(data){
            alert(data.info);
            //window.location.reload();
        },
        error : function(data){
        	alert(data);
        }
    });
};
</script>
<body>
	<form id="uploadForm"  enctype="multipart/form-data" method="post">
		<!-- 标题: <input type="text" name="blogName" value=''/> -->
		所属文集: <select name="collectionId">
        			<option >请选择..</option>
        			<c:forEach items="${collectionList}" var="collection">
        			<option value="${collection.COLLECTION_ID}">${collection.COLLECTION_NAME}</option>
        			</c:forEach>
        		</select>
        选择文件:<input  type="file" name="file" width="120px">  
              <button id="uploadButton" type="button">上传</button>
	</form>
	
	<input id="path" type="hidden" value='<%=request.getContextPath() %>'>
	<h3>在线人数统计：${Count}</h3>
	<h3><button id="backSystem" onclick="backToLogin()">退出</button></h3>
	<h3>
		发布版本日志：<input type="text" id="versionTag" placeholder="请输入版本号..." >
			<input type="text" placeholder="请输入版本描述..." id="versionDescribe" style="width:20%;height:10%"> 
			<button onclick="submitVersionLog()">发布</button>
	</h3>
	
</body>
</html>