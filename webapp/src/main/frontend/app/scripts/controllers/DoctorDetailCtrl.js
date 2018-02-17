'use strict';
define(['ChoPidoTurnos', 'services/doctorsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('DoctorDetailCtrl', ['doctorsService', '$stateParams', function(doctorsService, $stateParams) {
    var doctorId = $stateParams.doctorId;

    var _this = this;
    this.userRating = 0;

    doctorsService
      .getDoctor(doctorId)
      .then(function (result) {
        console.log(result);
        _this.doctor = result.data;
      }
    );

    doctorsService
      .getUserRating(doctorId)
      .then(function (result) {
        _this.userRating = result.data.value;
      });

    doctorsService
      .getRatingSummary(doctorId)
      .then(function (result) {
        var data = result.data;
        console.log(data);
        _this.ratingAverage = Math.round(data.average);
        _this.ratingSummary = data.valuesCount;
      });

    this.onDoctorRated = function (ev) {
      doctorsService
        .rate(doctorId, ev.rating)
        .then(function (result) {

        })
    }
  }]);
});
