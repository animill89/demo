<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<title>新闻发布系统</title>
</head>
<body>
	<a href="#" target="right">管理用户信息</a>
	<p>
	<a href="#" target="right">审核新闻</a>
	<p>
</body>
</html>