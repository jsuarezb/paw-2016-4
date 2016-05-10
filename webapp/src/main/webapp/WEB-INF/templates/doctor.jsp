<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<z:base title="Perfil del doctor ${doctor.name} ${doctor.lastName}">
  <div class="panel panel-default">
    <div class="panel-body">
      <h1>Doctor</h1>
      <h2>${doctor.name}, ${doctor.lastName}</h2>
      <h3>${doctor.speciality}</h3>
      <p>${doctor.email}</p>
      <p>Telefonos:</p>
      <ul>
          <c:forEach var="phone" items="${ doctor.phones }">
            <li>${ phone }</li>
          </c:forEach>
      </ul>
    </div>
  </div>
</z:base>