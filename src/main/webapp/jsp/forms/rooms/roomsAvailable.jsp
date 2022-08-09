<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Available rooms</title>
</head>
<body>
<jsp:include page="../../navbar.jsp"/>
<p>${requestScope.message}</p>
<table>
    <jsp:include page="paginationRoomsAvalable.jsp"/>

    <tr>
        <th>#</th>
        <th>Number</th>
        <th>Type</th>
        <th>Capacity</th>
        <th>Status</th>
        <th>Price/night USD</th>
        <th>Action</th>
    </tr>

    <c:forEach items="${rooms_available}" var="room" varStatus="counter">
        <tr>
            <td><a href="controller?command=room&id=${room.id}">${counter.count}</a></td>
            <td>${room.number}</td>
            <td>${room.type}</td>
            <td>${room.capacity}</td>
            <td>${room.status}</td>
            <td>${room.price}</td>
            <td>
                <form method="post" action="controller">
                    <input type="hidden" name="command" value="booking">
                    <input type="hidden" name="room_id" value="${requestScope.room.id}">
                    <input type="submit" value="Book now">
                </form>
            </td>
        </tr>
    </c:forEach>

    <ul>
        <li><a href="/hotel_booking">Home page</a></li>
    </ul>

</table>
</body>
</html>