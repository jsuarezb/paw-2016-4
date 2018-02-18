'use strict';

define(['ChoPidoTurnos',
  'moment',
  'services/doctorsService',
  'services/appointmentsService',
  'services/dateService'], function(ChoPidoTurnos, moment) {

  ChoPidoTurnos
    .controller('DoctorDetailCtrl', ['$stateParams', 'doctorsService', 'appointmentsService', 'dateService',
    function($stateParams, doctorsService, appointmentsService, dateService) {
      var _this = this;

      this.userRating = 0;
      this.doctorId = $stateParams.doctorId;

      doctorsService
        .getDoctor(this.doctorId)
        .then(function (result) {
          _this.doctor = result.data;
        });

      doctorsService
        .getUserRating(this.doctorId)
        .then(function (result) {
          _this.userRating = result.data.value;
        });

      doctorsService
        .getRatingSummary(this.doctorId)
        .then(function (result) {
          var data = result.data;

          _this.ratingAverage = Math.round(data.average);
          _this.ratingSummary = data.valuesCount;
        });

      this.onDoctorRated = function (ev) {
        doctorsService
          .rate(this.doctorId, ev.rating)
          .then(function (result) {
            // TODO
          });
      };
    }
  ]);
});
