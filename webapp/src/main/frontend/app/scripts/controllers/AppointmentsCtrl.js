'use strict';
define(['ChoPidoTurnos', 'services/AppointmentsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('AppointmentsCtrl', ['AppointmentsService', function(appointmentsService) {
    var _this = this;

    appointmentsService.getAppointments().then(
      function(result) {
        console.log(result);
        _this.appointments = result.data;
      }
    );
  }]);
});
