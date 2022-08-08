<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Room</title>
</head>
<body>
<jsp:include page="../../navbar.jsp"/>
<h1 id="title">The Room</h1>
<p>${requestScope.message}</p>
<table>
    <tr>
        <th>Field</th>
        <th>Value</th>
    </tr>
    <tr>
        <td class="name">Id</td>
        <td class="sign">${requestScope.room.id}</td>
    </tr>
    <tr>
        <td class="name">Number</td>
        <td class="sign">${requestScope.room.number}</td>
    </tr>
    <tr>
        <td class="name">Type</td>
        <td class="sign">${requestScope.room.typeDto}</td>
    </tr>
    <tr>
        <td class="name">Capacity</td>
        <td class="sign">${requestScope.room.capacityDto}</td>
    </tr>
    <tr>
        <td class="name">Status</td>
        <td class="sign">${requestScope.room.statusDto}</td>
    </tr>
    <tr>
        <td class="name">Price/night, USD</td>
        <td class="sign">${requestScope.room.price}</td>
    </tr>
    <tr>
        <form method="post" action="controller">
            <input type="hidden" name="command" value="add_to_cart">
            <input type="hidden" name="room_id" value="${requestScope.room.id}">
            <input type="submit" value="Add to cart">
        </form>
    </tr>

</table>

<ul>
    <li><a href="controller?command=update_room_form&id=${requestScope.room.id}">Update room</a></li>
    <li><a id="allBooks" href="controller?command=rooms">Return
        to the list of all rooms</a></li>
    <li><a id="home" href="/hotel_booking">Home page</a></li>
</ul>
</body>
</html>