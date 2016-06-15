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

<link rel="stylesheet" type="text/css" href="<%=css_path%>">

<script type="text/javascript" src="<%=jquery_js%>"></script>

<script type="text/javascript">
	$(function() {
		$("#nameForm").submit(function(event) {
			if ($("input[name='username']").val().length == 0) {
				alert("用户名不能为空!!");
				return false;
			}
			event.preventDefault(); //阻止表单提交
			event.stopPropagation(); //阻止表单冒泡
			$.ajax({
				url : "redisName",
				data : $("#nameForm").serialize(),
				type : "post",
				dataType : "json",
				success : function(response, xhr, status) {
					console.log("the data is " + response.ret);
					$("#hint").addClass("hint");
					$("#hint").text(response.msg);
				},
				error : function(xhr, status) {
					console.log("the request is error." + xhr);
				},
				complete : function(xhr, status) {
					console.log("完成请求");
				}
			});
		});
	});
</script>
</head>

<body>
	<span id="hint"></span>
	<h1>输入姓名:</h1>
	<form id="nameForm" action="redisName">
		姓名:<input type="text" name="username" /> <br /> <input type="submit"
			value="提交" />
	</form>
</body>
</html>
