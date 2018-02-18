'use strict';

define(['ChoPidoTurnos'], function(ChoPidoTurnos) {
  function DoctorSearchItemController(appointmentsService, doctorService) {
    return {
      '$onInit': function() {
        var _this = this;

        doctorService
          .getRatingSummary(this.doctor.id)
          .then(function (result) {
            _this.doctorAverageRating = result.data.average;
          });
      }
    };
  }

  DoctorSearchItemController.$inject = ['appointmentsService', 'doctorsService'];

  ChoPidoTurnos
    .component('doctorSearchItem', {
      bindings: {
        doctor: '<',
        weekNumber: '<',
        year: '<'
      },
      controller: DoctorSearchItemController,
      templateUrl: 'views/doctorSearchItem.html'
    });
});
