<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/tables.css">
    <title>User</title>
</head>
<body>
<jsp:include page="../../navbar.jsp"/>
<h1>User personal info</h1>
<p>${requestScope.message}</p>
<table class="first">
    <tr>
        <th>Field</th>
        <th>Value</th>
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
        <td><c:out value="${requestScope.user.phoneNumber}"/></td>
    </tr>
    <tr>
        <td>Role</td>
        <td>${requestScope.user.role.toString().toLowerCase()}</td>
    </tr>
</table>
<ul>
    <li><a href="controller?command=update_user_form&id=${requestScope.user.id}">Update user</a></li>
    <c:if test="${sessionScope.user.role == 'ADMIN'}">
        <li><a href="controller?command=delete_user&id=${requestScope.user.id}">Delete user</a></li>
    </c:if>
</ul>
</body>
</html>