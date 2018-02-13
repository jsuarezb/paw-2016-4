'use strict';
define(['ChoPidoTurnos', 'services/DoctorsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('DoctorsCtrl', ['DoctorsService', '$routeParams', function(doctorsService, $routeParams) {
    var _this = this;
    this.params = $routeParams;

    doctorsService.getDoctors().then(
      function(result) {
        console.log(result);
        _this.doctors = result.data;
      }
    );
  }]);
});
