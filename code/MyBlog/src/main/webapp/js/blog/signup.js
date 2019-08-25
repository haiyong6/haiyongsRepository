$(document).ready(function(){
	$("#userName").blur(function(){
		var userName = $("#userName").val();
		$("#checkUserName").html("");
		if($.trim(userName).length == 0){
			$("#checkUserName").html("用户名不能为空！");
		}
		//判断用户名有没有重复
		var path = document.getElementById("path").value;
    	var url = path + "/user/checkUserNameByUserName.do?userName=" + userName;
		$.ajax({
	        url : url,
	       // data : {userName:userName},
	        type : 'post',
	        dataType:"json",
	        processData:false,
	        contentType:false,
	        async:true,
	        success : function(data){
	            if(data.flag){
	            	$("#checkUserName").html("用户名已存在！");
	            	return;
	            }
	        },
	        error : function(data){
	        	alert(data);
	        }
	    });
	});
	
	$("#passWord").blur(function(){
		var passWord = $("#passWord").val();
		$("#checkPassWord").html("");
		if($.trim(passWord).length == 0){
			$("#checkPassWord").html("密码不能为空！");
		}
	});
	
	$("#confirmPassWord").blur(function(){
		var confirmPassWord = $("#confirmPassWord").val();
		$("#checkConfirmPassWord").html("");
		if($.trim(confirmPassWord).length == 0){
			$("#checkConfirmPassWord").html("确认密码不能为空！");
		}
	});
});
function submitForm(){
	var form = new FormData(document.getElementById("checkForm"));
    var path = document.getElementById("path").value;
    var url = path + "/user/createUser.do";    //这里的“项目访问路径”要改为你自己的路径
    
    var userName = $("#userName").val();
	$("#checkUserName").html("");
	if($.trim(userName).length == 0){
		$("#checkUserName").html("用户名不能为空！");
		return;
	}
	//判断用户名有没有重复
	$.ajax({
        url : path + "/user/checkUserNameByUserName.do?userName=" + userName,
       // data : {userName:userName},
        type : 'post',
        dataType:"json",
        processData:false,
        contentType:false,
        async:true,
        success : function(data){
            if(data.flag){
            	$("#checkUserName").html("用户名已存在！");
            	return;
            }
        },
        error : function(data){
        	alert(data);
        }
    });
	
	var passWord = $("#passWord").val();
	$("#checkPassWord").html("");
	if($.trim(passWord).length == 0){
		$("#checkPassWord").html("密码不能为空！");
		return;
	}
	
	var confirmPassWord = $("#confirmPassWord").val();
	$("#checkConfirmPassWord").html("");
	if($.trim(confirmPassWord).length == 0){
		$("#checkConfirmPassWord").html("确认密码不能为空！");
		return;
	}
	
	if(passWord != confirmPassWord){
		$("#checkConfirmPassWord").html("");
		$("#checkConfirmPassWord").html("确认密码与密码不一致！");
		return;
	}
    $.ajax({
        url : url,
        data : form,
        type : 'post',
        dataType:"json",
        processData:false,
        contentType:false,
        async:true,
        success : function(data){
            alert(data.info);
            if(data.success){
            	 window.location="/MyBlog";
            }
        },
        error : function(data){
        	alert(data);
        }
    }); 
};