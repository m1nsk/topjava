<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section>
    <form id="auth" class="form-horizontal" method="get" action="meals">
        <label for="users">Users</label>
        <select name="user" id="users">
            <option value="${requestScope.user}" selected>${requestScope.user}</option>
            <c:forEach items="${requestScope.users}" var="user">
                <option value="${user.id}">${user.id} </option>
            </c:forEach>
        </select>
        <input type="submit" value="Select User">
    </form>
</section>