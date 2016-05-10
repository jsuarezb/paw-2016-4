<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<z:base title="Perfil del doctor ${doctor.name} ${doctor.lastName}">
  <div class="panel panel-default">
    <div class="panel-body">
      <h1>Doctor</h1>
      <h2>${doctor.name}, ${doctor.lastName}</h2>
      <ul class="list-inline">
        <c:forEach var="speciality" items="${ doctor.specialities }">
          <h3><li>${ speciality.name }</li></h3>
        </c:forEach>
      </ul>
      <p>${doctor.email}</p>
    </div>
  </div>
</z:base>