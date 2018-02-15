'use strict';
define(['ChoPidoTurnos', 'services/appointmentsService', 'services/sessionService'], function(ChoPidoTurnos) {
  ChoPidoTurnos
    .controller('ConfirmedCtrl',
      ['$stateParams', function($stateParams) {
      console.log($stateParams)
      }]);
});
