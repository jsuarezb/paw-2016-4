<%@ tag description="DayOfWeek render" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="value" required="true" type="java.lang.Integer" %>

<c:choose>
    <c:when test="${ value == 1 }">Lunes</c:when>
    <c:when test="${ value == 2 }">Martes</c:when>
    <c:when test="${ value == 3 }">Miércoles</c:when>
    <c:when test="${ value == 4 }">Jueves</c:when>
    <c:when test="${ value == 5 }">Viernes</c:when>
    <c:when test="${ value == 6 }">Sábado</c:when>
    <c:when test="${ value == 7 }">Domingo</c:when>
    <c:otherwise>-</c:otherwise>
</c:choose>