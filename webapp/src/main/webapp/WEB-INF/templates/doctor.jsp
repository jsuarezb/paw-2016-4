<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<z:base title="Perfil del doctor ${doctor.name} ${doctor.lastName}">
  <div class="panel panel-default">
    <div class="panel-body">
        <c:choose>
        <c:when test="${ doctor != null}">
          <h1>Doctor</h1>
          <h2>${doctor.name}, ${doctor.lastName}</h2>
          <ul class="list-inline">
            <c:forEach var="speciality" items="${ doctor.specialities }">
              <h3><li>${ speciality.name }</li></h3>
            </c:forEach>
          </ul>
          <p>${doctor.email}</p>
        </c:when>
        <c:otherwise>
            <h1>Oops! 404 Not Found</h1>
        </c:otherwise>
        </c:choose>
    </div>
  </div>
</z:base>