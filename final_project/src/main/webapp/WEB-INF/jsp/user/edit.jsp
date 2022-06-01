<%@ page import="vsu.by.models.User" %>
<%@ page import="vsu.by.models.Role" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<c:choose>
    <c:when test="${not empty user}">
        <c:set var="title" value="Редактирование пользователя ${user.login}"/>
    </c:when>
    <c:when test="${empty user}">
        <c:set var="title" value="Создание пользователя"/>
    </c:when>
</c:choose>

<u:page title="${title}" mainPage="true">

    <c:url var="userSaveUrl" value="/user/save.html"/>
    <form action="${userSaveUrl}" method="post">
        <c:if test="${not empty user}">
            <input type="hidden" name="id" value="${user.id}"><br>
        </c:if>
        Имя пользователя:<br>
        <input type="text" name="login" value="${user.login}"><br>
        <br>
        Пароль:<br>
        <input type="text" name="password" value="${user.password}"><br>
        <br>

        <c:choose>
            <c:when test="${session_user.role == 'ADMIN'}">
                Роль:<br>
                <select name="role">
                    <c:forEach var="role" items="${roles}">
                        <c:choose>
                            <c:when test="${role == user.role}">
                                <c:set var="selected" value="selected"/>
                            </c:when>
                            <c:otherwise>
                                <c:remove var="selected"/>
                            </c:otherwise>
                        </c:choose>
                        <option value="${role}" ${selected}>${role.name}</option>
                    </c:forEach>
                </select>
            </c:when>

            <c:otherwise>
                <input type="hidden" name="role" value="${session_user.role}">
            </c:otherwise>

        </c:choose>

        <br>
        <br>

        <button type="submit">сохранить</button>
    </form>
</u:page>
