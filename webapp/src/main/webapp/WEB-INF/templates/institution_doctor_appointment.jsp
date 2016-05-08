<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<z:base title="Index">
  <a href="/institutions/${ institution.id }/doctors/${ doctor.id }">
    <h1>${ institution.name } / ${ doctor.name } ${ doctor.lastName }</h1>
  </a>
    <c:choose>
        <c:when test="${ not empty appointments }">
            <table class="table table-bordered">
               <thead>
                 <th>Día</th>
                 <th>Hora inicio</th>
                 <th>Duración</th>
                 <th>Institución</th>
                 <th>Acciones</th>
               </thead>
               <tbody>
                    <c:forEach var="appointment" items="${ appointments }">
                      <joda:format value='${ appointment.date }' pattern='yyyy-MM-dd HH:mm' var="formattedDate" />
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
                        <td>${ appointment.slot.hour }:00</td>
                        <td>1 hs</td>
                        <td>${ institution.address }</td>
                        <td>
                            <form:form modelAttribute="newAppointment" action="/appointments" method="post" enctype="application/x-www-form-urlencoded">
                                <form:input path="patientId" type="hidden" value="${ user.id }" />
                                <form:input path="doctorId" type="hidden" value="${ doctor.id }" />
                                <form:input path="slotId" type="hidden" value="${ appointment.slot.id }" />
                                <form:input path="startDate" type="hidden" value="${ formattedDate }" />
                                <button type="submit" class="btn btn-success">Reservar</button>
                            </form:form>
                        </td>
                      <tr>
                    </c:forEach>
               </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>No hay turnos disponibles</p>
        </c:otherwise>
    </c:choose>
</z:base>