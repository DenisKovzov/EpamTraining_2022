<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<u:page title="User" mainPage="false">

    <c:if test="${not empty param.message}">
        <u:error message="${param.message}"/>
    </c:if>

    <c:url var="userEditUrl" value="/user/edit.html">
        <c:param name="id" value="${session_user.id}"/>
    </c:url>
    <a href="${userEditUrl}">редактирование пользователя</a>

    <br><br>
    <c:url var="logoutUrl" value="/logout.html"/>
    <a href="${logoutUrl}">logout</a><br><br>

</u:page>