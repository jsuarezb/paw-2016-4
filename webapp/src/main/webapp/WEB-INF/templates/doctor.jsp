<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<z:base title="Perfil del doctor ${doctor.name} ${doctor.lastName}">
  <div class="panel panel-default">
    <div class="panel-heading">
      <h2>Dr. ${doctor.name}, ${doctor.lastName}</h2>
    </div>
    <div class="panel-body">
      <ul class="list-inline">
        <c:forEach var="speciality" items="${ doctor.specialities }">
          <h3><li>${ speciality.name }</li></h3>
        </c:forEach>
        <c:forEach var="worksIn" items="${ doctor.worksIn }">
          <li>${ worksIn.institution }</li>
        </c:forEach>
      </ul>
      <p>${doctor.email}</p>
    </div>
  </div>
</z:base>
