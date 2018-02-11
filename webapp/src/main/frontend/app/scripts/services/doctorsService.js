'use strict';
define(['ChoPidoTurnos','services/httpRequestBuilderService'], function(ChoPidoTurnos) {
  ChoPidoTurnos.service('DoctorsService', [
    'httpRequestBuilderService',
    function (httpRequestBuilderService) {
      return {
        getDoctors: function() {
         return httpRequestBuilderService.buildHttpRequest('GET', 'doctors', null);
        },
        getDoctor: function(doctorId) {
          return httpRequestBuilderService.buildHttpRequest('GET', 'doctors/' + doctorId, null);
        }
    };
  }]);
});
