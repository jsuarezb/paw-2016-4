<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<z:base title="Index">
  <c:if test="${institution.id == 1 || institution.id == 2}">
    <h1>Lista de Doctores en: <a href="/grupo4/institutions/${ institution.id }">${ institution.name }</a></h1>
    <h3>Direccion: ${ institution.address.streetName } ${ institution.address.streetNumber } ${ institution.address.apartment }, ${ institution.address.city }, ${ institution.address.state }, ${ institution.address.country }</h3>
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>Nombre</th>
          <th>Apellido</th>
          <th>Especialidad</th>
          <th>Correo Electronico</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="doctor" items="${doctors}">
          <tr>
            <td>
              <a href="/grupo4/doctors/${ doctor.id }">
                ${ doctor.name }
              </a>
            </td>
            <td>
              <a href="/grupo4/doctors/${ doctor.id }">
                ${ doctor.lastName }
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
          </tr>
          </a>
        </c:forEach>
      </tbody>
    </table>
  </c:if>
  <c:if test="${institution.id != 1 && institution.id != 2}">
  <h3>No hay instituciones con identificador numero: ${ wrongId }</h3>
  </c:if>
</z:base>