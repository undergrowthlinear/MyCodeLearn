<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String value=(String)request.getParameter("name");
if("张三".equals(value))
	response.getWriter().write("返回数据,数据为"+value);
else
  	response.getWriter().write("无法返回数据");
	

%>
