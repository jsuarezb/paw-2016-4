'use strict';
define(['ChoPidoTurnos','services/httpRequestBuilderService'], function(ChoPidoTurnos) {
  ChoPidoTurnos.service('AppointmentsService', [
    'httpRequestBuilderService',
    function (httpRequestBuilderService) {
      return {
        getAppointments: function() {
          return httpRequestBuilderService.buildHttpRequest('GET', 'appointments', null);
        },
        getAppointment: function(appointmentId) {
          return httpRequestBuilderService.buildHttpRequest('GET', 'appointments' + appointmentId, null);
        }
      };
    }]);
});
