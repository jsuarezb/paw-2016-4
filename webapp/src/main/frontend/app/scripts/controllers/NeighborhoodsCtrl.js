'use strict';
define(['ChoPidoTurnos', 'services/neighborhoodsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('NeighborhoodsCtrl', ['NeighborhoodsService', function(neighborhoodsService) {
    var _this = this;

    neighborhoodsService.getNeighborhoods().then(
      function(result) {
        _this.neighborhoods = result.data;
      }
    );
  }]);
});
