<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<z:base title="Index">
  <a href="/institutions/${ institution.id }/doctors/${ doctor.id }">
    <h1>${ institution.name } / ${ doctor.name } ${ doctor.lastName }</h1>
  </a>
  <table class="table table-bordered">
    <thead>
      <th>Hora inicio</th>
      <th>Duración</th>
      <th>Institución</th>
    </thead>
    <c:forEach var="appointment" items="${appointments}">
      <tr>
        <td>${ appointment.slot.hour }:00</td>
        <td>1 hs</td>
        <td>${ appointment.slot.institution.address }</td>
      <tr>
    </c:forEach>
  </table>
</z:base>