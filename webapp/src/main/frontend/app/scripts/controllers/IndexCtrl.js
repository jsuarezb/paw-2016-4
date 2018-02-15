'use strict';
define(['ChoPidoTurnos', 'services/sessionService'], function(ChoPidoTurnos) {
  ChoPidoTurnos.controller('IndexCtrl', ['sessionService', function(sessionService) {
    return {
      getLoggedUser: function() {
        return sessionService.getLoggedUser();
      },
      getUserType: function () {
        return sessionService.getUserType();
      },
      logout: function() {
        sessionService.logout();
      }
    };
  }]);
});
