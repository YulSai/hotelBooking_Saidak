<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Booking</title>
</head>
<body>
<jsp:include page="../../navbar.jsp"/>
<h1 id="title">Your booking</h1>
<p>${requestScope.message}</p>
<tr>
    <th>Full name</th>
    <th>Items</th>
    <th>Price</th>
    <th>Type</th>
    <th>Capacity</th>
    <th>Price/nights USD</th>
    <th>Check in date</th>
    <th>Check out date</th>
    <th>Nights</th>
    <th>Calculation</th>
</tr>

<td>
    <table>
        <td><a
                href="controller?command=user&id=${requestScope.reservation.user.id}">${requestScope.reservation.user.firstName}
            ${requestScope.reservation.user.lastName}</a>
        </td>
        <td><a
                href="controller?command=reservation&id=${requestScope.reservation.id}">${requestScope.reservation.id}</a>
        </td>
        <td><a href="controller?command=room&id=${requestScope.reservation.roomId}">${requestScope.room_number}</a></td>
        <td>${requestScope.reservation.type}</td>
        <td>${requestScope.reservation.capacity}</td>
        <td>${requestScope.reservation.roomPrice}</td>
        <td>${requestScope.reservation.checkIn}</td>
        <td>${requestScope.reservation.checkOut}</td>
        <td>${requestScope.nights}</td>
        <td>$${requestScope.reservation.roomPrice} x ${requestScope.nights}</td>
    </table>
</td>
TOTAL PRICE: ${requestScope.reservation.totalCost} USD
<td>${requestScope.reservation.status.toString().toLowerCase()}</td>
</body>
</html>