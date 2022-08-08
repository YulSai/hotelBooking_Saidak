<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/style.css">
<title>User</title>
</head>
<body>
<jsp:include page="../../navbar.jsp"/>
	<h1>User personal info</h1>
	<p>${requestScope.message}</p>
	<table>
		<tr>
			<th>Field</th>
			<th>Value</th>
		</tr>
		<tr>
			<td>Id</td>
			<td>${requestScope.user.id}</td>
		</tr>
		<tr>
			<td>First name</td>
			<td>${requestScope.user.firstName}</td>
		</tr>
		<tr>
			<td>Last name</td>
			<td>${requestScope.user.lastName}</td>
		</tr>
		<tr>
			<td>Email</td>
			<td>${requestScope.user.email}</td>
		</tr>
		<tr>
			<td>Phone number</td>
			<td>${requestScope.user.phoneNumber}</td>
		</tr>
		<tr>
			<td>Role</td>
			<td>${requestScope.user.roleDto.toString().toLowerCase()}</td>
		</tr>
	</table>
	<ul>
		<li><a href="controller?command=update_user_form&id=${requestScope.user.id}">Update user</a></li>
		<li><a href="controller?command=users">Return to the list of
				all users</a></li>
		<li><a href="/hotel_booking">Home page</a></li>
	</ul>
</body>
</html>