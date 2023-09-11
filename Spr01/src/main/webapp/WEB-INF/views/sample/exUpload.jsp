<%--
  Created by IntelliJ IDEA.
  User: hanjae
  Date: 2023/09/08
  Time: 4:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>exUpload</title>
</head>
<body>
<form action="/sample/exUploadPost" method="post" enctype="multipart/form-data">
    <input type="file" name="files">
    <input type="file" name="files">
    <input type="file" name="files">
    <input type="submit" value="저장">
</form>
</body>
</html>
