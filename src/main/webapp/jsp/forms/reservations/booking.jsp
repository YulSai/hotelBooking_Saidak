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
<c:if test="${requestScope.booking == null}}">
    <p>There are no current bookings</p>
</c:if>
<c:if test="${requestScope.booking != null}">
    <table>
        <tr>
            <th>Items</th>
            <th>Price/nights USD</th>
            <th>Check in date</th>
            <th>Check out date</th>
        </tr>
        <c:forEach items="${requestScope.booking.details}" var="item">
            <tr>
                <td><a href="controller?command=room&id=${item.room.id}">${item.room.number}</a></td>
                <td>${item.room.price}</td>
                <td>${item.chekIn}</td>
                <td>${item.checkOut}</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="3">TOTAL COST: ${requestScope.booking.totalCost} USD</td>
        </tr>
    </table>
    <a href="controller?command=create_reservation">Place reservation</a>
</c:if>
</body>
</html>