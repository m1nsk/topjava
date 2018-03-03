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
        <th>ID</th>
        <th>date time</th>
        <th>description</th>
        <th>calories</th>
        <th>exceed</th>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <tr>
            <td>
                <c:out value="${meal.id}" />
            </td>
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
                <c:if test="${meal.exceed eq true}">
                    <p class="green">
                        <c:out value="${meal.exceed}" />
                    </p>
                </c:if>
                <c:if test="${meal.exceed ne true}">
                    <p class="red">
                        <c:out value="${meal.exceed}" />
                    </p>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>