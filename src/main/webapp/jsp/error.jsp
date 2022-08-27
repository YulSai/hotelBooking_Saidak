<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Error</title>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>Error</h1>
<p>${requestScope.message}</p>
</body>
</html>