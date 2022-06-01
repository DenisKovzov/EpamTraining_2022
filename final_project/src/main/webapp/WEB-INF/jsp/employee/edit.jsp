<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<c:choose>
    <c:when test="${not empty employee}">
        <c:set var="title" value="Редактирование сотрудника ${employee.login}"/>
    </c:when>
    <c:when test="${empty employee}">
        <c:set var="title" value="Создание сотрудника"/>
    </c:when>
</c:choose>
<u:page title="${title}" mainPage="true">
    <c:url var="employeeSaveUrl" value="/employee/save.html"/>


    <form action="${employeeSaveUrl}" method="post">
        <c:choose>
            <c:when test="${not empty employee}">
                <input type="hidden" name="id" value="${employee.id}"><br>
            </c:when>

            <c:otherwise>
                <select name="id">
                    <c:forEach var="user" items="${users}">
                        <option value="${user.id}">${user.login}</option>
                    </c:forEach>
                </select>
            </c:otherwise>
        </c:choose>

        <br><br>
        Должность:<br>
        <select name="position">
            <c:forEach var="position" items="${positions}">
                <c:choose>
                    <c:when test="${position == employee.position}">
                        <c:set var="selected" value="selected"/>
                    </c:when>
                    <c:otherwise>
                        <c:remove var="selected"/>
                    </c:otherwise>
                </c:choose>
                <option value="${position}" ${selected}>${position.name}</option>
            </c:forEach>
        </select>
        <br>
        <br>
        <button type="submit">сохранить</button>
    </form>
</u:page>

