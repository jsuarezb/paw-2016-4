'use strict';
define(['ChoPidoTurnos','services/httpRequestBuilderService'], function(ChoPidoTurnos) {
  ChoPidoTurnos.service('appointmentsService', [
    'httpRequestBuilderService',
    function (httpRequestBuilderService) {
      return {
        getAppointments: function() {
          return httpRequestBuilderService.buildHttpRequest('GET', 'appointments', null);
        },
        searchAppointments: function(institution, speciality, neighborhood, weekOfYear, year, doctorId, page) {
          return httpRequestBuilderService.buildHttpRequest('GET', 'appointments?speciality=' + speciality +
            '&neighborhood=' + neighborhood + '&institution=' + institution + '&weekOfYear=' + weekOfYear +
            '&year=' + year + '&doctorId=' + doctorId + '&page=' + page);
        },
        getAppointmentsPatient: function(patientId) {
          return httpRequestBuilderService.buildHttpRequest('GET', 'appointments/patient/', null);
        },
        getDoctorFutureAppointments: function () {
          return httpRequestBuilderService.buildHttpRequest('GET', 'appointments/doctor?future=true', null);
        },
        getDoctorPastAppointments: function (doctorId) {
          return httpRequestBuilderService.buildHttpRequest('GET', 'appointments/doctor?future=false', null);
        },
        getDoctorAvailableAppointments: function (doctorId, weekNumber, year) {
          return httpRequestBuilderService
            .buildHttpRequest('GET', 'doctors/' + doctorId + '/appointments?weekNumber=' + weekNumber + '&year=' + year, null);
        },
        deleteAppointment: function(appointmentId) {
          return httpRequestBuilderService.buildHttpRequest('DELETE', 'appointments/' + appointmentId, null);
        },
        postAppointment: function(appointment) {
          return httpRequestBuilderService.buildHttpRequest('POST', 'appointments', appointment);
        }
      };
    }]);
});
