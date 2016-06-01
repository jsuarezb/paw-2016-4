<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<z:base title="Perfil del doctor ${doctor.name} ${doctor.lastName}">
  <div class="panel panel-default">
    <div class="panel-body">
      <h1>Doctor</h1>
      <h2>${doctor.name}, ${doctor.lastName}</h2>
      <ul class="list-inline">
        <c:forEach var="speciality" items="${ doctor.specialities }">
          <h3><li>${ speciality.name }</li></h3>
        </c:forEach>
        <c:forEach var="institution" items="${ doctor.institutions }">
            <li>${ institution }</li>
        </c:forEach>
      </ul>
      <p>${doctor.email}</p>
    </div>
  </div>
</z:base>
