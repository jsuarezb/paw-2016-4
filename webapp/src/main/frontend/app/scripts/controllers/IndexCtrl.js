'use strict';
define(['ChoPidoTurnos', 'services/sessionService',
        'components/header',
        'components/alerts',
        'components/appointment'], function(ChoPidoTurnos) {
  ChoPidoTurnos.controller('IndexCtrl', ['$state', '$scope', 'sessionService', function ($state, $scope, sessionService) {
    this.getLoggedUser = function() {
      return sessionService.getLoggedUser();
    };

    this.getUserType = function () {
      var loggedUser = this.getLoggedUser();
      return loggedUser && loggedUser.type;
    };

    this.logout = function() {
      sessionService.logout();
      $state.go('home');
    };

    this.getLoggedUser();
  }]);
});
