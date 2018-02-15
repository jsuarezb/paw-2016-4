'use strict';
define(['ChoPidoTurnos', 'services/appointmentsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('AppointmentsCtrl', ['appointmentsService', '$scope', '$state', function(appointmentsService, $scope, $state) {
    var _this = this;

    appointmentsService.getAppointmentsPatient().then(
      function (result) {
        console.log(result);
          _this.patientAppointment = result.data;
      }
    );

    $scope.cancelAppointment = function (appointment) {
      appointmentsService
        .deleteAppointment(appointment.id)
        .then(function (response) {
          if (response.status < 400) {
            $state.go('patientAppointments');
          }
        });
    };
  }]);
});
