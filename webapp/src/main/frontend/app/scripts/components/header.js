'use strict';
define(['ChoPidoTurnos'], function (ChoPidoTurnos) {
  ChoPidoTurnos.component('header', {
    bindings: {
      user: '=',
      userType: '<',
      logout: '='
    },
    templateUrl: 'views/header.html'
  });
});
