<%--
  Created by IntelliJ IDEA.
  User: Star_Star
  Date: 2024/4/22
  Time: 8:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <meta name="author" content="刘氏新闻网">
    <link rel="stylesheet" href="../css/task.css" />
</head>
<body>
<div class="imges"><img src="../img/task.png" class="task">
    <div align="center" class="text">
        <p class="ts">EnjoyWeb</p>
        <form name="form1" method="post" action="../servlet/LoginServlet">
            <%--        <form name="form1" method="post" action="deal.jsp">    --%>
            <table border="0" align="center">
                <tr class="login">
                    <td>用户姓名：</td>
                    <td><input type="text" name="name" value="" class="txtBox" /></td>
                </tr>
                <tr class="password">
                    <td>密码：</td>
                    <td><input type="password" name="password" value="" class="txtBox"/></td>
                </tr>

                <tr class="role">
                    <td>身份：</td>
                    <td>
                        <input type="radio" name="role" value="0"  checked="checked"/>普通用户
                        <input type="radio" name="role" value="1"/>管理员
                    </td>
                </tr>
                <tr class="button">
                    <td>
                        <input type="submit" name="action" value="登录" />
                    </td>
                    <%--                <form name="form2" method="post" action="register.jsp">--%>
                    <td>
                        <a href="../Enroll/rejest.jsp"><input type="button" value="注册" /></a>
                    </td>
                    <%--                </form>--%>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>