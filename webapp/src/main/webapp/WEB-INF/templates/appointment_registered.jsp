<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<z:base title="Turno reservado.">
  <div class="panel panel-default">
    <div class="panel-body">
      <h1>Turno reservado</h1>
      <p>Turno reservado para el <fmt:formatDate value="${ appointment.date }" pattern="dd/MM/yyyy"/></p>
    </div>
  </div>
</z:base>