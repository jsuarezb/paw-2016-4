'use strict';
define(
  ['ChoPidoTurnos',
   'services/appointmentsService',
   'services/sessionService'],
   function(ChoPidoTurnos) {
  ChoPidoTurnos
    .controller('AppointmentSearchCtrl',
    ['$scope', '$state','$stateParams', 'appointmentsService', 'sessionService',
      function($scope, $state, $stateParams, appointmentService, sessionService) {
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
      console.log(sessionService.getLoggedUser());

      this.dayOfWeek = function(date) {
        switch (date.getDay()) {
          case 1: return 'Lunes';
          case 2: return 'Martes';
          case 3: return 'Miércoles';
          case 4: return 'Jueves';
          case 5: return 'Viernes';
          case 6: return 'Sabado';
          case 7: return 'Domingo';
          default: return '-';
        }
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
        console.log(appointment);
        appointmentService.postAppointment({
          slotId: appointment.appointmentSlot.id,
          weekNumber: this.weekOfYear(new Date(appointment.date)),
          year: new Date(appointment.date).getFullYear(),
          commets: appointment.comments
        }
        ).then(function(response) {
          console.log(response.status);
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
