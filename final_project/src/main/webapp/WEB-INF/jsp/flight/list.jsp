<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<u:page title="Список рейсов" mainPage="true">

    <c:if test="${not empty param.message}">
        <u:error message="${param.message}"/>
    </c:if>

    <table>
        <tr>
            <td>Откуда-Куда</td>
            <td>Количество билетов</td>
            <td>Бригада</td>
            <td>Статус</td>
        </tr>

        <c:forEach var="flight" items="${flights}">
            <tr>
                <td>
                    <c:url var="flightEditUrl" value="/flight/edit.html">
                        <c:param name="id" value="${flight.id}"/>
                    </c:url>
                    <a href="${flightEditUrl}">${flight.departurePoint} - ${flight.destination}
                </td>
                <td>${flight.numberTickets}</td>
                <td>${flight.brigade.name}</td>
                <td>${flight.status.name}</td>
            </tr>
        </c:forEach>

    </table>
    <ul>

    </ul>
    <c:url var="flightEditUrl" value="/flight/edit.html"/>
    <a href="${flightEditUrl}">Добавить новый рейс</a>
</u:page>