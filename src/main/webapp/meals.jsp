<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Simple jsp page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style type="text/css">
        td {
            padding: 10px;
        }
        th {
            padding: 10px;
        }
        .red {
            color: red;
        }
        .green {
            color: green;
        }
    </style>
</head>

<body>
<h1>Meals</h1>
<c:set var="meals" value="${requestScope.mealList}" />

<table class="table-bordered" style="margin: auto 0px">
    <!-- here should go some titles... -->
    <tr>
        <th>date time</th>
        <th>description</th>
        <th>calories</th>
        <th>delete</th>
        <th>edit</th>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <tr class="${meal.exceed ? "green" : "red"}">
            <td>
                <c:out value="${meal.formatedDateTime}" />
            </td>
            <td>
                <c:out value="${meal.description}" />
            </td>
            <td>
                <c:out value="${meal.calories}" />
            </td>
            <td>
                <form action="crud" method="get">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="id" value=${meal.id}>
                    <input type="submit" value="edit"/>
                </form>
            </td>
            <td>
                <form action="crud" method="get">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${meal.id}">
                    <input type="submit" value="delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>