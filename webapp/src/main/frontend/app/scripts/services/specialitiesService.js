'use strict';
define(['ChoPidoTurnos','services/httpRequestBuilderService'], function(ChoPidoTurnos) {
  ChoPidoTurnos.service('SpecialitiesService', [
    'httpRequestBuilderService',
    function (httpRequestBuilderService) {
      return {
        getSpecialities: function() {
          return httpRequestBuilderService.buildHttpRequest('GET', 'specialities', null);
        },
        getSpeciality: function(specialityId) {
          return httpRequestBuilderService.buildHttpRequest('GET', 'specialities/' + specialityId, null);
        }
      };
    }]);
});
