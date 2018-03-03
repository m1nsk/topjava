<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal create</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Add new meal</h2>
<c:set var="mealItem" value="${requestScope.mealItem}" />
<c:set var="errorItem" value="${requestScope.errorItem}" />
<form action="${requestScope['javax.servlet.forward.request_uri']}" method="post" name="frmAddMeal">
    <c:if test='${requestScope.containsKey("mealItem")}'>
        <p>id: <input type = "text" name = "id" readonly="readonly" value="${mealItem.id}"/></p>
    </c:if>
    <p>Description: <input type = "text" name = "description" value="${mealItem.description}"/></p>
    <c:if test="${errorItem.containsKey('description')}">
        <p style="color: red"><c:out value="error ${errorItem.description}" /></p>
    </c:if>
    <p>Calories: <input type = "text" name = "calories" value="${mealItem.calories}"/></p>
    <c:if test="${errorItem.containsKey('calories')}">
        <p style="color: red"><c:out value="error: ${errorItem.calories}" /></p>
    </c:if>
    <p>DateTime: <input type="datetime-local" name="dateTime" value="${mealItem.dateTime}"></p>
    <c:if test="${errorItem.containsKey('dateTime')}">
        <p style="color: red"><c:out value="error: ${errorItem.dateTime}" /></p>
    </c:if>
    <input type = "submit" value = "Submit" />
</form>
</body>
</html>