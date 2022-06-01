<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<u:page title="Dispatcher" mainPage="false">
    <ul>
        <li>
            <c:url var="brigadeList" value="/brigade/list.html"/>
            <a href="${brigadeList}">brigade list</a><br><br>
        </li>
    </ul>
    <br><br>
    <c:url var="logoutUrl" value="/logout.html"/>
    <a href="${logoutUrl}">logout</a><br><br>

</u:page>