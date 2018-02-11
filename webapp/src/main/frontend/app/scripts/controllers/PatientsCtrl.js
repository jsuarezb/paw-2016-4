'use strict';
define(['ChoPidoTurnos', 'services/PatientsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('PatientsCtrl', ['PatientsService', function(patientsService) {
    var _this = this;

    patientsService.getPatients().then(
      function(result) {
        console.log(result);
        _this.patients = result.data;
      }
    );
  }]);
});
