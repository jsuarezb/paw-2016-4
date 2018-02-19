'use strict';

define(['ChoPidoTurnos',
  'moment',
  'services/doctorsService',
  'services/appointmentsService',
  'services/dateService'], function(ChoPidoTurnos, moment) {

  ChoPidoTurnos
    .controller('DoctorDetailCtrl', ['$scope', '$stateParams', 'doctorsService', 'appointmentsService', 'dateService',
    function($scope, $stateParams, doctorsService, appointmentsService, dateService) {
      var _this = this;

      this.userRating = 0;
      this.doctorId = $stateParams.doctorId;
      this.searchDate = new Date();
      this.weekNumber = moment(this.searchDate).week();
      this.year = moment(this.searchDate).year();

      this.isDatepickerOpen = false;

      this.openDatePicker = function() {
        this.isDatepickerOpen = true;
      };

      $scope.$watch('$ctrl.searchDate', function (newValue, oldValue) {
        var d = moment(newValue);

        _this.weekNumber = d.week();
        _this.year = d.year();

        $scope.$broadcast('date.change', {
          weekNumber: d.week(),
          year: d.year()
        });
      });

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
