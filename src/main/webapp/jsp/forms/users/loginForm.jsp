<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <title>Login</title>
</head>
<body>
<jsp:include page="../../navbar.jsp"/>
<h1>Login</h1>
<p>${requestScope.message}</p>
<form method="post" action="controller">
    <input name="command" type="hidden" value="login"/>
    <div class="imgcontainer">
        <img src="${pageContext.request.contextPath}/images/img_avatar.png" alt="Avatar" class="avatar">
    </div>

    <div class="container">
        <label for="email-input"><b>Email</b></label>
        <input id="email-input" type="email" placeholder="Enter email" name="email" required>
        <br/>
        <label for="password-input"><b>Password</b></label>
        <input id="password-input" type="password" placeholder="Enter password" name="password" min="6" required>
        <br/>

        <button type="submit">Login</button>
    </div>

    <div class="container" style="background-color:#f1f1f1">
        <button type="button" class="cancelbtn"><a href="/hotel_booking">Cancel</a></button>
    </div>
</form>
</body>
</html>