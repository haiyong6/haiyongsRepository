<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>一汽大众报告库</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<LINK rel=stylesheet type=text/css href="css/login.css">
<LINK rel=stylesheet type=text/css href="css/jquery.alerts.css">

<style type="text/css">
* {margin:0;padding:0;}
html {text-align:left;}
a {text-decoration: none;color:#000000;}
img {border:none;margin:0;}
ul,li {list-style-type: none;}
body {color:#000000;margin:0 auto;position:relative;font-size:12px;background:#ffffff url(images/login/bg_login.jpg) center top no-repeat;}
h1,h2,h3,h4,h5,h6,body {font-family:Arial,宋体;}
div,ul,li,span,p,em,strong,dl,dt,dd {overflow:hidden;margin:0;padding:0;}
html body a:hover {text-decoration:underline;color:#000000;}
.left {float:left;}
.right {float:right;}
.main {width:880px;margin:0 auto;position:relative;}


.language {float:left;width:104px;height:87px;margin-left:4px;_margin-left:2px;margin-top:57px;font-family:Arial;font-size:14px;font-weight:bold;}
.language a {float:left;margin-top:1px;width:104px;height:42px;line-height:42px;text-align:center;background:url(images/login/lang_out.jpg)  no-repeat;}
.language .over {float:left;width:104px;height:42px;line-height:42px;text-align:center;background:url(images/login/lang_over.jpg) no-repeat;color:#344793;}
.login {float:left;width:323px;height:260px;margin-left:56px;margin-left:56px;margin-top:22px;overflow:hidden;}
.login span {float:left;}
.helps {width:323px;font-size:14px;text-align:right;}
.text {float:left;width:330px;font-size:12px;text-align:left;}
.text1 {float:left;width:330px;margin-top:24px;font-size:12px;text-align:left;}
.btn {float:left;width:300px;margin-top:24px;font-size:12px;text-align:left;}
.input {float:left;width:185px;height:22px;line-height:22px;background:url(images/login/input.jpg)  no-repeat;border:1px solid #b1b1b1;font-size:12px;font-family:Arial;}
.btnout {margin:0;padding:0px;border:0px;width:86px;height:34px;line-height:34px;background:url(images/login/btn_out.jpg)  no-repeat;font-size:14px;}
.btnover {margin:0;padding:0px;border:0px;width:86px;height:34px;line-height:34px;background:url(images/login/btn_over.jpg)  no-repeat;font-size:14px;}

#logo {float:left;width:360px;height:360px;}
#logo img {float:left;}
#login {float:right;width:520px;height:360px;background:url(images/login/login.png) 0px 0px no-repeat;_background:url(images/login/login.jpg) 0px 0px no-repeat;}

#help li a {color:#999;}
#help {width:400px;height:200px;background:#fff;}
#help li {float:left;height:30px;line-height:30px;text-align:left;font-size:14px;color:#999;border-bottom:1px dashed #e1e1e1;margin-right:30px;}
#help h1 {text-align:center;color:#666;font-size:16px;}
#copyright {width:880px;height:100px;border-top:1px dotted #666666;}
#copyright p {line-height:80px;text-align:center;font-size:14px;font-family:微软雅黑,Arial;color:#666666;}
</style>

</head>
<body>
	<DIV style="HEIGHT: 150px" class=mian></DIV>
	<DIV class=main>
		<DIV id=logo>
			<OBJECT
				codeBase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0"
				classid=clsid:D27CDB6E-AE6D-11cf-96B8-444553540000 width=360 height=60>
				<PARAM NAME="movie" VALUE="images/logo.swf">
				<PARAM NAME="quality" VALUE="high">
				<PARAM NAME="wmode" VALUE="transparent">
				<embed src="images/login/logo.swf" quality="high" 
					   pluginspage="http://www.macromedia.com/go/getflashplayer"
					   type="application/x-shockwave-flash" width="360" height="60">
				</embed>
			</OBJECT>
		</DIV>
		<DIV id=login>
			<DIV class=language>
				<A id=cn class=over href="">中&nbsp;&nbsp;&nbsp;&nbsp;文</A> 
				<A id=en href="">English</A>
			</DIV>
			
			<DIV class=login>
				<FORM name="loginForm" action="${ctx}/auth/login.do" method="post">
					<SPAN class=helps> 
					<B class=left>登录到 AUTO-WAYS报告库</B> 
					<A id=help_link class=right href="">帮助</A>
					</SPAN> 
					   <SPAN class=text1>用户名:</SPAN> <SPAN><INPUT id=username class=input tabIndex=1 type=text name=username value ="ruanrf"></SPAN> 
					   <SPAN class=text1>密 码: </SPAN> <SPAN><INPUT id=password class=input tabIndex=2 type=password name=password value ="rrf"></SPAN> <SPAN class=btn>
					   <P>
					     <INPUT class=btnout onmouseover="this.className='btnover'"tabIndex=3 onmouseout="this.className='btnout'"onmousedown="this.className='btnover'" value="登 录" type=submit>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					     <INPUT class=btnout onmouseover="this.className='btnover'" tabIndex=4 onmouseout="this.className='btnout'" onmousedown="this.className='btnover'" value="重 置" type=reset>
					   </P>
					</SPAN>
				</FORM>
			</DIV>
			
		</DIV>
		
		<DIV id=copyright>
			<P>©&nbsp;&nbsp;2009-2015,威尔森企业管理咨询有限公司 版权所有 <A href="http://www.miibeian.gov.cn/" target=_blank>粤ICP备09206296号</A></P>
		</DIV>
	</DIV>
</BODY>
</body>
</html>
