<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Register new room</title>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<h1>Register new room</h1>
<p>${requestScope.message}</p>
<form method="post" action="controller">
    <input name="command" type="hidden" value="create_room"/>

    <label for="number-input">Number: </label>
    <input id="number-input" name="number" type="text"/>
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
    <select name="status" required="required">
        <option value="">Choose status</option>
        <option value="AVAILABLE">AVAILABLE</option>
        <option value="UNAVAILABLE">UNAVAILABLE</option>
    </select>
    <br/>
    <label for="price-input">Price/night, USD: </label>
    <input id="price-input" name="price" type="text"/>
    <br/>

    <input type="submit" value="REGISTER"/>
</form>

<ul>
    <li><a id="allBooks" href="controller?command=rooms">Return
        to the list of all rooms</a></li>
    <li><a id="home" href="/hotel_booking">Home page</a></li>
</ul>

</body>
</html>