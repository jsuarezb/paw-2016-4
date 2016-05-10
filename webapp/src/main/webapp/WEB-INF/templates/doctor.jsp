<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<z:base title="Perfil del doctor ${doctor.name} ${doctor.lastName}">
  <div class="row">
    <div class="col-md-8 col-md-offset-2">
      <div class="panel panel-primary">
        <div class="panel-heading">
          <h1>${doctor.lastName}, ${doctor.name}</h1>
        </div>
        <div class="panel-body">
          <dl class="dl-horizontal">
            <dt>Especialidad:</dt>
            <dd>${doctor.speciality}</dd>
            <dt>Email:</dt>
            <dd>${doctor.email}</dd>
          </dl>
          <div class="text-center">
            <a class="btn btn-success" href="/grupo4/doctors/${doctor.id}/appointment_slots">
              Pedir turno con ${doctor.name}
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</z:base>