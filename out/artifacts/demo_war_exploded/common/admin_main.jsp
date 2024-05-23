<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="news.bean.User"%>
<!DOCTYPE html>
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="${basePath }">
    <title>新闻发布系统</title>
 
    <style>
        body
        {
            margin: 0;
            padding: 0;
            overflow: hidden;
            height: 100%;
            max-height: 100%;
        }
 
        #frameTop
        {
            position: absolute;
            top: 0;
            left: 0;
            height: 100px;
            width: 100%;
            overflow: hidden;
            vertical-align: middle;
        }
 
        #frameContentLeft
        {
            position: fixed;
            top: 100px;
            left: 0;
            height: 100%;
            width: 150px;
            overflow: hidden;
            vertical-align: top;
            background-color: #D2E6FA;
        }
 
        #frameContentRight
        {
            position: absolute;
            left: 150px;
            top: 100px;
            height: 100%;
            width: 100%;
            right: 0;
            bottom: 0;
            overflow: hidden;
            background: #fff;
        }
    </style>
</head>
<body>
<% 
User user=(User)session.getAttribute("user");//获取保存在session范围内的用户名

if(user == null){
	String redirectURL = "login.jsp";
    //重定向到登录页面
	out.write("<script>window.parent.location.href='"+redirectURL+"';</script>");
    return;
}%>
	<div>
        <iframe id="frameTop" src="../common/admin_top.jsp"></iframe>
    </div>
 
    <div>
        <iframe id="frameContentLeft" src="../common/admin_left.jsp"></iframe>
        <iframe id="frameContentRight" src="../common/admin_right.jsp" name="right"></iframe>
    </div>
</body>
</html>