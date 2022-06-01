<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<c:choose>
  <c:when test="${not empty flight}">
    <c:set var="title" value="Редактирование рейса ${flight.departurePoint} - ${flight.destination}"/>
  </c:when>
  <c:when test="${empty flight}">
    <c:set var="title" value="Создание рейса"/>
  </c:when>
</c:choose>
<u:page title="${title}" mainPage="true">

  <c:url var="flightSaveUrl" value="/flight/save.html"/>
  <form action="${flightSaveUrl}" method="post">
    <c:if test="${not empty flight}">
      <input type="hidden" name="id" value="${flight.id}"><br>
      <input type="hidden" name="status" value="${flight.status}">
    </c:if>
    Количество билетов:<br>
    <input type="number" name="numberTickets" value="${flight.numberTickets}">
    <br><br>

    Время вылета:<br>
    <input type="datetime-local" name="departureTime" value="${requestScope.departureTime}">
    <br><br>

    Время прибытия:<br>
    <input type="datetime-local" name="arrivalTime" value="${requestScope.arrivalTime}">
    <br><br>

    Место отправления:<br>
    <input type="text" name="departurePoint" value="${flight.departurePoint}">
    <br><br>

    Место назначения:<br>
    <input type="text" name="destination" value="${flight.destination}">
    <br><br>

    Бригада:<br>
    <select name="brigadeId">
      <c:forEach var="brigade" items="${brigades}">
        <c:choose>
          <c:when test="${brigade.id == flight.brigade.id}">
            <c:set var="selected" value="selected"/>
          </c:when>
          <c:otherwise>
            <c:remove var="selected"/>
          </c:otherwise>
        </c:choose>
        <option value="${brigade.id}" ${selected}>${brigade.name}</option>
      </c:forEach>
    </select>
    <br><br>

    <button type="submit">сохранить</button>

    <br><br>
  </form>

  <c:if test="${not empty flight}">
    <c:url var="flightDeleteUrl" value="/flight/delete.html"></c:url>
    <form action="${flightDeleteUrl}" method="post">
      <input name="id" type="hidden" value="${flight.id}">
      <button type="submit">Удалить Рейс</button>
    </form>
  </c:if>
</u:page>

