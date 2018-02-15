'use strict';
define(['ChoPidoTurnos', 'services/sessionService'], function(ChoPidoTurnos) {
  ChoPidoTurnos.controller('IndexCtrl', ['$state', 'sessionService', function ($state, sessionService) {
    return {
      getLoggedUser: function() {
        return sessionService.getLoggedUser();
      },
      getUserType: function () {
        return sessionService.getUserType();
      },
      logout: function() {
        sessionService.logout();
        $state.go('home');
      }
    };
  }]);
});
