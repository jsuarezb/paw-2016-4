<%@ tag description="Pager" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="nextUrl" required="true" type="java.lang.String" %>
<%@ attribute name="prevUrl" required="true" type="java.lang.String" %>
<%@ attribute name="showPrev" required="true" type="java.lang.Boolean" %>

<ul class="pager">
    <c:choose>
        <c:when test="${showPrev}">
            <c:set value="previous" var="previousWeekClass" />
        </c:when>
        <c:otherwise>
            <c:set value="previous disabled" var="previousWeekClass" />
        </c:otherwise>
    </c:choose>
    <li class="${ previousWeekClass }">
        <a href="/grupo4/speciality/${ speciality.id }/appointment_slots?date=${ formattedPrevWeek }">&lt; Semana anterior</a>
    </li>
    <li class="next">
        <a href="/grupo4/speciality/${ speciality.id }/appointment_slots?date=${ formattedNextWeek }">Semana siguiente &gt;</a>
    </li>
</ul>