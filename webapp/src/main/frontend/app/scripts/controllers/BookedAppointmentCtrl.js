'use strict';
define(['ChoPidoTurnos', 'services/appointmentsService', 'services/sessionService'], function(ChoPidoTurnos) {
  ChoPidoTurnos
    .controller('BookedAppointmentCtrl',
      ['$stateParams', function($stateParams) {
        console.log($stateParams);
      }]);
});
