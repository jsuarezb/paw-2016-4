'use strict';
define(['ChoPidoTurnos', 'services/AppointmentsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('AppointmentsCtrl', ['AppointmentsService', function(appointmentsService) {
    var _this = this;

      appointmentsService.getAppointmentsPatient(5).then(
        function (result) {
          console.log(result);
            _this.patientAppointment = result.data;
        }
      );
  }]);
});
