<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<z:base title="Mis turnos">
  <h1>${ institution.name }</h1>
    <c:choose>
        <c:when test="${not empty appointments}">
            <div>
                <c:forEach var="appointment" items="${ appointments }">
                    <fmt:formatDate value='${ appointment.date }' pattern='yyyy-MM-dd hh:mm' var="formattedDate" />
                    <p>Turno para el ${ formattedDate }<p>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div>Sin turnos</div>
        </c:otherwise>
    </c:choose>
</z:base>