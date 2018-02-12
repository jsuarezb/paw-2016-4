'use strict';
define(['ChoPidoTurnos','services/httpRequestBuilderService'], function(ChoPidoTurnos) {
  ChoPidoTurnos.service('PatientsService', [
    'httpRequestBuilderService',
    function (httpRequestBuilderService) {
      return {
        getPatients: function() {
          return httpRequestBuilderService.buildHttpRequest('GET', 'patients', null);
        },
        getPatient: function(patientId) {
          return httpRequestBuilderService.buildHttpRequest('GET', 'patients/' + patientId, null);
        }
      };
    }]);
});
