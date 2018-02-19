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
      var now = moment();
      this.currentWeek = now.week();
      this.year = now.year();

      var groupBy = function(xs, f) {
        return xs.reduce(function(rv, x) {
          (rv[f(x)] = rv[f(x)] || []).push(x);
          return rv;
        }, {});
      };

      this.searchDate = new Date();
      this.pageNumber = 0;
      this.isLoggedIn = sessionService.getLoggedUser() !== null;
      this.doctorsFetched = false;
      this.isDatepickerOpen = false;

      this.openDatePicker = function() {
        this.isDatepickerOpen = true;
      };

      this.loadWeek = function(week, year) {
        _this.currentWeek = week;
        _this.year = year;

        doctorService
          .findAvailable(_speciality, _neighborhood, _institution, week, year, 0)
          .then(function (response) {
            _this.doctorsPage = response.data;
            _this.doctors = _this.doctorsPage.results;
            _this.doctorsFetched = true;
          });
      };

      this.loadWeek(this.currentWeek, this.currentYear);

      $scope.$watch('$ctrl.searchDate', function (newValue, oldValue) {
        var d = moment(newValue);
        _this.startOfWeek = d.startOf('week').format('DD/MM/YYYY');
        _this.endOfWeek = d.endOf('week').format('DD/MM/YYYY');

        _this.loadWeek(d.week(), d.year());
      });

      this.dayOfWeek = function(date) {
        return dateService.dayOfWeek(date);
      };

      this.bookAppointment = function(appointment) {
        appointmentService.postAppointment({
          slotId: appointment.appointmentSlot.id,
          weekNumber: moment(appointment.date).week(),
          year: moment(appointment.date).year(),
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
