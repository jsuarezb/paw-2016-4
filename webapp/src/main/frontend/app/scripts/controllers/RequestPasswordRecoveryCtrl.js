'use strict';

define(['ChoPidoTurnos', 'services/usersService'], function(ChoPidoTurnos) {
  ChoPidoTurnos.controller('RequestPasswordRecoveryCtrl', ['usersService', function (usersService) {
    var _this = this;

    this.$onInit = function() {
      this.alerts = [];
    };

    this.requestRecovery = function() {
      usersService.requestPasswordReset({email: this.email}).then(function (response) {
        if (response.status >= 400 || response.status < 0) {
          _this.alerts.push({message: 'Ha ocurrido un error', type: 'danger'});
          return;
        }
        _this.alerts.push({message: 'Se ha enviado un mail para restablecer su contraseña', type: 'success'});
      });
    };
  }]);
});
