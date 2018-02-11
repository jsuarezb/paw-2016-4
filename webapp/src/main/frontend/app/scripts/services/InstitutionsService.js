'use strict';
define(['ChoPidoTurnos','services/httpRequestBuilderService'], function(ChoPidoTurnos) {
  ChoPidoTurnos.service('InstitutionsService', [
    'httpRequestBuilderService',
    function (httpRequestBuilderService) {
      return {
        getInstitutions: function() {
          return httpRequestBuilderService.buildHttpRequest('GET', 'institutions', null);
        },
        getInstitution: function(institutionId) {
          return httpRequestBuilderService.buildHttpRequest('GET', 'institutions/' + institutionId, null);
        }
      };
    }]);
});
