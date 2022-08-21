<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="css/navbar.css">
<ul class="navbar">
    <li><a class="active" href="/hotel_booking">Home</a></li>
    <li><a href="controller?command=search_available_rooms_form">Search available rooms</a></li>
    <li><a href="controller?command=booking">Booking</a></li>


    <c:if test="${sessionScope.user.role == 'ADMIN'}">
        <li><a href="controller?command=rooms">All rooms</a></li>
        <li><a href="controller?command=users">All users</a></li>
        <li><a href="controller?command=reservations">All reservations</a></li>
        <li><a href="controller?command=create_room_form">Submit a new room</a></li>
    </c:if>

    <c:if test="${sessionScope.user.role == 'CLIENT'}">
        <li><a href="controller?command=user&id=${sessionScope.user.id}">Profile</a></li>
        <li><a href="controller?command=reservations">My reservations</a></li>
    </c:if>

    <c:if test="${sessionScope.user != null}">
        <li><a style="float:right" href="controller?command=logout">Logout</a></li>
    </c:if>
    <c:if test="${sessionScope.user == null}">
        <li><a style="float:right" href="controller?command=create_user_form">Register</a></li>
        <li><a style="float:right" href="controller?command=login_form">Sign in</a></li>
    </c:if>
</ul>