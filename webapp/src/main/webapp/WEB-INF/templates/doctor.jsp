<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<z:base title="Perfil del doctor ${doctor.name} ${doctor.lastName}">
  <div class="row">
    <div class="col-md-8 col-md-offset-2">
      <div class="panel panel-primary">
        <div class="panel-heading">
          <h1>${doctor.lastName}, ${doctor.name}</h1>
        </div>
        <div class="panel-body">
          <dl class="dl-horizontal">
            <dt>Especialidades<dt>
            <dd>
              <ul class="list-inline">
                <c:forEach var="speciality" items="${ doctor.specialities }">
                  <li>${ speciality.name }</li>
                </c:forEach>
              </ul>
            </dd>
            <dt>Email</dt>
            <dd>${doctor.email}</dd>
            <c:if test="${ not empty doctor.phones }">
              <dt>Telefonos</dt>
              <dd>
                <ul class="list-inline">
                  <c:forEach var="phone" items="${ doctor.phones }">
                    <li>${ phone }</li>
                  </c:forEach>
                </ul>
              </dd>
            </c:if>
          </dl>
          <div class="text-center">
            <a class="btn btn-success" href="/grupo4/doctors/${doctor.id}/appointment_slots">
              Pedir turno con ${doctor.name}
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</z:base>
