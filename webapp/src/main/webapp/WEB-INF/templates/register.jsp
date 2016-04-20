<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<z:base title="Registro">
  <div class='container'>
    <form:form class="form-horizontal" method='post' action="/patients" modelAttribute="patientForm">
      <div class="form-group">
        <label for="name" class="col-sm-2 control-label">
          Name
        </label>
        <div class="col-sm-10">
          <form:input path="name" class="form-control" placeholder="Name"/>
          <form:errors path="name" />
        </div>
      </div>
      <div class="form-group">
        <label for="lastName" class="col-sm-2 control-label">
          Last Name
        </label>
        <div class="col-sm-10">
          <form:input path="lastName" class="form-control" placeholder="Last Name"/>
          <form:errors path="lastName" />
        </div>
      </div>
      <div class="form-group">
        <label for="email" class="col-sm-2 control-label">
          Email
        </label>
        <div class="col-sm-10">
          <form:input path="email" class="form-control" placeholder="Email"/>
          <form:errors path="email" />
        </div>
      </div>
      <div class="form-group">
        <label for="password" class="col-sm-2 control-label">
          Password
        </label>
        <div class="col-sm-10">
          <form:password class="form-control" path="password" placeholder="Password"/>
          <form:errors path="password" />
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-default">
            Register
          </button>
        </div>
      </div>
    </form:form>
  </div>
</z:base>