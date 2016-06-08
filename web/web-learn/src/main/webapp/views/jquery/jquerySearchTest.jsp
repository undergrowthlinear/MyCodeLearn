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
	var saveContent=$("#surPar2");
	//找到父类 parent找到上一级父类  parents找到上一级所有父类
		saveContent.html(saveContent.text()+"<br/>"+$("#childPa").parent().text());
		saveContent.html(saveContent.text()+"<br/>"+$("#childPa").parents().length);
		/* $("#childPa").parents().each(function(idx,ele){
			
		}); */
		//找到最近的父亲
		//alert($("#childPa").closest("p").length);
		//找到子元素
		//find 可以在父类下面多级子类  children只能找下一级的子类
		saveContent.html(saveContent.html()+"<br/>"+$("#par1").find("p").html());
		//找到兄弟
		saveContent.html(saveContent.html()+"<br/>"+$("#par1").next().html()+"<br/>"+$("#par1").prev().length+"<br/>"+$("#par1").nextAll().last().html());
	   //添加css
	   $("#surPar2").css({
	   color:"red",
	   fontSize:"28px"
	   });
	   //data 操作
	   //$("#par2").data("dataKey",{foo:"red"});
	   //alert($("#par2").data("dataKey"));
	   //工具方法
	   //去除字符串头和尾的空格
	  // alert($.trim(" qq ").length);
	  //遍历
	 /*  $.each(["q","w"],function(idx,val){
	  	alert("index:"+idx+" value:"+val);
	  });
	  $.each({name:"q",age:15},function(k,v){
	  	alert("key:"+k+" value:"+v);
	  }); */
	  //判断是否在数组中
	 // alert($.inArray(4,[1,2,3,4]));
	 
	 
	 //attr方法的匹配
	 //获取集合中匹配的第一个元素
	// alert($("img").attr("src"));
	//设置集合中的所有元素
	$("img").attr({
		src:"images/hej.jpg"
	});
	   $("#gi").attr({
	   title:"华尔街",
	   	alt:"qq",
	   	width:"100px;",
	   	height:"100px"
	   });
	   $("#gi").attr("title",function(idx,val){
	   return "修改过的"+val;
	   });
	   
	   
	 //css方法的匹配
	//alert($("#surPar2").css("width"));
	//css获取多个属性
	var html=["显示内容区域的内容为:"];
	 $.each($("#surPar2").css(["width","height","backgroundColor"]),function(prop,val){
	 	html.push(prop+":"+val);
	 });
	 //将上面获取到的多个属性数组以字符串的形式显示在第一个p中
	 $("p").eq($("p").length-2).html(html.join("<br/>"));  
	 
	 //一次单击事件 +=表示在原来基础上加上100
	 $("#gi").one("click",function(){
	 	$(this).css("width","+=100");
	 });
	 
	 //获取每一个单词的底板颜色
	 $("p").last().css("border","1px solid red");
	var words=$("p").last().text().split(" ");
	var text=words.join("</span> <span>");
	$("p").last().html("<span>"+text+"</span>");
	$("span").click(function(idx,ele){
	$("span").css("backgroundColor","");
		$(this).css("backgroundColor","red");
	});
	
	//改变最后一个段落的宽度和高度
	$("p").last().click(function(){
	
		$(this).css({
			width:function(idx,val){
			return parseFloat(val)*1.1;
			},
			height:function(idx,val){
				return parseFloat(val)*1.8;
			}
		});
		});
	
	
	//val的值可以接受一个函数
	$("input").val(function(idx,val){
		return "index:"+idx+" value:"+val;
	});
	//获取到元素属性的集合
	$("div").last().html($("div").last().html()+" ");
	/* alert($("p").map(function(idx,ele){ //ele为dom元素 需要转换为jquery object
	 return $(ele).html();
	}).get().join("<br/>")); */
	
	$("p").last().css("display","none");
	
	//判断一个元素是否隐藏 若是隐藏 则显示
	if($("p").last().is(":hidden")){
		$("p").last().show();
	}
	
	//动画
	//$("#gi:visible").animate({  width: "+=200px"},2000,function(){alert("动画完成");});
	
	//禁用、使能元素
	/* $("input").prop("disabled",true);
	$("div").on("click",function(){
	//alert("ok");
		$("input").prop("disabled",false);
	}); */
	
	//选择复选框 单选框
	$("#cb").prop("checked",true);
	$("#dx").prop("checked",false);
	
	//获取select的value和text
	//alert($("#selId").val()+$("#selId").text());
	
	});
	
	
</script>
</head>
<body>
<div id="par2">
 祖父元素
 <hr/>
<div id="par1">

   父元素
  <p id="childPa">子元素</p>
</div>  
<hr/>
<div id="surPar1">
 父兄弟元素
</div>
</div>
<hr/>
<div id="surPar2">
 祖父兄弟元素
</div>
<img id="gi" src="images/hej.jpg" alt="图片1" />
<!-- <img id="td" src="images/td.jpg" alt="图片2" /> -->
<p></p>
<p>
this is a css param,if you click one, you will find more
</p>
<input type="text" value="测试input" />
<input type="checkbox" id="cb" >选择
<br/>
<input type="radio" id="dx" >单选
<select id="selId">
<option value="1">第一个</option>
</select>
</body>
</html>
