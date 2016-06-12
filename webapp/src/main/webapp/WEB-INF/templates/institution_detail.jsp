<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<z:base title="${ institution.name }">
  <div class="row">
    <div class="col-md-8 col-md-offset-2">
      <div class="panel panel-primary">
        <div class="panel-heading">
          <h1>${ institution.name }</h1>
        </div>
        <div class="panel-body">
          <img class="img-responsive center-block" src="http://placehold.it/400x300"/>
          <br>
          <address>
            ${ institution.address.streetName } ${ institution.address.streetNumber }<br>
            ${ institution.address.city } - ${ institution.address.state }, ${ institution.address.country }
          </address>
          <c:url value='/institutions/${institution.id}/doctors' var="doctors_url"/>
          <a class="btn btn-default"
             href="${doctors_url}">
            Ver doctores
          </a>
          <c:url value='/institutions/${institution.id}/specialities' var="specialities_url"/>
          <a class="btn btn-default"
             href="${specialities_url}">
            Ver especialidades
          </a>
        </div>
      </div>
    </div>
  </div>
</z:base>
