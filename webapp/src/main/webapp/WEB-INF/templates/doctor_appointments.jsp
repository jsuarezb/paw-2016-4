<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<z:base title="Turnos - ${ doctor.name } ${ doctor.lastName }">
    <h1>Turnos - ${ doctor.name } ${ doctor.lastName }</h1>
    <c:choose>
        <c:when test="${ not empty appointments }">
            <table class="table table-bordered">
               <thead>
                 <th>Día</th>
                 <th>Fecha</th>
                 <th>Duración</th>
                 <th>Institución</th>
                 <th>Acciones</th>
               </thead>
               <tbody>
                    <c:forEach var="appointment" items="${ appointments }">
                      <joda:format value='${ appointment.date }' pattern='yyyy-MM-dd HH:mm' var="formattedDate" />
                      <joda:format value='${ appointment.date }' pattern='dd/MM/yyyy HH:mm' var='readableDate' />
                      <tr>
                        <td>
                            <c:choose>
                                <c:when test="${ appointment.slot.dayOfWeek == 1 }">Lunes</c:when>
                                <c:when test="${ appointment.slot.dayOfWeek == 2 }">Martes</c:when>
                                <c:when test="${ appointment.slot.dayOfWeek == 3 }">Miércoles</c:when>
                                <c:when test="${ appointment.slot.dayOfWeek == 4 }">Jueves</c:when>
                                <c:when test="${ appointment.slot.dayOfWeek == 5 }">Viernes</c:when>
                                <c:when test="${ appointment.slot.dayOfWeek == 6 }">Sábado</c:when>
                                <c:when test="${ appointment.slot.dayOfWeek == 7 }">Domingo</c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${ readableDate }</td>
                        <td>1 hs</td>
                        <td>
                            <a href="/grupo4/institutions/${ appointment.slot.institution.id }">${ appointment.slot.institution.name }</a>
                        </td>
                        <td>
                            <c:if test="${ patient != null }">
                                <form:form modelAttribute="newAppointment" action="/grupo4/appointments" method="post" enctype="application/x-www-form-urlencoded">
                                    <form:input path="patientId" type="hidden" value="${ user.id }" />
                                    <form:input path="doctorId" type="hidden" value="${ doctor.id }" />
                                    <form:input path="slotId" type="hidden" value="${ appointment.slot.id }" />
                                    <form:input path="startDate" type="hidden" value="${ formattedDate }" />
                                    <button type="submit" class="btn btn-success">Reservar</button>
                                </form:form>
                            </c:if>
                        </td>
                      <tr>
                    </c:forEach>
               </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>No hay turnos disponibles esta semana</p>
        </c:otherwise>
    </c:choose>
    <nav>
        <joda:format value="${ prevWeek }" pattern="yyyy-MM-dd" var="formattedPrevWeek" />
        <joda:format value="${ nextWeek }" pattern="yyyy-MM-dd" var="formattedNextWeek" />
        <ul class="pager">
            <c:choose>
                <c:when test="${ null != prevWeek }">
                    <c:set value="previous" var="previousWeekClass" />
                </c:when>
                <c:otherwise>
                    <c:set value="previous disabled" var="previousWeekClass" />
                </c:otherwise>
            </c:choose>
            <li class="${ previousWeekClass }">
                <a href="/grupo4/doctors/${ doctor.id }/appointment_slots?date=${ formattedPrevWeek }">&lt; Semana anterior</a>
            </li>
            <li class="next">
                <a href="/grupo4/doctors/${ doctor.id }/appointment_slots?date=${ formattedNextWeek }">Semana siguiente &gt;</a>
            </li>
        </ul>
    </nav>
</z:base>
