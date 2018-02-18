'use strict';
define(['ChoPidoTurnos', 'moment', 'services/doctorsService', 'services/appointmentsService'],
    function(ChoPidoTurnos, moment) {

  ChoPidoTurnos.controller('DoctorsCtrl',
    ['doctorsService', '$stateParams', '$scope', function(doctorsService, $stateParams, $scope) {
    var _this = this;

    this.doctorsFetched = false;
    this.weekNumber = moment().weeks();
    this.year = moment().year();

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
