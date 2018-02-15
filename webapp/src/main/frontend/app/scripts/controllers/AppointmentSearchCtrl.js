'use strict';
define(['ChoPidoTurnos', 'services/appointmentsService'], function(ChoPidoTurnos) {
  ChoPidoTurnos
    .controller('AppointmentSearchCtrl',
    ['$scope', '$stateParams', 'AppointmentsService', function($scope, $stateParams, appointmentService) {
      var _this = this;

      var institution = $stateParams.institution || '';
      var speciality = $stateParams.speciality || '';
      var neighborhood = $stateParams.neighborhood || '';

      appointmentService.searchAppointments(institution, speciality, neighborhood).then(function (data) {
        var page = data.data;

        $scope.appointmentsSearched = true;
        $scope.appointments = page.results;
        $scope.emptyAppointments = page.results.length === 0;
        $scope.hasPreviousPage = page.page > 0;
        $scope.hasNextPage = Math.floor(page.total / page.pageSize) > page.page;
        console.log(data.data);
      });

      $scope.dayOfWeek = function(slot) {
        switch (slot.dayOfWeek) {
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

      $scope.bookAppointment = function(appointment) {
        console.log(appointment);
      };
  }]);
});
