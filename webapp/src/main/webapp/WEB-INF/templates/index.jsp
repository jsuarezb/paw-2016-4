<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<z:base title="ChoPido Turnos">
  <div class="jumbotron">
    <h1>ChoPido Turnos</h1>
    <div id="home-specialities">
        <c:forEach var="speciality" items="${ specialities }">
          <a href="/grupo4/speciality/${ speciality.id }/appointment_slots">
            <span class="label label-primary">${ speciality.name }</span>
          </a>
        </c:forEach>
    </div>
  </div>
</z:base>
