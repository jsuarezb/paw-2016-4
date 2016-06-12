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
                    <c:set var="doctor" value="${appointment.slot.worksIn.doctor}"/>
                    <c:set var="institution" value="${appointment.slot.worksIn.institution}"/>
                    <z:localDateTime date='${ appointment.date }' pattern='dd/MM/yyyy HH:mm' var="formattedDate" />
                    <div class="list-group-item">
                        <h3 class="list-group-item-heading">
                            <c:url value='/doctors/${ doctor.id}' var="doctorUrl"/>
                            <a href="${doctorUrl}">
                                ${ doctor.name } ${ doctor.lastName }
                            </a>
                        </h3>
                        <div class="list-group-item-text">
                            <dl class="dl-horizontal">
                              <dt>Especialidades</dt>
                              <dd>
                                <ul class="list-inline">
                                    <c:forEach var="speciality" items="${ appointment.slot.doctor.specialities }">
                                        <li>${ speciality.name }</li>
                                    </c:forEach>
                                </ul>
                              </dd>
                              <dt>Institución</dt>
                              <dd>
<<<<<<< f0d8bd498cde1d9530f0ff16e63624996d7b8087
                                <a href="<c:url value='/institutions/${ appointment.slot.institution.id }'/>">
                                    ${ appointment.slot.institution.name }
                                </a>
=======
                                <a href="/grupo4/institutions/${ institution.id }">${ institution.name }</a>
>>>>>>> More fixes
                              </dd>
                              <dt>Fecha</dt>
                              <dd>${ formattedDate }</dd>
                            </dl>
                            <button class="appointment-delete btn btn-danger btn-danger"
                                    data-url="<c:url value='/appointments/${appointment.id}'/>">
                                Cancelar turno
                            </button>
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