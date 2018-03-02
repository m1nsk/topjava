<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Simple jsp page</title></head>
<body>
vasyano: <c:out value="${sessionScope.vasyano}" />
<c:forEach begin="0" end="255" var="i">
    <span style='color:rgb(
        <c:out value="${i}"/>,
        <c:out value="${i}"/>,
        <c:out value="${i}"/>);'>
        <c:out value="${i}"/></span> <br/>
</c:forEach>
</body>
</html>