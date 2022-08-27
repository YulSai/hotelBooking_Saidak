<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Register new user</title>
</head>
<body>
<jsp:include page="../../navbar.jsp"/>
<h1>Register new user</h1>
<p>${requestScope.message}</p>
<form method="post" action="controller">
    <input name="command" type="hidden" value="create_user"/>
    <label for="first_name-input">First name: </label>
    <input id="first_name-input" name="first_name" type="text"/>
    <br/>
    <label for="last_name-input">Last name: </label>
    <input id="last_name-input" name="last_name" type="text"/>
    <br/>
    <label for="email-input">Email: </label>
    <input id="email-input" name="email" type="email"/>
    <br/>
    <label for="password-input">Password: </label>
    <input id="password-input" name="password" type="password" min="6"/>
    <br/>
    <label for="phone_number-input">Phone number: </label>
    <input id="phone_number-input" name="phone_number" type="tel" min="6"/>
    <br/>
    <input type="submit" value="Register"/>
</form>
</body>
</html>