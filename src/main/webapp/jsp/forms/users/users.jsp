<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Users</title>
</head>
<body>
<jsp:include page="../../navbar.jsp"/>
<p>${requestScope.message}</p>
	<table>
		<th>#</th>
		<th>First name</th>
		<th>Last name</th>
		<th>Email</th>
		<th>Phone number</th>
		<th>Role</th>

		<c:forEach items="${requestScope.users}" var="user" varStatus="counter">
			<tr>
				<td>${counter.count}</td>
				<td><a href="controller?command=user&id=${user.id}">${user.firstName}</a></td>
				<td><a href="controller?command=user&id=${user.id}">${user.lastName}</a></td>
				<td>${user.email}</td>
				<td>${user.phoneNumber}</td>
				<td>${user.roleDto.toString().toLowerCase()}</td>
				<td><li><a href="controller?command=update_user_form&id=${user.id}">Update user</a></li></td>
			</tr>
		</c:forEach>

		<ul>
			<li><a href="/hotel_booking">Home page</a></li>
		</ul>

	</table>
</body>
</html>