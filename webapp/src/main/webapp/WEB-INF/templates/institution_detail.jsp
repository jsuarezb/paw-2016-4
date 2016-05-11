

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>

<z:base title="Index">
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
          <a class="btn btn-default" href="/grupo4/institutions/${institution.id}/doctors">Ver doctores</a>
          <a class="btn btn-default" href="/grupo4/institutions/${institution.id}/specialities">Ver especialidades</a>
        </div>
      </div>
    </div>
  </div>
</z:base>
