'use strict';

define(['ChoPidoTurnos'], function(ChoPidoTurnos) {
  ChoPidoTurnos.component('appointment', {
    bindings: {
      appointment: '<',
      subject: '@',
      onCancel: '&'
    },
    templateUrl: 'views/appointment.html'
  });
});
