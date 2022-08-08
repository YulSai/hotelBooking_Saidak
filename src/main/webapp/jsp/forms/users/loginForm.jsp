<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<title>Login</title>
</head>
<body>
<jsp:include page="../../navbar.jsp"/>
<h1>Login</h1>
<p>${requestScope.message}</p>
<form method="post" action="controller">
	<input name="command" type="hidden" value="login"/>

	<label for="email-input">Email: </label>
	<input id="email-input" name="email" type="email"/>
	<br/>

	<label for="password-input">Password: </label>
	<input id="password-input" name="password" type="password" min="6"/>
	<br/>

	<input type="submit" value="LOGIN"/>
</form>

<ul>
	<li><a href="/hotel_booking">Home page</a></li>
</ul>

</body>
</html>