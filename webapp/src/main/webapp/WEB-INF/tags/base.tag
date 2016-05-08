<%@tag description="Base template" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="title"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="name" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">

    <title>${title}</title>

    <!-- Bootstrap core CSS -->
    <link href="/grupo4/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <link href="/grupo4/css/styles.css" rel="stylesheet">
  </head>

  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/grupo4">ChoPido Turnos</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="/grupo4/institutions">Instituciones</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <c:if test="${patient == null}">
            <li><a href="/grupo4/login">Log in</a></li>
            <li><a href="/grupo4/register">Register</a></li>
            </c:if>
            <c:if test="${patient != null}">
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${patient.email} <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="/grupo4/profile">Profile</a></li>
                  <li><a href="/grupo4/appointments">Mis turnos</a></li>
                  <li><a href="/grupo4/logout">Log out</a></li>
                </ul>
              </li>
            </c:if>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div class="container container-main">
      <jsp:doBody/>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="/grupo4/bower_components/jquery/dist/jquery.js"></script>
    <script src="/grupo4/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
  </body>
</html>
