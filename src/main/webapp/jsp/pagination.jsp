<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<a href="controller?command=rooms&page=1">First</a>
<a href="controller?command&rooms&page=${requestScope.current_page - 1}">Prev</a>
Page ${requestScope.current_page} out of ${requestScope.total_pages}
<a href="controller?command=rooms&page=${requestScope.current_page + 1}">Next</a>
<a href="controller?command=rooms&page=${requestScope.total_pages + 1}">Last</a>


<a href="controller?command=users&page=1">First</a>
<a href="controller?command=users&page=${requestScope.current_page - 1}">Prev</a>
Page ${requestScope.current_page} out of ${requestScope.total_pages}
<a href="controller?command=users&page=${requestScope.current_page + 1}">Next</a>
<a href="controller?command=users&page=${requestScope.total_pages + 1}">Last</a>


<a href="controller?command=reservations&page=1">First</a>
<a href="controller?command=reservations&page=${requestScope.current_page - 1}">Prev</a>
Page ${requestScope.current_page} out of ${requestScope.total_pages}
<a href="controller?command=reservations&page=${requestScope.current_page + 1}">Next</a>
<a href="controller?command=reservations&page=${requestScope.total_pages + 1}">Last</a>
