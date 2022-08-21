<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/tables.css">
    <title>Available rooms</title>
</head>
<body>
<jsp:include page="../../navbar.jsp"/>
<h1 id="title">Available rooms for the period</h1>
<h2>${requestScope.check_in} - ${requestScope.check_out}</h2>
<p>${requestScope.message}</p>
<table>
    <jsp:include page="../pagination.jsp"/>
    <tr>
        <th>#</th>
        <th>Number</th>
        <th>Type</th>
        <th>Capacity</th>
        <th>Status</th>
        <th>Price/night USD</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${requestScope.rooms_available}" var="room" varStatus="counter">
        <tr>
            <td><a href="controller?command=room&id=${room.id}">${counter.count}</a></td>
            <td>${room.number}</td>
            <td>${room.type}</td>
            <td>${room.capacity}</td>
            <td>${room.status}</td>
            <td>${room.price}</td>
            <td>
                <form method="post" action="controller">
                    <input type="hidden" name="command" value="add_booking">
                    <input type="hidden" name="room_id" value="${room.id}">
                    <input type="hidden" name="check_in" value="${check_in}">
                    <input type="hidden" name="check_out" value="${check_out}">
                    <input type="submit" value="Book now">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>