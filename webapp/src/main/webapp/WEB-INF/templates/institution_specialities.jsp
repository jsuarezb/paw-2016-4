<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<z:base title="Especialidades - ${ institution.name }">
    <h1>Especialidades - <a href="/grupo4/institutions/${ institution.id }">${ institution.name }</a></h1>
    <h3>${ institution.address.streetName } ${ institution.address.streetNumber } ${ institution.address.apartment }, ${ institution.address.city }, ${ institution.address.state }, ${ institution.address.country }</h3>
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
</z:base>