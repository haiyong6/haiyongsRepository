<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath() %>/image/title.ico" rel="icon" type="image/x-icon" />
<title>More learn more wanted!</title>
</head>
<style>
body {
	background: #F3F2EE;font-family:"PT Serif", "Times New Roman", Times, serif;
};
.title{
	
}
</style>
<body>
	<h4><a href="<%=request.getContextPath() %>/blog/blogIndex.do"><span style="color:rgb(6, 85, 136);">返回首页<span></a></h4>
	<h2><div class="title" style="width: 70%;margin-left:15%;color: #1F0909;"><center>${blogName}</center></div></h2>
	<h6>
		<div class="author" style="width: 70%;margin-left:15%;color: #1F0909;
				font-size: smaller;
				border-bottom:1px solid #C5C5C5;padding-bottom:0.8125em;">
			<center>
				作者：${blogAuthorName}&nbsp;&nbsp;上传时间：${createTime}&nbsp;&nbsp;最近更新时间：${updateTime}
			</center>
		</div>
	</h6>
		  <jsp:include page="${blogUrl}"/>
	<footer>
		<center>
		<ul>
			<li style="display: block;"><span>不久会有留言功能，还在开发中，敬请期待......</span></li>
			<li style="display: block;"><span>如果文章对你有帮助，也可自愿付费支持一下，请瓶水喝啦O(∩_∩)O</span></li>
			<li style="display: block;">支付宝：</li>
			<li style="display: block;"><img alt="AlipayCollection" src="<%=request.getContextPath() %>/image/alipayCollection.png"></li>
		</ul>
		</center>
		
	</footer>
</body>
</html>