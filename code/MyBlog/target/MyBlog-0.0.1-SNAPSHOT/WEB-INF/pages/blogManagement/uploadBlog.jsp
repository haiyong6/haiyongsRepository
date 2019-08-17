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
</body>
</html>