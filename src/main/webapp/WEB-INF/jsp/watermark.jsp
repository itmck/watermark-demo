<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>水印图片</title>
</head>
<body>
<div align="center">
    加上水印之前:
    <hr/>
    <img src="${pageContext.request.contextPath}${pi1}">
    <hr>
    加上单个文字水印之后:
    <hr>
    <img src="${pageContext.request.contextPath}${pi2}">
    <hr>
    加上多个文字水印之后:
    <hr>
    <img src="${pageContext.request.contextPath}${pi3}">
</div>
</body>
</html>
