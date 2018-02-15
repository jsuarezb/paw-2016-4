'use strict';
define(['ChoPidoTurnos', 'services/doctorsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('DoctorsCtrl',
    ['doctorsService', '$stateParams', '$scope', function(doctorsService, $stateParams, $scope) {
    var _this = this;

    $scope.firstName = $stateParams.firstName;
    $scope.lastName = $stateParams.lastName;

    doctorsService
      .getDoctors($stateParams.firstName, $stateParams.lastName)
      .then(function(result) {
        _this.doctors = result.data;
      });
  }]);
});
