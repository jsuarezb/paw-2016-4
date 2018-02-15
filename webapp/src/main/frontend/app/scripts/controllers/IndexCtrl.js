'use strict';
define(['ChoPidoTurnos', 'services/sessionService', 'components/header'], function(ChoPidoTurnos) {
  ChoPidoTurnos.controller('IndexCtrl', ['$state', 'sessionService', function ($state, sessionService) {
    return {
      getLoggedUser: function() {
        return sessionService.getLoggedUser();
      },

      getUserType: function () {
        var loggedUser = sessionService.getLoggedUser();
        return loggedUser && loggedUser.type;
      },

      logout: function() {
        sessionService.logout();
        $state.go('home');
      }
    };
  }]);
});
