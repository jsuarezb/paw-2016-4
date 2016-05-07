<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<z:base title="Mis turnos">
  <h1>Mis turnos</h1>
    <c:choose>
        <c:when test="${not empty appointments}">
            <div class="list-group">
                <c:forEach var="appointment" items="${ appointments }">
                    <joda:format value='${ appointment.date }' pattern='dd/MM/yyyy HH:mm' var="formattedDate" />
                    <div class="list-group-item">
                        <h4 class="list-group-item-heading">${ appointment.slot.doctor.speciality } - Dr. ${ appointment.slot.doctor.name } ${ appointment.slot.doctor.lastName }</h4>
                        <div class="list-group-item-text">
                            <dl class="dl-horizontal">
                              <dt>Institución</dt>
                              <dd>
                                <a href="/institutions/${ appointment.slot.institution.id }">${ appointment.slot.institution.name }</a>
                              </dd>
                              <dt>Fecha</dt>
                              <dd>${ formattedDate }</dd>
                            </dl>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div>Sin turnos</div>
        </c:otherwise>
    </c:choose>
</z:base>