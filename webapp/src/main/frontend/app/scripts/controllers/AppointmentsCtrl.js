'use strict';
define(['ChoPidoTurnos', 'services/AppointmentsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('AppointmentsCtrl', ['AppointmentsService', '$routeParams', function(appointmentsService,$routeParams) {
    var _this = this;
    this.patientId = $routeParams.patientId;

      appointmentsService.getAppointmentsPatient(this.patientId).then(
        function (result) {
          console.log(result);
            _this.patientAppointment = result.data;
        }
      );
  }]);
});
