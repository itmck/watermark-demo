<%--
  Created by IntelliJ IDEA.
  User: 34745
  Date: 2018/10/13
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传页面</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/water/mark" method="post" enctype="multipart/form-data">
    <input type="file" name="img" >
    <input type="submit" value="上传图片">
</form>
</body>
</html>
