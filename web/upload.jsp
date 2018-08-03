<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2018/8/2
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>文件上传</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/UploadHandleServlet" enctype="multipart/form-data" method="post">
    上传文件1：<input type="file" name="file1"><br/>
    <input type="submit" value="提交">
</form>
</body>
</html>



