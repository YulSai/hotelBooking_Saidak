<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Error</title>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h2>No such page!</h2>
<p>${requestScope.message}</p>
</body>
</html>