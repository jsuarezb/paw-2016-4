'use strict';
define(['ChoPidoTurnos'], function (ChoPidoTurnos) {
  function HeaderController($state) {
    return {
      goToAppointments: function() {
        $state.go(this.user.type + 'Appointments', {patientId: this.user.id});
      },
      handleLogout: function() {
        this.user = null;
        this.onLogout();
      }
    };
  }

  HeaderController.$inject = ['$state'];

  ChoPidoTurnos.component('header', {
    controller: HeaderController,
    bindings: {
      user: '<',
      onLogout: '&'
    },
    templateUrl: 'views/header.html'
  });
});
