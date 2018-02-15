'use strict';
define(['ChoPidoTurnos'], function (ChoPidoTurnos) {
  ChoPidoTurnos.component('header', {
    bindings: {
      user: '<',
      userType: '<'
    },
    templateUrl: 'views/header.html'
  });
});
