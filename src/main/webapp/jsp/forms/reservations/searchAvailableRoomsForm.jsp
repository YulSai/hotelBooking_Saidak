<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Search available rooms</title>
</head>
<body>
<jsp:include page="../../navbar.jsp"/>
<h1>Search available rooms</h1>
<p>${requestScope.message}</p>
<form method="post" action="controller">
    <input name="command" type="hidden" value="search_available_rooms"/>
    <label for="check_in">Check in:</label>
    <input id="check_in" name="check_in" type="date" min="2022-08-21" max="2025-12-30">
    <br/>
    <label for="check_out">Check out:</label>
    <input id="check_out" name="check_out" type="date" min="2022-08-22" max="2025-12-31">
    <br/>
    <select name="type" required="required">
        <option value="">Choose type</option>
        <option value="STANDARD">STANDARD</option>
        <option value="COMFORT">COMFORT</option>
        <option value="LUX">LUX</option>
        <option value="PRESIDENT">PRESIDENT</option>
    </select>
    <br/>
    <select name="capacity" required="required">
        <option value="">Choose capacity</option>
        <option value="SINGLE">SINGLE</option>
        <option value="DOUBLE">DOUBLE</option>
        <option value="TRIPLE">TRIPLE</option>
        <option value="FAMILY">FAMILY</option>
    </select>
    <br/>
    <input type="submit" value="SEARCH"/>
</form>
</body>
</html>