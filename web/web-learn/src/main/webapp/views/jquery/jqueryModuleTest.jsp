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
  //对象原意模型 对象原意模型中的所有成员都是共有的
  var objectLiteral={
  	onReady:function(){
  	   $("p").first().hover(objectLiteral.addBackColor); 
  	   $("span").first().on("click",myModulePatt.sayDisPriText);
  	   //添加多个类
  	   $("span").last().text(myModulePatt.disPubText).addClass("testA");
  	   //添加类匿名函数
  	   $("div").addClass(objectLiteral.addClassByIf);
  	},
  	addBackColor:function(){
  	  $(this).toggleClass("test");	
  	},
  	addClassByIf:function(idx,currClass){
  	   var newClass="testA";
  	   	if(currClass.length==0) newClass="test";
  	   	return newClass;
  	   }
  };
  
  //模块模型编程 只有通过return返回的变量还有函数才是共有的  可以被外部所访问
  var myModulePatt=(function(){
  	var disPriText="显示私有的模块模型编程";
  	var disPubText="显示公有的模块模型编程";
  	var sayDisPriText=function(){
  	    console.log(disPriText);
  		$(this).text(disPriText);
  	};
  	return{
  		disPubText:disPubText,
  		sayDisPriText:sayDisPriText
  	};
  })();
  
  $(objectLiteral.onReady);
  
</script>
</head>
<body>
	<p>这是对象原意模型测试段落</p>
	<span>测试模块模型编程</span>
	<hr />
	<span>测试模块模型编程</span>
	<div>测试添加多个类的方法</div>
</body>
</html>
