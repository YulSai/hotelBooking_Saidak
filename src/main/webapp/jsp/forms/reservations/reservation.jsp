<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Reservation</title>
</head>
<body>
<jsp:include page="../../navbar.jsp"/>
<h1>Reservation Detailed Info</h1>
<table>
    <jsp:include page="pagination.jsp"/>
    <tr>
        <th>Reservations id</th>
    </tr>
    <td><a
            href="controller?command=reservation&id=${requestScope.reservation.id}">${requestScope.reservation.id}</a>
    </td>
    <td>
        <table>
            <th>Room number</th>
            <th>Type</th>
            <th>CheckIn</th>
            <th>CheckOut</th>
            <th>Nights</th>
            <th>Price/night USD</th>
            <th>Calculation</th>
            <th>Total cost USD</th>
            <th>Status</th>
            <c:forEach items="${requestScope.reservation.details }" var="info">
                <tr>
                    <td><a href="controller?command=room&id=${info.room.id}">${info.room.number}</a></td>
                    <td>${info.room.type}</td>
                    <td>${info.room.capacity}</td>
                    <td>${info.checkIn}</td>
                    <td>${info.checkOut}</td>
                    <td>${info.nights}</td>
                    <td>${info.room.price} x ${info.nights}</td>
                </tr>
            </c:forEach>
        </table>
        TOTAL COST: ${requestScope.reservation.totalCost} USD
    </td>
    <td>${requestScope.reservation.status.toString().toLowerCase()}</td>
    </tr>
</table>
</body>
</html>