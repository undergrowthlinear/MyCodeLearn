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
	//get数据 异步发送请求
		<%-- $.get("<%=basePath%>/views/jquery/jqueryData.jsp",function(data){
			console.log(data);
		}); --%>
		
    
    //ajax请求
     <%-- $.ajax({
    	url:"<%=basePath%>/views/jquery/jqueryData.jsp",
    	data:{name:"张三"},
    	type:"get",
    	dataType:"text",
    	timeout:"2000",
    	success:function(response,xhr,status){
    		console.log("the data is "+response);
    	},
    	error:function(xhr,status){
    		console.log("the request is error.");
    	} ,
    	complete:function(xhr,status){
    		console.log("完成请求");
    	} 
    	});  --%>
    	
    	$("#loginForm").submit(function(event){
    		if($("input[name='username']").val().length==0){
    			 alert("用户名不能为空!!");
    			 return false;
    		}
    		if($("input[name='password']").val().length==0){
    			 alert("密码不能为空!!");
    			 return false;
    		}
    		event.preventDefault(); //阻止表单提交
    		event.stopPropagation(); //阻止表单冒泡
    		 $.ajax({
    			url:"jqueryAuthen",
    			data:$("#loginForm").serialize(),
    			type:"get",
    			dataType:"json",
    			success:function(response,xhr,status){
    	    		console.log("the data is "+response.ret);
    	    	},
    	    	error:function(xhr,status){
    	    		console.log("the request is error."+xhr);
    	    	} ,
    	    	complete:function(xhr,status){
    	    		console.log("完成请求");
    	    	} 
    		});
    	});
    
    		
	});
</script>
  </head>
  
  <body>
  ajax测试界面
  <hr/>
  <form id="loginForm" action="jqueryAuthen">
  用户名:<input type="text" name="username" />
 密码:<input type="text" name="password"> 
 <input type="submit" value="提交"/>
  </form>
</body>
</html>
