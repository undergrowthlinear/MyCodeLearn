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
	
	
	<script type="text/javascript" src="<%=jquery_js%>"></script>
	<script type="text/javascript">
		$(function(){
		//绑定鼠标进入添加样式
			$("p").on("mouseenter",function(){
				$(this).addClass("test");
			});
			//绑定鼠标离开移除样式
			$("p").on("mouseleave",function(){
				$(this).removeClass("test");
			});
			//移动元素
			//移动到元素之后
			$("p").insertAfter($("#content"));
			//移动到元素之前
			$("#spanCon").insertBefore($("#content"));
			//移动到内容之中
			$("#spanMov").appendTo($("#content"));
			//复制元素
			$("#spanCon").clone().appendTo($("#content"));
			//清除内容
			//$("#content").empty();
			$("<p>插入的内容</p>").insertAfter($("#content"));
			
			//新建一个a标签 并添加到id为content的元素之后
			$("<a/>",{
				href:"http://www.baidu.com",
				html:"插入的超链接",
				"class":"test"
			}).insertAfter($("#content"));
			
			//数组操作
			//重复添加多个元素
			var myItems=[];
		    for(var i=0;i<6;i++)
		    {
		     myItems.push("<li>这是第"+i+"个</li>");
		    }
		    $("#testUl").append(myItems.join(""));
		    
		    //属性操作  匿名函数用于返回a标签的href的新值
		    $("a").attr("href",function(index,href){
		    	return "http://www.google.com";
		    });
		    //获取到jquery对象的指定位置的元素 eq获取到jquery object 
		    //get 获取到dom element
		    //每一个jquery object都是唯一的
		     console.log($("div").eq(0)==$("div").eq(0));
		    console.log($("div").get(0)==$("div").get(0)); 
		    
		   /*  $("div").eq(0).html($("span").eq(0).html()); */
		   
		});
	</script>
  </head>
  
  <body>
  <span id="spanMov">this is a span,move to content.</span>
  <span id="spanCon">this  is span.</span>
    <p>This is my JSP page. </p>
    <hr/>
    <div id="content" style="border:1px solid red">
      我是内容选择区
    </div>
    <ul id="testUl">
    </ul>
  </body>
</html>
