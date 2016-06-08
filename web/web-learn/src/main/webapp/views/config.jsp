<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%
final String context = request.getContextPath();
final String source = context;
final String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+context+"/";
//folder
final String scripts = source+"/scripts";
final String css = source+"/css";

//js
final String jquery_js = scripts+"/jquery-1.8.1.min.js";
//css
final String css_path = css+"/style.css";


pageContext.setAttribute("source",source);
pageContext.setAttribute("context",context);
pageContext.setAttribute("jquery_js",jquery_js);
%>