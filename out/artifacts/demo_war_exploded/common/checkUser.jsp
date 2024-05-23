<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/4/24
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="news.bean.User"%>
<%
    User user=(User)session.getAttribute("user");//获取保存在session范围内的用户名

    if(user == null){
        String redirectURL = request.getContextPath()+"/login/login.jsp";

        //重定向到登录页面
        out.write("<script>window.parent.location.href='"+redirectURL+"';</script>");
        return;
    }
%>