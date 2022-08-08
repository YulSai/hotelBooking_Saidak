<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Reservation</title>
</head>
<body>
<jsp:include page="../../navbar.jsp"/>
<h1>Reservation Detailed Info</h1>
<table>
    <tr>
        <th>Id</th>
        <th>User</th>
        <th>Type</th>
        <th>Capacity</th>
        <th>Price/night USD</th>
        <th>CheckIn</th>
        <th>CheckOut</th>
        <th>Nights</th>
        <th>Calculation</th>
        <th>Total cost USD</th>
        <th>Status</th>
    </tr>
    <tr>
        <td>${requestScope.reservation.id}</td>
        <td><a
                href="controller?command=user&id=${requestScope.reservation.user.id}">${requestScope.reservation.user.email}</a>
        </td>
        <td><a href="controller?command=room&id=${requestScope.reservation.roomId}">${requestScope.reservation.type}</a></td>
        <td>${requestScope.reservation.capacity}</td>
        <td>${requestScope.reservation.roomPrice}</td>
        <td>${requestScope.reservation.checkIn}</td>
        <td>${requestScope.reservation.checkOut}</td>
        <td>${nights}</td>
        <td>$${requestScope.reservation.roomPrice} x ${nights}</td>
        <td>TOTAL PRICE: ${requestScope.reservation.totalCost} USD</td>
        <td>${requestScope.reservation.status.toString().toLowerCase()}</td>
    </tr>
</table>
</body>
</html>