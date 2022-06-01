<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<u:page title="Admin" mainPage="false">
    <ul>
        <li>
            <c:url var="userList" value="/user/list.html"/>
            <a href="${userList}">user list</a><br><br>
        </li>
        <li>
            <c:url var="employeeList" value="/employee/list.html"/>
            <a href="${employeeList}">employee list</a><br><br>
        </li>
        <li>
            <c:url var="flightList" value="/flight/list.html"/>
            <a href="${flightList}">flight list</a><br><br>
        </li>
    </ul>

    <br><br>
    <c:url var="logoutUrl" value="/logout.html"/>
    <a href="${logoutUrl}">logout</a><br><br>

</u:page>