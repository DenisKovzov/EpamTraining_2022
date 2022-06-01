<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<u:page title="Список пользователей" mainPage="true">

    <c:if test="${not empty param.message}">
        <u:error message="${param.message}"/>
    </c:if>

    <c:url var="userDeleteUrl" value="/user/delete.html"/>
    <form action="${userDeleteUrl}" method="post">
        <ul>
            <c:forEach var="user" items="${users}">
                <c:url var="userEditUrl" value="/user/edit.html">
                    <c:param name="id" value="${user.id}"/>
                </c:url>
                <li><input type="checkbox" name="id" value="${user.id}"><a href="${userEditUrl}">${user.login}</a>
                    [${user.role.name}]
                </li>
            </c:forEach>
        </ul>
        <c:url var="userEditUrl" value="/user/edit.html"/>
        <a href="${userEditUrl}">Добавить нового пользователя</a>
        <br>
        <br>
        <button type="submit">Удалить</button>
    </form>
</u:page>