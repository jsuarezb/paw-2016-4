'use strict';
define(['ChoPidoTurnos','services/institutionsService'], function(ChoPidoTurnos,institutionsService) {
  function AvailableSlotsCtrl ($scope,institutionsService) {
    var _this = this;
    $scope.daysOfTheWeek = ['Lunes','Martes','Miércoles','Jueves','Viernes','Sábado','Domingo'];
    $scope.hoursAvailable = ['10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00'];
  };

  AvailableSlotsCtrl.$inject = ['$scope', 'institutionsService'];

  ChoPidoTurnos.component('availableSlotsComponent', {
    templateUrl: 'views/Modal/availableSlotsInnerView.html',
    controller: AvailableSlotsCtrl,
    bindings: {
      modalInstance: '<',
      resolve: '<'
    }
  });


});
