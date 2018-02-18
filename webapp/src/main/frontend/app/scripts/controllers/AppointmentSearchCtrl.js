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

      this.dayOfWeek = function(date) {
        return dateService.dayOfWeek(date);
      };

      this.weekOfYear = function (d) {
       /* For a given date, get the ISO week number
        *
        * Based on information at:
        *
        *    http://www.merlyn.demon.co.uk/weekcalc.htm#WNR
        *
        * Algorithm is to find nearest thursday, it's year
        * is the year of the week number. Then get weeks
        * between that date and the first day of that year.
        *
        * Note that dates in one year can be weeks of previous
        * or next year, overlap is up to 3 days.
        *
        * e.g. 2014/12/29 is Monday in week  1 of 2015
        *      2012/1/1   is Sunday in week 52 of 2011
        */
        // Copy date so don't modify original
        d = new Date(Date.UTC(d.getFullYear(), d.getMonth(), d.getDate()));
        // Set to nearest Thursday: current date + 4 - current day number
        // Make Sunday's day number 7
        d.setUTCDate(d.getUTCDate() + 4 - (d.getUTCDay() || 7));
        // Get first day of year
        var yearStart = new Date(Date.UTC(d.getUTCFullYear(), 0, 1));
        // Calculate full weeks to nearest Thursday
        var weekNo = Math.ceil((((d - yearStart) / 86400000) + 1) / 7);
        // Return array of year and week number
        return weekNo;
      };

      this.month = function (date) {
        return date.getMonth();
      };

      this.searchAppointments = function () {
        var weekOfYear = this.weekOfYear(this.searchDateWeek);
        var year = this.searchDateWeek.getFullYear();

        appointmentService
          .searchAppointments(_institution, _speciality, _neighborhood, weekOfYear, year, _doctor, this.pageNumber)
          .then(function (data) {
            var page = data.data;
            var currentWeek = _this.weekOfYear(new Date());
            var currentYear = new Date().getFullYear();

            _this.page = page;

            _this.appointmentsSearched = true;
            _this.appointments = page.results;
            _this.emptyAppointments = page.results.length == 0;
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

      doctorService
        .findAvailable(_speciality, _neighborhood, _institution, 8, 2018)
        .then(function (response) {
          _this.doctorsPage = response.data;
          _this.doctors = _this.doctorsPage.results;
          _this.doctorsFetched = true;
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
