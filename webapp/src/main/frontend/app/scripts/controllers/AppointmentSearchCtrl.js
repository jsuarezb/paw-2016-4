'use strict';
define(['ChoPidoTurnos', 'services/appointmentsService', 'services/sessionService'], function(ChoPidoTurnos) {
  ChoPidoTurnos
    .controller('AppointmentSearchCtrl',
    ['$scope', '$stateParams', 'appointmentsService', 'sessionService',
      function($scope, $stateParams, appointmentService, sessionService) {
      var _this = this;

      var _institution = $stateParams.institution || '';
      var _speciality = $stateParams.speciality || '';
      var _neighborhood = $stateParams.neighborhood || '';

      var groupBy = function(xs, f) {
        return xs.reduce(function(rv, x) {
          (rv[f(x)] = rv[f(x)] || []).push(x);
          return rv;
        }, {});
      };

      $scope.searchDateWeek = new Date();
      $scope.pageNumber = 0;
      $scope.isLoggedIn = sessionService.getLoggedUser() != null;
      console.log(sessionService.getLoggedUser());

      $scope.dayOfWeek = function(date) {
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

      $scope.weekOfYear = function (d) {
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

      $scope.month = function (date) {
        return date.getMonth();
      };

      var searchAppointments = function () {
        var weekOfYear = $scope.weekOfYear($scope.searchDateWeek);
        var year = $scope.searchDateWeek.getFullYear();

        appointmentService
          .searchAppointments(_institution, _speciality, _neighborhood, weekOfYear, year, $scope.pageNumber)
          .then(function (data) {
            var page = data.data;
            var currentWeek = $scope.weekOfYear(new Date());
            var currentYear = new Date().getFullYear();

            $scope.page = page;

            $scope.appointmentsSearched = true;
            $scope.appointments = page.results;
            $scope.emptyAppointments = page.results.length === 0;
            $scope.hasPreviousPage = (weekOfYear > currentWeek && year >= currentYear) || page.page > 0;

            if (!$scope.emptyAppointments) {
              $scope.appointmentGroups = groupBy($scope.appointments, function (app) {
                var date = new Date(app.date);
                return new Date(date.getFullYear(), date.getMonth(), date.getDate());
              });

              $scope.appointmentDays = Object
                .keys($scope.appointmentGroups)
                .map(function (d) {
                  return new Date(d);
                })
                .sort();
            }
          });
      };

      searchAppointments();

      $scope.bookAppointment = function(appointment) {
        console.log(appointment);
      };

      $scope.prevPage = function () {
        if ($scope.pageNumber === 0) {
          $scope.searchDateWeek.setDate($scope.searchDateWeek.getDate() - 7);
          $scope.pageNumber = 0;
        } else {
          $scope.pageNumber--;
        }

        searchAppointments();
      };

      $scope.nextPage = function () {
        var endOfWeek = Math.floor($scope.page.total / $scope.page.pageSize) <= $scope.page.page;

        if (endOfWeek) {
          $scope.searchDateWeek.setDate($scope.searchDateWeek.getDate() + 7);
          $scope.pageNumber = 0;
        } else {
          $scope.pageNumber++;
        }

        searchAppointments();
      };
  }]);
});
