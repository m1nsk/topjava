<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <c:import url="head.html"/>
</head>

<body>
<c:import url="header.html" />
<jsp:useBean id="MealsUtil" class="ru.javawebinar.topjava.util.MealsUtil"/>
<c:set var="meals" value="${requestScope.mealList}" />

<h1>Meals</h1>
<li><a href="crud?action=create">Create</a></li>
<table class="table-bordered" style="margin: auto 0px">
    <tr>
        <th>date time</th>
        <th>description</th>
        <th>calories</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <tr class="${meal.exceed ? "red" : "green"}">
            <td>
                <c:set var="mFormatted" value='${MealsUtil.getFormatedDateTime(meal.dateTime)}' />
                <c:out value='${mFormatted}'/>
            </td>
            <td>
                <c:out value="${meal.description}" />
            </td>
            <td>
                <c:out value="${meal.calories}" />
            </td>
            <td><a href="crud?action=edit&id=${meal.id}">Edit</a></td>
            <td><a href="crud?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>