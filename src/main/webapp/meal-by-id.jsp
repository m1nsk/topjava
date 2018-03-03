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
    </style>
</head>

<body>
<h1>Meal item</h1>
<c:set var="mealItem" value="${requestScope.mealItem}" />

<table class="table-bordered" style="margin: auto 0px">
    <!-- here should go some titles... -->
    <tr>
        <th>date time</th>
        <th>description</th>
        <th>calories</th>
    </tr>
    <tr>
        <td>
            <c:out value="${mealItem.formatedDateTime}" />
        </td>
        <td>
            <c:out value="${mealItem.description}" />
        </td>
        <td>
            <c:out value="${mealItem.calories}" />
        </td>
    </tr>
</table>
</body>
</html>