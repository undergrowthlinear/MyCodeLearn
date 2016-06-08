<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../config.jsp"%>
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
	//hover事件 提供一个函数 则mouseenter和mouseleave都调用这个函数
	     $("p").first().hover(function(){
	    	$(this).toggleClass("test");
	    }); 
	//事件委托给document  新添加的元素也能够响应相应的事件
	 $(document).on("click","span",function(){
		console.log($(this).text());
	});  
	//新建一个span 添加到第一个div中  因为上面span的click事件委托给了document 所以这个添加的span也具有click响应事件
	$("<span>最后一个span</span>").appendTo($("div").first());
	
	//显示事件相关的内容   传递事件数据给事件处理器
	$("div").last().click({name:"qq"},function(eventObject){
		var $th=$(this);
		//显示事件的详细信息
		$th.html($th.text()+"<br/>事件类型为:"+eventObject.type+"<br/>pageX,pageY:"+eventObject.pageX
		+","+eventObject.pageY
		+"<br/>数据为:"+eventObject.data.name+"<br/>目标dom的内容为:"+$(eventObject.target).text()
		+"<br/>时间为:"+eventObject.timeStamp+"<br/>命名空间为:"+eventObject.namespace
		+"<br/>按钮为:"+eventObject.which+"<br/>所有事件为:"+eventObject);
		
		console.dir(eventObject);
	});
	//on绑定多个事件
	$("p").first().on("click focus",function(){
		console.log($(this).text());
	});
	
	
	//取消a标签的链接
	/* $("a").last().click(function(eventObject){
		eventObject.preventDefault();
		alert($(this).text());
	}); */
	
	var pText=$("p").last().text();
	window.console.log(pText);
	
	//on绑定事件的另一种写法
	$("a").last().on({
		mouseenter:function(){
			console.log("a标签的悬停");
		},
		mouseleave:function(){
			console.log("a标签的离开");
		}
	});
	
	
	//事件委托
	/* $("ul").on("mouseenter","li",function(){
		//alert($(this).text());
		$("a").last().simulate("click");
		
	}); */
	
	
	//
	
	});

</script>
</head>
<body>
	<p>这是事件测试代码</p>
	<span>事件一</span>
	<span>事件二</span>
	<div></div>
	<div>显示事件属性</div>
	<ul>
		<li><a href="http://www.baidu.com">取消链接</a></li>
		<li>测试li</li>
	</ul>

</body>
</html>
