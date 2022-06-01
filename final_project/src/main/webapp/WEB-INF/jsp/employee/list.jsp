<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<u:page title="Список сотрудников" mainPage="true">

    <c:if test="${not empty param.message}">
        <u:error message="${param.message}"/>
    </c:if>

    <c:url var="employeeDeleteUrl" value="/employee/delete.html"/>
    <form action="${employeeDeleteUrl}" method="post">
        <ul>
            <c:forEach var="employee" items="${employees}">
                <c:url var="employeeEditUrl" value="/employee/edit.html">
                    <c:param name="id" value="${employee.id}"/>
                </c:url>
                <li><input type="checkbox" name="id" value="${employee.id}"> <a href="${employeeEditUrl}">${employee.login}</a>
                    - ${employee.position.name}
                </li>
            </c:forEach>
        </ul>
        <c:url var="employeeEditUrl" value="/employee/edit.html"/>
        <a href="${employeeEditUrl}">Добавить нового сотрудника</a>
        <br>
        <br>
        <button type="submit">Удалить</button>
    </form>
</u:page>