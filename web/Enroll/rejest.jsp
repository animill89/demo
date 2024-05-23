<%--
  Created by IntelliJ IDEA.
  User: Star_Star
  Date: 2024/4/22
  Time: 8:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <head>
        <meta charset="UTF-8">
        <title>用户注册</title>
        <meta name="author" content="...的博客">
        <style>
            .txtBox{
                padding: 3px;
                width: 300px;
                font-size: 16px;
            }
        </style>
    </head>
<body>
<div align="center">请输入注册信息
    <%--    <form name="form1" method="post" action="login.jsp">--%>
    <form name="form1" method="post" action="../servlet/RejestServlet">
        <table border="0" align="center">
            <tr>
                <td>用户姓名：</td>
                <td><input type="text" name="name" value="" class="txtBox" /></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="password" name="password" value="" class="txtBox"/></td>
            </tr>
            <tr>
                <td>性别：</td>
                <td>
                    <input type="radio" name="sex" value="0"  checked="checked"/>男
                    <input type="radio" name="sex" value="1"/>女
                </td>
            </tr>
            <tr>
                <td>年龄：</td>
                <td><input type="text" name="age" value="" class="txtBox"/></td>
            </tr>
            <tr>
                <td>电话：</td>
                <td><input type="text" name="phone" value="" class="txtBox"/></td>
            </tr>
            <tr>
                <td>兴趣爱好：</td>
                <td>
                    <input type="checkbox" name="favorites" value="足球"  />足球
                    <input type="checkbox" name="favorites" value="篮球"  />篮球
                    <input type="checkbox" name="favorites" value="羽毛球"  />羽毛球
                    <input type="checkbox" name="favorites" value="乒乓球"  />乒乓球
                </td>
            </tr>
            <tr>
                <td>身份：</td>
                <td>
                    <input type="radio" name="role" value="0" />普通用户
                    <input type="radio" name="role" value="1"/>管理员
                </td>
            </tr>
            <!-- 以下是提交、取消按钮 -->
            <tr>
                <td>
                    <input type="submit" value="注册" />
                </td>
                <td>
                    <input type="reset" value="取消" />
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>