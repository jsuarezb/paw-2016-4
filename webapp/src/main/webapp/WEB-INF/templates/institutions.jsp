<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<z:base title="Index">
  <h1>Instituciones</h1>
  <table class="table table-bordered">
    <thead>
      <tr>
        <th>Institución</th>
        <th>Dirección</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="institution" items="${institutions}">
        <tr>
          <td>
            <a href="/institutions/${ institution.id }">${ institution.name }</a>
          </td>
          <td>
            ${ institution.address.streetName } ${institution.address.streetNumber}, ${institution.address.city}, ${institution.address.state}, ${institution.address.country }
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</z:base>