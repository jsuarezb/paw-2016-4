'use strict';
define(['ChoPidoTurnos','services/httpRequestBuilderService'], function(ChoPidoTurnos) {
  ChoPidoTurnos.service('institutionsService', [
    'httpRequestBuilderService',
    function (httpRequestBuilderService) {
      return {
        getInstitutions: function() {
          return httpRequestBuilderService.buildHttpRequest('GET', 'institutions', null);
        },
        getInstitution: function(institutionId) {
          return httpRequestBuilderService.buildHttpRequest('GET', 'institutions/' + institutionId, null);
        },
        getInstitutionSpecialities: function(institutionId) {
          return httpRequestBuilderService.buildHttpRequest('GET', 'institutions/' + institutionId + '/specialities', null);
        },
        getInstitutionDoctors: function (institutionId) {
        return httpRequestBuilderService.buildHttpRequest('GET', 'institutions/' + institutionId + '/doctors', null);
        }
      };
    }]);
});
