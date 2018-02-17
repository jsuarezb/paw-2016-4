'use strict';

define(
  ['ChoPidoTurnos',
   'services/appointmentsService',
   'services/sessionService',
   'services/dateService'], function(ChoPidoTurnos) {

  ChoPidoTurnos
    .controller('AppointmentSearchCtrl',
    ['$scope', '$state','$stateParams', 'appointmentsService', 'sessionService', 'dateService',
      function($scope, $state, $stateParams, appointmentService, sessionService, dateService) {
      var _this = this;

      this.alerts = [];

      var _institution = $stateParams.institution || '';
      var _speciality = $stateParams.speciality || '';
      var _neighborhood = $stateParams.neighborhood || '';
      var _doctor = $stateParams.doctor || '';

      var groupBy = function(xs, f) {
        return xs.reduce(function(rv, x) {
          (rv[f(x)] = rv[f(x)] || []).push(x);
          return rv;
        }, {});
      };

      this.searchDateWeek = new Date();
      this.pageNumber = 0;
      this.isLoggedIn = sessionService.getLoggedUser() !== null;

      $scope.dayOfWeek = function(date) {
        return dateService.dayOfWeek(date);
      };

      this.searchAppointments = function () {
        var weekOfYear = dateService.weekOfYear(_this.searchDateWeek);
        var year = _this.searchDateWeek.getFullYear();

        appointmentService
          .searchAppointments(_institution, _speciality, _neighborhood, weekOfYear, year, _doctor, this.pageNumber)
          .then(function (data) {
            var page = data.data;
            var currentWeek = dateService.weekOfYear(new Date());
            var currentYear = new Date().getFullYear();

            _this.page = page;

            _this.appointmentsSearched = true;
            _this.appointments = page.results;
            _this.emptyAppointments = !page.results;
            _this.hasPreviousPage = (weekOfYear > currentWeek && year >= currentYear) || page.page > 0;

            _this.groupAppointmentsByDate();
          });
      };

      this.groupAppointmentsByDate = function() {
        if (!_this.emptyAppointments) {
          _this.appointmentGroups = groupBy(_this.appointments || [], function (app) {
            var date = new Date(app.date);
            return new Date(date.getFullYear(), date.getMonth(), date.getDate());
          });

          _this.appointmentDays = Object
            .keys(_this.appointmentGroups)
            .map(function (d) {
              return new Date(d);
            })
            .sort();
        }
      };

      this.searchAppointments();

      this.bookAppointment = function(appointment) {
        appointmentService.postAppointment({
          slotId: appointment.appointmentSlot.id,
          weekNumber: dateService.weekOfYear(new Date(appointment.date)),
          year: new Date(appointment.date).getFullYear(),
          commets: appointment.comments
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

      this.prevPage = function () {
        if (this.pageNumber === 0) {
          this.searchDateWeek.setDate(this.searchDateWeek.getDate() - 7);
          this.pageNumber = 0;
        } else {
          this.pageNumber--;
        }

        this.searchAppointments();
      };

      this.nextPage = function () {
        var endOfWeek = Math.floor(this.page.total / this.page.pageSize) <= this.page.page;

        if (endOfWeek) {
          this.searchDateWeek.setDate(this.searchDateWeek.getDate() + 7);
          this.pageNumber = 0;
        } else {
          this.pageNumber++;
        }

        this.searchAppointments();
      };

  }]);
});
