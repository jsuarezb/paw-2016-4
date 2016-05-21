<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<z:base title="Registro">
  <div class='container'>
    <h2>
      Ingrese sus datos
    </h2>
    <form:form method='post' action="/grupo4/patients" modelAttribute="patientForm">
      <div class="form-group">
        <label for="name" class="control-label">
          Nombre
        </label>
        <form:input path="name" class="form-control" placeholder="Nombre"/>
        <form:errors path="name" />
      </div>
      <div class="form-group">
        <label for="lastName" class="control-label">
          Apellido
        </label>
        <form:input path="lastName" class="form-control" placeholder="Apellido"/>
        <form:errors path="lastName" />
      </div>
      <div class="form-group">
        <label for="email" class="control-label">
          Email
        </label>
        <form:input path="email" class="form-control" placeholder="Email"/>
        <form:errors path="email" />
      </div>
      <div class="form-group">
        <label for="password" class="control-label">
          Password
        </label>
        <form:password class="form-control" path="password" placeholder="Password"/>
        <form:errors path="password" />
      </div>
      <div class="form-group">
        <button type="submit" class="btn btn-success">
          Registrarse
        </button>
      </div>
    </form:form>
  </div>
</z:base>
