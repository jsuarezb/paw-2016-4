'use strict';
define(['ChoPidoTurnos', 'services/appointmentsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('AppointmentsCtrl', ['appointmentsService', function(appointmentsService) {
    var _this = this;

    appointmentsService.getAppointmentsPatient().then(
      function (result) {
        console.log(result);
          _this.patientAppointment = result.data;
      }
    );
  }]);
});
