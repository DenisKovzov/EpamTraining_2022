<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>


<u:page title="Авторизация" mainPage="false">

    <c:if test="${not empty param.message}">
        <u:error message="${param.message}"/>
    </c:if>

    <c:url var="loginUrl" value="/login.html"/>
    <form action="${loginUrl}" method="post">
        <c:if test="${not empty user}">
            <input type="hidden" name="id" value="${user.id}"><br>
        </c:if>
        Имя пользователя:<br>
        <input type="text" name="login"><br>
        <br>
        Пароль:<br>
        <input type="password" name="password"><br>
        <br>

        <button type="submit">войти</button>
    </form>
</u:page>
