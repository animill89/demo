<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>Insert title here</title>
    <script src="https://cdn.ckeditor.com/ckeditor5/37.1.0/super-build/ckeditor.js"></script>
    <link rel="stylesheet" type="text/css" href="css/editor.css">
    <script type="text/javascript" src="js/editor.js"></script>
</head>
<body>
   <div align="center">请输入新闻信息
       <form method="post" enctype="multipart/form-data" action="AddNewsServlet">
        <table border="0" align="center">
            <tr>
                <td>新闻标题：</td>
                <td><input type="text" class="txtBox" name="title"></td>
            </tr>
            <tr>
                <td>新闻内容：</td>
                <td>
                <div id="container">
                <textarea id="editor" name="content"></textarea>
                </div>
                </td>
            </tr>
            <tr>
                <td>图片：</td>
                <td><input type="file" name="photo"></td>
            </tr>
            
            
            <!-- 以下是提交、取消按钮 -->
            <tr>
                <td>
                    <input type="submit" value="发布新闻" />
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