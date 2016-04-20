<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<z:base title="Perfil del doctor ${doctor.name} ${doctor.lastName}">
  <div class="panel panel-default">
    <div class="panel-body">
      <h1>Doctor</h1>
      <h2>${doctor.name}, ${doctor.lastName}</h2>
      <h3>${doctor.speciality}</h3>
      <p>${doctor.email}</p>
    </div>
  </div>
</z:base>