'use strict';
define(['ChoPidoTurnos','services/httpRequestBuilderService'], function(ChoPidoTurnos) {
  ChoPidoTurnos.service('AppointmentsService', [
    'httpRequestBuilderService',
    function (httpRequestBuilderService) {
      return {
        getAppointments: function() {
          return httpRequestBuilderService.buildHttpRequest('GET', 'appointments', null);
        },
        searchAppointments: function(institution, speciality, neighborhood, weekOfYear, year, page) {
          return httpRequestBuilderService.buildHttpRequest('GET', 'appointments?speciality=' + speciality +
            '&neighborhood=' + neighborhood + '&institution=' + institution + '&weekOfYear=' + weekOfYear +
            '&year=' + year + '&page=' + page);
        },
        getAppointmentsPatient: function(patientId) {
          return httpRequestBuilderService.buildHttpRequest('GET', 'appointments/patient/' + patientId, null);
        },
        deleteAppointment: function(appointmentId) {
          return httpRequestBuilderService.buildHttpRequest('DELETE', 'appointments/' + patientId, null);
        },
        postAppointment: function() {
          return httpRequestBuilderService.buildHttpRequest('POST', 'appointments', null);
        }
      };
    }]);
});
