<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<u:page title="Список бригад" mainPage="true">

    <c:if test="${not empty param.message}">
        <u:error message="${param.message}"/>
    </c:if>

    <ul>

        <c:forEach var="brigade" items="${brigades}">
            <c:url var="brigadeEditUrl" value="/brigade/edit.html">
                <c:param name="id" value="${brigade.id}"/>
            </c:url>
            <li>
                <a href="${brigadeEditUrl}">${brigade.name}</a>
                <select name="employee">
                    <c:forEach var="employee" items="${brigade.employees}">
                        <option value="${employee.id}">${employee.login}</option>
                    </c:forEach>
                </select>
            </li>
        </c:forEach>
    </ul>
    <c:url var="brigadeEditUrl" value="/brigade/edit.html"/>
    <a href="${brigadeEditUrl}">Сформировать новую бригаду</a>

</u:page>