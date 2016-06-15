<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<z:base title="Turnos - ${ speciality.name }">
    <h1>Turnos - ${ speciality.name }</h1>
    <c:choose>
        <c:when test="${ not empty appointments }">
            <table class="table table-bordered">
                <thead>
                    <th>Día</th>
                    <th>Fecha</th>
                    <th>Duración</th>
                    <th>Doctor</th>
                    <th>Institucion</th>
                    <th>Acciones</th>
                </thead>
                <tbody>
                    <c:forEach var="appointment" items="${ appointments }">
                        <z:localDateTime date='${ appointment.date }'
                                         pattern='yyyy-MM-dd HH:mm'
                                         var='formattedDate' />
                        <z:localDateTime date='${ appointment.date }'
                                         pattern='dd/MM/yyyy HH:mm'
                                         var='readableDate' />
                        <c:set var="slot" value="${appointment.slot}"/>
                        <c:set var="doctor" value="${slot.worksIn.doctor}"/>
                        <c:set var="institution" value="${slot.worksIn.institution}"/>
                        <tr>
                            <td>
                                <z:dayOfWeek value="${slot.dayOfWeek}"/>
                            </td>
                            <td>${ readableDate }</td>
                            <td>1 hs</td>
                            <td>
                                <a href="/grupo4/doctors/${ doctor.id }"> Dr. ${ doctor.name }, ${ doctor.lastName }</a>
                            </td>
                            <td>
                                <a href="/grupo4/institutions/${ institution.id }">${ institution.name }</a>
                            </td>
                            <td>
                                <c:if test="${ patient != null }">
                                    <c:url value="/appointments" var="url" />
                                    <form:form modelAttribute="newAppointment" action="${url}"
                                               method="post" enctype="application/x-www-form-urlencoded">
                                        <form:input path="doctorId" type="hidden" value="${ doctor.id }" />
                                        <form:input path="slotId" type="hidden" value="${ slot.id }" />
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
        <z:localDateTime date="${ prevWeek }" pattern="yyyy-MM-dd" var="formattedPrevWeek" />
        <z:localDateTime date="${ nextWeek }" pattern="yyyy-MM-dd" var="formattedNextWeek" />
        <z:pager prevUrl="/grupo4/speciality/${ speciality.id }/appointment_slots?date=${ formattedPrevWeek }"
                 nextUrl="/grupo4/speciality/${ speciality.id }/appointment_slots?date=${ formattedNextWeek }"
                 showPrev="${ null != prevWeek }"/>
    </nav>
</z:base>
