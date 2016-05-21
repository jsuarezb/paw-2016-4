<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<z:base title="Doctores - ${ institution.name }">
    <h1>Doctores - <a href="/grupo4/institutions/${ institution.id }">${ institution.name }</a></h1>
    <h3>${ institution.address.streetName } ${ institution.address.streetNumber } ${ institution.address.apartment }, ${ institution.address.city }, ${ institution.address.state }, ${ institution.address.country }</h3>
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>Doctor</th>
          <th>Especialidad</th>
          <th>Correo Electronico</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="doctor" items="${doctors}">
          <tr>
            <td>
              <a href="/grupo4/doctors/${doctor.id}">
                ${ doctor.name } ${ doctor.lastName }
              </a>
            </td>
            <td>
              <ul class="list-inline">
                <c:forEach var="speciality" items="${ doctor.specialities }">
                <li>${ speciality.name }</li>
                </c:forEach>
              </ul>
            </td>
            <td>${ doctor.email }</td>
            <c:if test="${patient != null}">
              <td>
                <a class="btn btn-success"
                   href="/grupo4/institutions/${ institution.id }/doctors/${ doctor.id }/appointment_slots">
                  Pedir turno
                </a>
              </td>
            </c:if>
          </tr>
          </a>
        </c:forEach>
      </tbody>
    </table>
</z:base>
