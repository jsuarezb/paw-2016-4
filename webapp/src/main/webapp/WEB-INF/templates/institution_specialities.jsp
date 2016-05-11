<%--
  Created by IntelliJ IDEA.
  User: lucascarmona
  Date: 10/5/16
  Time: 9:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<z:base title="Index">
    <c:if test="${institution != null}">
        <h1>Lista de Especialidades en: <a href="/grupo4/institutions/${ institution.id }">${ institution.name }</a></h1>
        <h3>Direccion: ${ institution.address.streetName } ${ institution.address.streetNumber } ${ institution.address.apartment }, ${ institution.address.city }, ${ institution.address.state }, ${ institution.address.country }</h3>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Especialidad</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="speciality" items="${specialities}">
                <tr>
                    <td>${ speciality.name }</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${institution == null}">
        <h3>Oops! 404 Not Found</h3>
    </c:if>
</z:base>