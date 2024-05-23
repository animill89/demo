<%--
  Created by IntelliJ IDEA.
  User: Star_Star
  Date: 2024/4/22
  Time: 8:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册信息</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
%>
用户姓名：${param.name}<br />
密码：${param.password}<br />
性别：<c:choose>
    <c:when test="${param.sex==0}">男</c:when>
    <c:when test="${param.sex==1}">女</c:when>
</c:choose><br />
年龄：${param.age}<br />
电话：${param.phone}<br />
兴趣爱好：
<c:forEach var="favorite" items="${paramValues.favorites}">
    <c:out value="${favorite}"/>
</c:forEach>
<br/>
性别：<c:choose>
    <c:when test="${param.role==0}">普通用户</c:when>
    <c:when test="${param.role==1}">管理员</c:when>
</c:choose><br />
</body>
</html>