<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<z:base title="ChoPido Turnos">
    <div class="jumbotron">
        <h1>ChoPido Turnos</h1>
        <h4>Buscar por:</h4>
        <ul class="nav nav-pills nav-justified category-bar">
            <li class="active" data-form-id="searchBySpecialityForm">
                <a data-toggle="pill" href="#speciality">
                    Especialidad
                </a>
            </li>
            <li>
                <a data-toggle="pill" href="#doctor">
                    Doctor
                </a>
            </li>
            <!--    <li><a data-toggle="pill" href="#neighborhood">Barrio</a></li>  -->
        </ul>
        <div class="tab-content filter-container">
            <div id="speciality" class="tab-pane fade in active">
                <div class="row">
                    <div class="col-lg-6 col-lg-offset-3">
                        <c:url value='/search_by_speciality' var="url"/>
                        <form:form class="form-horizontal" method='get' action="${url}" modelAttribute="searchBySpecialityForm">
                            <div class="form-group">
                                <label for="speciality" class="col-sm-3 control-label form-label">
                                    Especialidad
                                </label>
                                <div class="col-sm-9">
                                    <form:select id="specialityId" path="specialityId"
                                                 class="form-control form-control-filter input-lg">
                                        <form:option value="-" label="Seleccione una Especialidad"/>
                                        <c:forEach var="speciality" items="${ specialities }">
                                            <form:option id="option-${ speciality.name }"
                                                         label="${ speciality.name }"
                                                         value="${ speciality.id }"/>
                                        </c:forEach>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="neighborhood" class="col-sm-3 control-label form-label">
                                    Barrio
                                </label>
                                        <%-- <c:forEach var="neighborhood" items="${ neighborhoods }"> --%>
                                        <%--    <option id="option-${ neighborhood.name }">${ neighborhood.name }</option> --%>
                                        <%-- </c:forEach> --%>
                                <div class="col-sm-9">
                                    <form:select path="neighborhood"
                                                 class="form-control form-control-filter input-lg"
                                                 id="neighborhood">
                                        <form:option value="-" label="Seleccione un Barrio"/>
                                        <form:option id="option-1" label="Belgrano" value="Belgrano"/>
                                        <form:option id="option-2" label="Colegiales" value="Colegiales"/>
                                        <form:option id="option-3" label="Flores" value="Flores"/>
                                        <form:option id="option-4" label="Nuñez" value="Nuñez"/>
                                        <form:option id="option-5" label="Palermo" value="Palermo"/>
                                        <form:option id="option-6" label="Parque Patricios" value="Parque Patricios"/>
                                        <form:option id="option-7" label="Recoleta" value="Recoleta"/>
                                        <form:option id="option-8" label="Villa Devoto" value="Villa Devoto"/>
                                        <form:option id="option-9" label="Villa Urquiza" value="Villa Urquiza"/>
                                        <form:option id="option-10" label="Villa del Parque" value="Villa del Parque"/>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-success">
                                    Registrarse
                                </button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
            <div id="doctor" class="tab-pane fade">
                <div class="row">
                    <div class="col-lg-6 col-lg-offset-3">
                        <c:url value='/search_by_doctor' var="searchByNameUrl"/>
                        <form class="form-horizontal" method='get' action="${ searchByNameUrl }"
                              modelAttribute="searchByDoctorForm">
                            <div class="form-group">
                                <label for="name" class="col-sm-3 control-label form-label">
                                    Nombre
                                </label>
                                <div class="col-sm-9 form-control-filter">
                                    <input id="name" name="name" class="form-control input-lg"
                                           placeholder="Nombre" type="text"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="lastName" class="col-sm-3 control-label form-label">
                                    Apellido
                                </label>
                                <div class="col-sm-9 form-control-filter">
                                    <input id="lastName" name="lastName" class="form-control input-lg"
                                           placeholder="Apellido" type="text"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-success">
                                    Buscar
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <button type="button" class="btn btn-success btn-search">Buscar</button>
            </div>
        </div>
    </div>
</z:base>