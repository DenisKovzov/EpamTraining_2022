<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<c:choose>
    <c:when test="${not empty brigade}">
        <c:set var="title" value="Редактирование бригады ${brigade.name}"/>
    </c:when>
    <c:when test="${empty brigade}">
        <c:set var="title" value="Создание бригады"/>
    </c:when>
</c:choose>
<u:page title="${title}" mainPage="true">


    <c:url var="brigadeSaveUrl" value="/brigade/save.html"/>
    <form action="${brigadeSaveUrl}" method="post">
        <c:if test="${not empty brigade}">
            <input type="hidden" name="id" value="${brigade.id}">
        </c:if>

        <input type="text" name="name" value="${brigade.name}">
        <ul>
            <c:forEach var="employee" items="${employees}">
                <c:if test="${not empty brigade}">
                    <c:choose>
                        <c:when test="${brigade.id == employee.brigadeId}">
                            <c:set var="checked" value="checked"/>
                        </c:when>
                        <c:otherwise>
                            <c:remove var="checked"/>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <li>
                    <input type="checkbox" name="employeeId" value="${employee.id}" ${checked}>
                    <b>${employee.login}</b>
                </li>
            </c:forEach>
        </ul>


        <button type="submit">Сохранить</button>
    </form>
    <br> <br>

    <c:if test="${not empty brigade}">
        <c:url var="brigadeDeleteUrl" value="/brigade/delete.html"></c:url>
        <form action="${brigadeDeleteUrl}" method="post">
            <input name="id" type="hidden" value="${brigade.id}">
            <button type="submit">Удалить Бригаду</button>
        </form>
    </c:if>

</u:page>

