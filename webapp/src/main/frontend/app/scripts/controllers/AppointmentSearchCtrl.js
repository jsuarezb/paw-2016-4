'use strict';

define(
  ['ChoPidoTurnos',
   'services/appointmentsService',
   'services/sessionService',
   'services/dateService',
   'services/doctorsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos
    .controller('AppointmentSearchCtrl',
    ['$scope', '$state','$stateParams', 'appointmentsService', 'sessionService', 'dateService', 'doctorsService',
      function($scope, $state, $stateParams, appointmentService, sessionService, dateService, doctorService) {
      var _this = this;

      this.alerts = [];

      var _institution = $stateParams.institution || '';
      var _speciality = $stateParams.speciality || '';
      var _neighborhood = $stateParams.neighborhood || '';

      var groupBy = function(xs, f) {
        return xs.reduce(function(rv, x) {
          (rv[f(x)] = rv[f(x)] || []).push(x);
          return rv;
        }, {});
      };

      this.searchDateWeek = new Date();
      this.pageNumber = 0;
      this.isLoggedIn = sessionService.getLoggedUser() !== null;
      this.doctorsFetched = false;

      $scope.dayOfWeek = function(date) {
        return dateService.dayOfWeek(date);
      };

      doctorService
        .findAvailable(_speciality, _neighborhood, _institution, 8, 2018)
        .then(function (response) {
          _this.doctorsPage = response.data;
          _this.doctors = _this.doctorsPage.results;
          _this.doctorsFetched = true;
        });

        $scope.$watch("dt", function(newValue, oldValue) {

          this.searchDateWeek = new Date(newValue);
          searchAppointments();
        });

      this.bookAppointment = function(appointment) {
        appointmentService.postAppointment({
          slotId: appointment.appointmentSlot.id,
          weekNumber: dateService.weekOfYear(new Date(appointment.date)),
          year: new Date(appointment.date).getFullYear(),
          comments: appointment.comments
        }
        ).then(function(response) {
          if (response.status !== 200) {
            _this.alerts.push({message: response.data.errors, type: 'danger'});
            return;
          }
          _this.alerts.push({message: 'Su turno fue reservado con éxito', type: 'success'});

          _this.appointments.splice(_this.appointments.indexOf(appointment), 1);
          _this.groupAppointmentsByDate();
        });
      };

      this.hasPrevPage = function () {
        return this.currentPage && this.currentPage.pageNumber === 0;
      };

      this.hasNextPage = function () {
        return this.currentPage && this.currentPage.pageNumber < Math.floor(this.currentPage.total / this.currentPage.pageSize);
      };
  }]);
});
