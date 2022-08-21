<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Edit user</title>
</head>
<body>
<jsp:include page="../../navbar.jsp"/>
<h1>Edit user</h1>
<p>${requestScope.message}</p>
<form method="post" action="controller">
    <input name="command" type="hidden" value="update_user"/>
    <input name="id" type="hidden" value="${requestScope.user.id}"/>

    <c:if test="${sessionScope.user.role == 'CLIENT'}">
        <label for="first_name-input">First name: </label>
        <input id="first_name-input" name="first_name" type="text" value="${requestScope.user.firstName}"/>
        <br/>

        <label for="last_name-input">Last name: </label>
        <input id="last_name-input" name="last_name" type="text" value="${requestScope.user.lastName}"/>
        <br/>

        <label for="email-input">Email: </label>
        <input id="email-input" name="email" type="email" value="<c:out value="${requestScope.user.email}"/>"/>
        <br/>

        <label for="password-input">Password: </label>
        <input id="password-input" name="password" type="password" min="6"/>
        <br/>

        <label for="phone_number-input">Phone number: </label>
        <input id="phone_number-input" name="phone_number" type="tel" min="13"
               value="${requestScope.user.phoneNumber}"/>
        <br/>
    </c:if>

    <c:if test="${sessionScope.user.role == 'ADMIN'}">
        <input id="role-input-admin" name="role" type="radio"
               value="ADMIN" ${requestScope.user.role='ADMIN' ? 'checked' : ''}/>
        <label for="role-input-admin">Admin</label>
        <input id="role-input-client" name="role" type="radio"
               value="CLIENT" ${requestScope.user.role='CLIENT' ? 'checked' : ''}/>
        <label for="role-input-client">Client</label>
        <br/>
    </c:if>

    <input type="submit" value="Save"/>
</form>
</body>
</html>