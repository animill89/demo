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
<%@include file="checkUser.jsp"%>
	
	<div style="font-size: 30px;text-align: center;">
	新闻发布系统
	</div>
	<div style="font-size: 30px;text-align: center;">
	您好！[${user.getName()}]欢迎您访问！
	<a href="http://localhost:8080/demo_war_exploded/login/login.jsp" target="_top">[退出]</a>
<%--		<a href="#" onclick="window.open('http://localhost:8080/demo_war_exploded/login/login.jsp','_blank');window.close;">退出</a>--%>
	</div>
</body>
</html>