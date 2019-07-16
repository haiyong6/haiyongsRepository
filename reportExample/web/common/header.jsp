<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<style>
#helper .navbar .nav > li > a {
	text-shadow:none; 
	color: #fff;
}
#helper .navbar .nav > li > a:HOVER{
	color:#fff;
	text-decoration: underline;
}
</style>
<script>
</script>
<div class="container-fluid wraper " id="headerDiv" style="padding-left:0px;height: auto;">
	<div class="row-fluid " style="background-color: #3d6699">
		<div class="span2" style="padding-top:2px;padding-left:10px;margin-top:5px;">
            	<img src="${ctx}/themes/bootcss/img/logo.png"  />
			</a>
         </div>
         <div class="span2" style="color: white;margin-top: 12px;font-size: 26px;">&nbsp; 报告库测试环境 </div>
         <div class="span8" style="margin:0px;padding:5px;" id="helper">
         	<div class="pull-right navbar topbar">
                <ul class="nav" style="height:15px;">
                	<li><a style="text-decoration: none">${userDetails.department}-${userDetails.userName}</a></li>
           		</ul>                           
        	</div>
    	</div>
	</div>
</div>
 	
 	
<div id="menuDiv" class="navbar pillswithdropdowns" id="pillswithdropdowns" style="padding:0px;margin:0px">
	<div class="navbar-inner ">
		<div class="container">
	    	<a class="btn btn-navbar " data-toggle="collapse" data-target=".navbar-inverse-collapse">
	        	<span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	         </a>
	        <a class="brand" href="index.php-module=Contacts&action=index.html#" >&nbsp;&nbsp;&nbsp;</a>
	        <div class="nav-collapse collapse navbar-inverse-collapse pillswithdropdowns">
	        	<ul class="nav nav-pills ">
						<li class="dropdown">
				     		<a href="#" class="dropdown-toggle" >成交价<b class="caret"></b></a>
				     		<ul class="dropdown-menu">
								<li class="dropdown parent">
									<a href="${ctx}/city/tp/tpCityMain.do" class="dropdown-toggle">成交价-报表</a>
								</li>
							</ul>
						</li>
						
						<li class="dropdown" style="display:none;">
				     		<a href="#" class="dropdown-toggle" >返点返利<b class="caret"></b></a>
				     		<ul class="dropdown-menu">
								<li class="dropdown parent">
									<a href="${ctx}/profit/vProfitMain.do" class="dropdown-toggle">型号利润-报表</a>
								</li>
							</ul>
						</li>
						<li class="dropdown">
				     		<a href="#" class="dropdown-toggle" >销量<b class="caret"></b></a>
				     		<ul class="dropdown-menu">
								<li class="dropdown parent">
									<a href="${ctx}/sale/saleMain.do" class="dropdown-toggle">销量查询</a>
								</li>
							</ul>
						</li>
						<li class="dropdown" style="display:none;">
				     		<a href="#" class="dropdown-toggle" >新车快讯<b class="caret"></b></a>
				     		<ul class="dropdown-menu">
								<li class="dropdown parent">
									<a href="${ctx}/news/newsCarsMain.do" class="dropdown-toggle">新车快讯-报表</a>
								</li>
							</ul>
						</li>
						
						<li class="dropdown">
				     		<a href="#" class="dropdown-toggle" >后台配置<b class="caret"></b></a>
				     		<ul class="dropdown-menu">
								<li class="dropdown parent">
									<a href="${ctx}/backstageConfig/originalAndCompetitorMain.do" class="dropdown-toggle">本竞品管理</a>
								</li>
								<li class="dropdown parent">
									<a href="${ctx}/backstageConfig/commonGroupMain.do" class="dropdown-toggle">常用对象管理</a>
								</li>
							</ul>
						</li>
						
						<li class="dropdown">
				     		<a href="#" class="dropdown-toggle" >基期TP<b class="caret"></b></a>
				     		<ul class="dropdown-menu">
								<li class="dropdown parent">
									<a href="${ctx}/basePeriodTPConfig/commonGroupMain.do" class="dropdown-toggle">基期TP管理</a>
								</li>
							</ul>
						</li>
						
				 </ul>
	        </div><!-- /.nav-collapse -->
	    </div><!-- /container -->
	</div><!-- /navbar-inner -->
	<ul class="breadcrumb" style="margin-left:16px;margin-bottom:10px" >
		<li class="active">当前位置：</li>
	  	<li class="active"><decorator:title/></li>
	</ul>
</div>