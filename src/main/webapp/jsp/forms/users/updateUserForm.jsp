<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Edit user</title>
</head>
<body>
<jsp:include page="../../navbar.jsp"/>
<h1>Edit user</h1>
<p>${requestScope.message}</p>
<form method="post" action="controller">
    <input name="command" type="hidden" value="update_user"/>
    <input name="id" type="hidden" value="${requestScope.user.id}"/>

    <label for="first_name-input">First name: </label>
    <input id="first_name-input" name="first_name" type="text" value="${requestScope.user.firstName}"/>
    <br/>

    <label for="last_name-input">Last name: </label>
    <input id="last_name-input" name="last_name" type="text" value="${requestScope.user.lastName}"/>
    <br/>

    <label for="email-input">Email: </label>
    <input id="email-input" name="email" type="email" value="${requestScope.user.email}"/>
    <br/>

    <label for="password-input">Password: </label>
    <input id="password-input" name="password" type="password" min="6"/>
    <br/>

    <label for="phone_number-input">Phone number: </label>
    <input id="phone_number-input" name="phone_number" type="tel" min="13" value="${requestScope.user.phoneNumber}"/>
    <br/>

    <input id="role-input-admin" name="role" type="radio"
           value="ADMIN" ${requestScope.user.role='ADMIN' ? 'checked' : ''}/>
    <label for="role-input-admin">Admin</label>
    <input id="role-input-client" name="role" type="radio"
           value="CLIENT" ${requestScope.user.role='CLIENT' ? 'checked' : ''}/>
    <label for="role-input-client">Client</label>
    <input id="role-input-guest" name="role" type="radio"
           value="GUEST" ${requestScope.user.role='GUEST' ? 'checked' : ''}/>
    <label for="role-input-guest">Guest</label>
    <br/>

    <input type="submit" value="Save"/>
</form>

<ul>
    <li><a href="controller?command=users">Return to the list of
        all users</a></li>
    <li><a href="/hotel_booking">Home page</a></li>
</ul>

</body>
</html>