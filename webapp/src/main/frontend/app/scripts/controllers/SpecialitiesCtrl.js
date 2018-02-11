'use strict';
define(['ChoPidoTurnos', 'services/SpecialitiesService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('SpecialitiesCtrl', ['SpecialitiesService', function(specialitiesService) {
    var _this = this;

    specialitiesService.getSpecialities().then(
      function(result) {
        console.log(result);
        _this.specialities = result.data;
      }
    );
  }]);
});
