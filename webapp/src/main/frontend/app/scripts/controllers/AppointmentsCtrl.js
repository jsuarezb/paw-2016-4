'use strict';
define(['ChoPidoTurnos', 'services/appointmentsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('AppointmentsCtrl', ['appointmentsService', '$scope', '$state', function(appointmentsService, $scope, $state) {
    var _this = this;
    this.alerts = [];

    appointmentsService.getAppointmentsPatient().then(
      function (result) {
        console.log(result);
          _this.appointments = result.data;
      }
    );

    this.cancelAppointment = function (appointment) {
      appointmentsService
        .deleteAppointment(appointment.id)
        .then(function (response) {
          if (response.status >= 400) {
            _this.alerts.push({message: 'No se ha podido cancelar el turno', type: 'danger'});
            return;
          }
          _this.alerts.push({message: 'Turno cancelado con éxito', type: 'success'});
          _this.appointments.splice(_this.appointments.indexOf(appointment), 1);
        });
    };
  }]);
});
