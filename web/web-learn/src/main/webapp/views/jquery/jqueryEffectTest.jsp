<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../config.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
	
	<link rel="stylesheet" type="text/css" href="<%=css_path%>">
	
	<script type="text/javascript" src="<%=jquery_js%>"></script>
	
	<script type="text/javascript">
	$(function(){
	//alert($("p").first().width()+" 高度为:"+$("p").first().height());
		//隐藏 slow normal fast 500ms
		//hide改变高度和宽度
		$("p").first().hide(function(){
			//alert($("p").first().width()+" 高度为:"+$("p").first().height());
		});
	   // alert($("p").first().width()+" 高度为:"+$("p").first().height());
	   //slide 改变高度  向上滑动隐藏效果
	    $("div").first().slideUp(300,function(){
	    	//alert($(this).width()+" 高度为:"+$(this).height());
	    	});
	    //fade 改变透明度 隐藏元素 通过改变透明度
	    $("a").first().fadeOut(300);
	    //toggle 开关显示或者隐藏
	   // $("p").toggle(1000);
	   //延时段落
	   $("p").first().toggle(1000).delay(1000).show(1000);
	   
	   //停止段落
	  // $("body *").filter(":animated").stop();
	   
	   //使用animate自定义动画
	   $("span").last().animate({
	   	left:"500",
	   	top:"300",
	   	opacity:0.25
	   },1000,function(){
	   	console.log("完成动画");
	   });
	   
	});
</script>
</head>
<body>
 <p>隐藏段落1</p>
 <div>
  隐藏区块1
 </div>
 <a href="http://www.baidu.com">隐藏链接</a>
 <span>隐藏span1</span>
</body>
</html>
