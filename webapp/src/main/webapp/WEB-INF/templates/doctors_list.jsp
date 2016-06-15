<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:choose>
    <c:when test="${ showNext }">
        <c:url value="/search_by_doctor?name=${ name }&lastName=${ lastName }&page=${ page + 1 }"
               var="nextUrl" />
    </c:when>
    <c:otherwise>
        <c:set var="nextUrl" value="#" />
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${ page - 1 >= 0 }">
        <c:url value="/search_by_doctor?name=${ name }&lastName=${ lastName }&page=${ page - 1 }"
               var="prevUrl"/>
    </c:when>
    <c:otherwise>
        <c:url value="#"
               var="prevUrl"/>
    </c:otherwise>
</c:choose>

<z:base title="ChoPido Turnos">
    <h1>Búsqueda de doctores</h1>
    <c:choose>
        <c:when test="${ not empty doctors }">
            <c:forEach items="${ doctors }" var="doctor">
                <z:doctorProfileList doctor="${ doctor }"/>
            </c:forEach>
            <ul class="pager">
                <c:choose>
                    <c:when test="${ showPrev }">
                        <c:set value="previous" var="prevPageClass" />
                    </c:when>
                    <c:otherwise>
                        <c:set value="previous disabled" var="prevPageClass" />
                    </c:otherwise>
                </c:choose>
                <c:set value="${ showNext ? 'next' : 'next disabled' }" var="nextPageClass"/>
                <li class="${ prevPageClass }">
                    <a href="${ prevUrl }">&lt; Anterior</a>
                </li>
                <li class="${ nextPageClass }">
                    <a href="${ nextUrl }">Siguiente &gt;</a>
                </li>
            </ul>
        </c:when>
        <c:otherwise>
            <p>No se encontraron doctores en la búsqueda</p>
        </c:otherwise>
    </c:choose>
</z:base>