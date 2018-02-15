'use strict';
define(['ChoPidoTurnos', 'services/doctorsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('DoctorsCtrl',
    ['doctorsService', '$stateParams', '$scope', function(doctorsService, $stateParams, $scope) {
    var _this = this;

    this.doctorsFetched = false;

    $scope.firstName = $stateParams.firstName;
    $scope.lastName = $stateParams.lastName;

    doctorsService
      .getDoctors($stateParams.firstName || '', $stateParams.lastName || '')
      .then(function(result) {
        _this.doctorsFetched = true;
        _this.doctors = result.data;
      });
  }]);
});
