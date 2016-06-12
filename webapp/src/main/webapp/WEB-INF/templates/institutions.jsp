<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<z:base title="Instituciones">
  <h1>Instituciones</h1>
    <div class="row">
      <c:forEach var="institution" items="${institutions}">
        <div class="col-md-4">
          <div class="panel panel-default">
            <div class="panel-heading">
              <img class="img-responsive" src="http://placehold.it/400x300"/>
            </div>
            <div class="panel-body">
              <p class="lead">
                <c:url value='/institutions/${ institution.id }' var="institution_url"/>
                <a href="${institution_url}">
                  ${ institution.name }
                </a>
              </p>
              <p>
                ${ institution.address.streetName } ${institution.address.streetNumber},
                ${institution.address.city}, ${institution.address.state},
                ${institution.address.country }
              </p>
              <div class="text-center">
                <c:url value='/institutions/${institution.id}/specialities' var="specialities_url"/>
                <a href="${specialities_url}"
                   class="btn btn-primary">
                  Ver especialidades
                </a>
              </div>
            </div>
          </div>
        </div>

      </c:forEach>
    </div>
</z:base>
