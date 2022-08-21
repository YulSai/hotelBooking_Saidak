<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/tables.css">
    <title>Reservation</title>
</head>
<body>
<jsp:include page="../../navbar.jsp"/>
<h1>Reservation Detailed Info</h1>
<table class="first">
    <jsp:include page="../pagination.jsp"/>
    <tr>
        <th>Id</th>
        <th>Item</th>
        <th>Total cost USD</th>
        <th>Status</th>
    </tr>
    <td><a
            href="controller?command=reservation&id=${requestScope.reservation.id}">${requestScope.reservation.id}</a>
    </td>
    <td>
        <table>
            <tr>
                <th>Room number</th>
                <th>Type</th>
                <th>Capacity</th>
                <th>CheckIn</th>
                <th>CheckOut</th>
                <th>Nights</th>
                <th>Price/night USD</th>
                <th>Calculation</th>

            </tr>
            <c:forEach items="${requestScope.reservation.details }" var="info">
                <tr>
                    <td><a href="controller?command=room&id=${info.room.id}">${info.room.number}</a></td>
                    <td>${info.room.type}</td>
                    <td>${info.room.capacity}</td>
                    <td>${info.checkIn}</td>
                    <td>${info.checkOut}</td>
                    <td>${info.nights}</td>
                    <td>${info.roomPrice}</td>
                    <td>${info.room.price} x ${info.nights}</td>
                </tr>
            </c:forEach>
        </table>
    </td>
    <td>${requestScope.reservation.totalCost} USD</td>
    <td>${requestScope.reservation.status.toString().toLowerCase()}</td>
    <c:if test="${sessionScope.user.role == 'ADMIN'}">
        <td><a href="controller?command=update_reservation_form&id=${requestScope.reservation.id}">Update status</a>
        </td>
    </c:if>
    <c:if test="${sessionScope.user.role == 'CLIENT'}">
        <td><a href="controller?command=cancel_reservation&id=${requestScope.reservation.id}">Cancel</a></td>
    </c:if>
</table>

</body>
</html>