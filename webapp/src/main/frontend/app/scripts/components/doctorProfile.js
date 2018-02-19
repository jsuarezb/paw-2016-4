'use strict';

define(['ChoPidoTurnos'], function(ChoPidoTurnos) {
  function DoctorProfileCtrl(doctorsService, sessionService) {
    return {
      '$onInit': function () {
        var _this = this;

        if (sessionService.getLoggedUser()) {
          doctorsService
            .getUserRating(this.doctor.id)
            .then(function (result) {
              _this.userRating = result.data.value;
            });
        }

        doctorsService
          .getRatingSummary(this.doctor.id)
          .then(function (result) {
            var data = result.data;

            _this.ratingAverage = Math.round(data.average);
            _this.ratingSummary = data.valuesCount;
          });
      },
      onDoctorRated: function (ev) {
        doctorsService
          .rate(this.doctor.id, ev.rating)
          .then(function (result) {
            // TODO
          });
      }
    };
  }

  DoctorProfileCtrl.$inject = ['doctorsService', 'sessionService'];

  ChoPidoTurnos
    .component('doctorProfile', {
      bindings: {
        doctor: '<'
      },
      controller: DoctorProfileCtrl,
      templateUrl: 'views/doctorProfile.html'
    });
});
