<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<z:base title="Iniciar sesión">
  <div class='container'>
    <form:form class="form-horizontal" method='post' action="/login" modelAttribute="loginForm">
      <div class="form-group">
        <label for="email" class="col-sm-2 control-label">Email</label>
        <div class="col-sm-10">
          <form:input type="email" path="email" class="form-control" placeholder="Email"/>
          <form:errors path="email" />
        </div>
      </div>
      <div class="form-group">
        <label for="password" class="col-sm-2 control-label">Password</label>
        <div class="col-sm-10">
          <form:password path="password" class="form-control"placeholder="Password"/>
          <form:errors path="password" />
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-default">Log in</button>
        </div>
      </div>
    </form:form>
  </div>
</z:base>