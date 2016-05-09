<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<z:base title="ChoPido Turnos">
  <div class="jumbotron">
    <h1>ChoPido Turnos</h1>
    <form action="/grupo4/search" method="GET" class="form-inline">
        <div class="form-group">
            <select class="form-control" name="speciality">
                <c:forEach var="speciality" items="${ specialities }">
                    <option value="${ speciality.id }">${ speciality.name }</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <button class="btn btn-primary" type="submit">Buscar</button>
        </div>
    </form>
  </div>
</z:base>