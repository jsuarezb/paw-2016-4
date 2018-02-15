'use strict';
define(['ChoPidoTurnos', 'services/sessionService'], function(ChoPidoTurnos) {
  ChoPidoTurnos.controller('IndexCtrl', ['sessionService', function(sessionService) {
    return {
      getLoggedUser: function() {
        return sessionService.getLoggedUser();
      },
      logout: function() {
        sessionService.logout();
      }
    };
  }]);
});
