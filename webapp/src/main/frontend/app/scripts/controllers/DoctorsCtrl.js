'use strict';
define(['ChoPidoTurnos', 'services/DoctorsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('DoctorsCtrl', ['DoctorsService', function(doctorsService) {
    var _this = this;

    doctorsService.getDoctors().then(
      function(result) {
        console.log(result);
        _this.doctors = result.data;
      }
    );
  }]);
});
