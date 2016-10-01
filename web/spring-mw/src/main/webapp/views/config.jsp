<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%
final String context = request.getContextPath();
final String source = context;
final String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+context+"/";
//folder
final String scripts = source+"/scripts";
final String css = source+"/css";
final String bootstrap = "/bootstrap";
final String table = "/table";

//js
final String jqueryJs = scripts+"/jquery.min.js";
final String bootstrapJs = scripts+bootstrap+"/bootstrap.min.js";
final String tableJs = scripts+table+"/bootstrap-table.min.js";
final String tableJsLocal = scripts+table+"/bootstrap-table-zh-CN.min.js";
//css
final String bootstrapCss = css+bootstrap+"/bootstrap.min.css";
final String tableCss = css+table+"/bootstrap-table.css";


%>