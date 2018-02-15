'use strict';
define(['ChoPidoTurnos', 'services/doctorsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('DoctorsCtrl', ['doctorsService', function(doctorsService) {
    var _this = this;

    doctorsService.getDoctors().then(
      function(result) {
        console.log(result);
        _this.doctors = result.data;
      }
    );
  }]);
});
