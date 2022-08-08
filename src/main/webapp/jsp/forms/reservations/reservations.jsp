<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Reservations</title>
</head>
<body>
<jsp:include page="../../navbar.jsp"/>
<h1>Reservations</h1>
<table>
    <tr>
        <th>#</th>
        <th>Id</th>
        <th>User</th>
        <th>Items</th>
        <th>Status</th>
    </tr>
    <c:forEach items="${requestScope.reservations}" var="reservation"
               varStatus="counter">
        <tr>
            <td>${counter.count}</td>
            <td><a href="controller?command=reservation&id=${reservation.id}">${reservation.id}</a></td>
            <td><a href="controller?command=user&id=${reservation.user.id}">${reservation.user.email}</a></td>
            <td>${reservation.type} ${reservation.capacity}</td>
            <td>TOTAL PRICE: ${reservation.invoice} USD</td>
            <td>${reservation.status.toString().toLowerCase()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>