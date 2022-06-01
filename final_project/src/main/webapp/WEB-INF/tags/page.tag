<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="title" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@attribute name="mainPage" required="true" rtexprvalue="true" type="java.lang.Boolean" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!Doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>
        AirCompany<c:if test="${not empty title}">: ${title}</c:if>
    </title>
</head>
<body>

<h1 style="text-align: center">${title}:</h1>

<jsp:doBody/>
<br>
<c:if test="${mainPage}">
    <c:url var="mainPage" value="/index.html"/>
    <a href="${mainPage}">Главная страница</a>
</c:if>

</body>
</html>
