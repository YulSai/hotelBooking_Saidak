<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="css/pages.css">
<div class="pagination">
    <a href="controller?command=reservations&page=1">First</a>
    <c:if test="${requestScope.current_page > 1}">
        <a href="controller?command=reservations&page=${requestScope.current_page - 1}">&laquo;</a>
    </c:if>
    <a class="active"
       href="controller?command=reservations&page=1">${requestScope.current_page}/${requestScope.total_pages}</a>
    <c:if test="${requestScope.current_page < requestScope.total_pages}">
        <a href="controller?command=reservations&page=${requestScope.current_page + 1}">&raquo;</a>
    </c:if>
    <a href="controller?command=reservations&page=${requestScope.total_pages}">Last</a>
</div>