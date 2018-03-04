<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="head.html"/>
</head>
<body>

<c:set var="mealItem" value="${requestScope.mealItem}"/>
<c:set var="errorItem" value="${requestScope.errorItem}"/>

<c:import url="header.html"/>
<h2><c:out value='${requestScope.containsKey("mealItem") ? "Add new meal item" : "Edit meal item"}'></c:out></h2>

<form action="${requestScope['javax.servlet.forward.request_uri']}" method="post" name="frmAddMeal">
    <p>Description: <input type="text" name="description" value="${mealItem.description}"/></p>
    <c:if test="${errorItem.containsKey('description')}">
        <p style="color: red"><c:out value="error ${errorItem.description}"/></p>
    </c:if>
    <p>Calories: <input type="text" name="calories" value="${mealItem.calories}"/></p>
    <c:if test="${errorItem.containsKey('calories')}">
        <p style="color: red"><c:out value="error: ${errorItem.calories}"/></p>
    </c:if>
    <p>DateTime: <input type="datetime-local" name="dateTime" value="${mealItem.dateTime}"></p>
    <c:if test="${errorItem.containsKey('dateTime')}">
        <p style="color: red"><c:out value="error: ${errorItem.dateTime}"/></p>
    </c:if>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>