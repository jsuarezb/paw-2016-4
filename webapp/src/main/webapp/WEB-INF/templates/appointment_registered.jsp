  <%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<z:base title="Turno reservado.">
  <div class="panel panel-default">
    <div class="panel-body">
      <h1>Turno reservado</h1>
      <p>
        <z:localDateTime date="${ appointment.date }" pattern="dd/MM/yyyy" var="appointmentDate"/>
        Turno reservado para el ${ appointmentDate }
      </p>
    </div>
  </div>
</z:base>