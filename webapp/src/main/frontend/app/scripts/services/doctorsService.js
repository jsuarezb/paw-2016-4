'use strict';
define(['ChoPidoTurnos','services/httpRequestBuilderService'], function(ChoPidoTurnos) {
  ChoPidoTurnos.service('doctorsService', [
    'httpRequestBuilderService',
    function (httpRequestBuilderService) {
      return {
        getDoctors: function(firstName, lastName) {
         return httpRequestBuilderService
            .buildHttpRequest('GET', 'doctors?first_name=' + firstName + '&last_name=' + lastName, null);
        },
        getDoctor: function(doctorId) {
          return httpRequestBuilderService.buildHttpRequest('GET', 'doctors/' + doctorId, null);
        }
    };
  }]);
});
