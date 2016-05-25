<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<z:base title="ChoPido Turnos">
    <div class="jumbotron">
        <h1>ChoPido Turnos</h1>
        <h4>Buscar por:</h4>
        <ul class="nav nav-pills nav-justified category-bar">
            <li class="active"><a data-toggle="pill" href="#speciality">Especialidad</a></li>
            <li><a data-toggle="pill" href="#doctor">Doctor</a></li>
            <!--    <li><a data-toggle="pill" href="#neighborhood">Barrio</a></li>  -->
        </ul>
        <div class="tab-content filter-container">
            <div id="speciality" class="tab-pane fade in active">
                <div class="row">
                    <div class="col-lg-12">
                        <fieldset class="form-group">
                            <label for="form_specialities" class="col-sm-2 control-label form-label">
                                Especialidad
                            </label>
                            <select class="form-control form-control-filter  input-lg" id="form_specialities">
                                <option id="option-speciality">Especialidad</option>
                                <c:forEach var="speciality" items="${ specialities }">
                                    <option id="option-${ speciality.name }">${ speciality.name }</option>
                                </c:forEach>
                            </select>

                            <label for="form_neighborhood" class="col-sm-2 control-label form-label">
                                Barrio
                            </label>


                            <select class="form-control form-control-filter  input-lg" id="form_neighborhood">
                                <option id="option-speciality">Barrio</option>
                                    <%-- <c:forEach var="neighborhood" items="${ neighborhoods }"> --%>
                                    <%--    <option id="option-${ neighborhood.name }">${ neighborhood.name }</option> --%>
                                    <%-- </c:forEach> --%>
                                <option id="option-1">Belgrano</option>
                                <option id="option-2">Colegiales</option>
                                <option id="option-3">Flores</option>
                                <option id="option-4">Nuñez</option>
                                <option id="option-5">Palermo</option>
                                <option id="option-6">Parque Patricios</option>
                                <option id="option-7">Recoleta</option>
                                <option id="option-8">Villa Devoto</option>
                                <option id="option-9">Villa Urquiza</option>
                                <option id="option-10">Villa del Parque</option>
                            </select>
                        </fieldset>
                    </div>
                </div>
            </div>
            <div id="doctor" class="tab-pane fade">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label form-label">
                                Nombre
                            </label>

                            <div class="col-sm-10 form-control-filter">
                                <input id="name" name="name" placeholder="Nombre" class="form-control input-lg" type="text" value="">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="form-group">
                            <label for="lastName" class="col-sm-2 control-label form-label">
                                Apellido
                            </label>
                            <div class="col-sm-10 form-control-filter">
                                <input id="lastName" name="lastName" placeholder="Apellido" class="form-control input-lg" type="text" value="">
                            </div>
                        </div>
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