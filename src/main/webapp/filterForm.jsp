<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section>
    <form id="filter" class="form-horizontal" method="get" action="meals">
        <div class="form-group">
            <label for="endDate">До даты</label>
            <input id="endDate" type="date" class="form-control" name="endDate" value="${param.endDate}">
            <label for="endTime">До времени</label>
            <input id="endTime" type="time" class="form-control" name="endTime" value="${param.endTime}">
        </div>
        <div class="form-group">
            <label for="startDate">От даты</label>
            <input id="startDate" type="date" class="form-control" name="startDate" value="${param.startDate}">
            <label for="startTime">От времени</label>
            <input id="startTime" type="time" class="form-control" name="startTime" value="${param.startTime}">
        </div>
        <input type="submit" value="Фильтр">
    </form>
</section>