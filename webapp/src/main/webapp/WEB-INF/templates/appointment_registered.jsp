<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<z:base title="Turno reservado.">
  <div class="panel panel-default">
    <div class="panel-body">
      <h1>Turno reservado</h1>
      <p>Turno reservado para el <joda:format value="${ appointment.date }" pattern="dd/MM/yyyy"/></p>
    </div>
  </div>
</z:base>