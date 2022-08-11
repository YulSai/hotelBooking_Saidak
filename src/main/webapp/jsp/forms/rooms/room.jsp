<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/tables.css">
    <title>Room</title>
</head>
<body>
<jsp:include page="../../navbar.jsp"/>
<h1 id="title">The Room</h1>
<p>${requestScope.message}</p>
<table class="first">
    <tr>
        <th>Field</th>
        <th>Value</th>
    </tr>
    <tr>
        <td class="name">Number</td>
        <td class="sign">${requestScope.room.number}</td>
    </tr>
    <tr>
        <td class="name">Type</td>
        <td class="sign">${requestScope.room.type}</td>
    </tr>
    <tr>
        <td class="name">Capacity</td>
        <td class="sign">${requestScope.room.capacity}</td>
    </tr>
    <tr>
        <td class="name">Status</td>
        <td class="sign">${requestScope.room.status}</td>
    </tr>
    <tr>
        <td class="name">Price/night, USD</td>
        <td class="sign">${requestScope.room.price}</td>
    </tr>
</table>
<ul>
    <li><a href="controller?command=update_room_form&id=${requestScope.room.id}">Update room</a></li>
</ul>
</body>
</html>