<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<z:base title="Mis turnos">
  <h1>${ institution.name }</h1>
    <c:choose>
        <c:when test="${not empty appointments}">
            <div>
                <c:forEach var="appointment" items="${ appointments }">
                    <joda:format value='${ appointment.date }' pattern='yyyy-MM-dd HH:mm' var="formattedDate" />
                    <p>Turno para el ${ formattedDate }<p>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div>Sin turnos</div>
        </c:otherwise>
    </c:choose>
</z:base>