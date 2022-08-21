<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Update reservation</title>
</head>
<body>
<jsp:include page="../../navbar.jsp"/>
<h1>Update reservation</h1>
<p>${requestScope.message}</p>
<form method="post" action="controller">
    <input name="command" type="hidden" value="update_reservation"/>
    <input name="id" type="hidden" value="${requestScope.reservation.id}"/>
    <br/>
    <select name="status" required="required">
        <option value="">Choose status</option>
        <option value="IN_PROGRESS">IN PROGRESS</option>
        <option value="CONFIRMED">CONFIRMED</option>
        <option value="REJECTED">REJECTED</option>
    </select>
    <br/>
    <input type="submit" value="Update"/>
</form>
</body>
</html>