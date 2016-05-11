

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<z:base title="Index">
  <div class="panel panel-default">
    <div class="panel-body">
      <h1>Institucion</h1>
      <h2>${institution.name}</h2>
      <h3>${institution.address.streetName} ${institution.address.streetNumber}</h3>
      <p>${institution.address.city} - ${institution.address.state}, ${institution.address.country}</p>
      <div class="container">
        <h2>Mas info:</h2>
        <a href="/grupo4/institutions/${institution.id}/doctors"><button type="button" class="btn btn-primary">Doctores</button></a>
        <a href="/grupo4/institutions/${institution.id}/specialities"><button type="button" class="btn btn-primary">Especialidades</button></a>
      </div>
    </div>
  </div>
</z:base>
