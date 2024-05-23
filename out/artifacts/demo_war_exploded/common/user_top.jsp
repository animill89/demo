<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="news.bean.User"%>
<!DOCTYPE html>
<html>
<head>
<title>新闻发布系统</title>
</head>
<body>
<%@include file="checkUser.jsp"%>
	
	<div style="font-size: 40px;text-align: center;">
	新闻发布系统
	</div>
	<div style="font-size: 40px;text-align: center;">
		您好！[${user.getName()} ]欢迎您访问！
	<a href="http://localhost:8080/demo_war_exploded/login/login.jsp" target="_top">[退出]</a>
	</div>
</body>
</html>