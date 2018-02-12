'use strict';
define(['ChoPidoTurnos','services/httpRequestBuilderService'], function(ChoPidoTurnos) {
  ChoPidoTurnos.service('NeighborhoodsService', [
    'httpRequestBuilderService',
    function (httpRequestBuilderService) {
      return {
        getNeighborhoods: function() {
          return httpRequestBuilderService.buildHttpRequest('GET', 'neighborhoods', null);
        }
      };
    }]);
});
