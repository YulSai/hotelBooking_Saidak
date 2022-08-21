<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charser=UTF-8"
        pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>HotelBooking</title>
</head>
<body>
<jsp:include page="jsp/navbar.jsp"/>
<p>${requestScope.message}</p>
<h1>Welcome to HotelBooking, ${sessionScope.user != null ? sessionScope.user.firstName : 'Guest'}!</h1>
<img src="images/main_hotel.jpg" alt="hotelBooking"/>
</body>
</html>