<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<z:base title="Iniciar sesión">
  <div class='container'>
    <h2>
      Iniciar sesión
    </h2>
    <c:url value='/login' var="url"/>
    <form:form method='post' action="${url}" modelAttribute="loginForm">
      <div class="form-group">
        <label for="email" class="control-label">Email</label>
        <form:input type="email" path="email" class="form-control" placeholder="Email"/>
        <form:errors path="email" />
      </div>
      <div class="form-group">
        <label for="password" class="control-label">Password</label>
        <form:password path="password" class="form-control" placeholder="Password"/>
        <form:errors path="password" />
      </div>
      <div class="form-group">
        <div class="checkbox">
          <label>
            <input type="checkbox" name="rememberme" value="">
            Remember me
          </label>
        </div>
      </div>
      <div class="form-group">
        <button type="submit" class="btn btn-success">Log in</button>
      </div>
    </form:form>
  </div>
</z:base>