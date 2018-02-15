'use strict';
define(['ChoPidoTurnos', 'services/appointmentsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos
    .controller(
      'DoctorAppointmentsCtrl',
      ['$scope', 'AppointmentsService',
      function($scope, appointmentsService) {
        var _this = this;

        $scope.pastAppointmentsFetched = false;
        $scope.futureAppointmentsFetched = false;

        appointmentsService
          .getDoctorFutureAppointments()
          .then(function (response) {
            $scope.futureAppointmentsFetched = true;
            $scope.futureAppointments = response.data.map(function (app) {
              app.isPast = false;
              return app;
            });
          });

        appointmentsService
          .getDoctorPastAppointments()
          .then(function (response) {
            console.log(response.data);
            $scope.pastAppointmentsFetched = true;
            $scope.pastAppointments = response.data.map(function (app) {
              app.isPast = true;
              return app;
            });
          });

        return {
          cancelAppointment: function (appointment) {
            console.log(appointment);
          }
        };
      }]
    );

  ChoPidoTurnos
    .directive('doctorAppointment', ['AppointmentsService',
      function (appointmentsService) {
        return {
          restrict: 'E',
          replace: true,
          scope: {
            appointment: '=',
          },
          templateUrl: 'views/doctorAppointment.html',
          link: function (scope, elm, attrs) {
            scope.cancelAppointment = function () {
              if (scope.isDeleted) {
                return;
              }

              appointmentsService
                .deleteAppointment(scope.appointment.id)
                .then(function (response) {
                  if (response.status < 400) {
                    scope.isDeleted = true;
                  }
                })
            }
          }
        };
      }]
    );
});
