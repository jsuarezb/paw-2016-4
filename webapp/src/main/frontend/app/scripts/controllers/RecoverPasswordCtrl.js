'use strict';

define(['ChoPidoTurnos', 'services/usersService'], function(ChoPidoTurnos) {
  ChoPidoTurnos.controller('RecoverPasswordCtrl', ['$stateParams', 'usersService', '$state', function($stateParams, usersService, $state) {
    var _this = this;
    this.$onInit = function() {
      this.alerts = [];
      this.token = $stateParams.token;
    };

    this.sendPasswordReset = function() {
      usersService.resetPassword({
        token: _this.token,
        password: _this.password,
        passwordConfirmation: _this.passwordConfirmation
      }).then(function(response) {
        if (response.status >= 400 || response.status < 0) {
          _this.alerts.push({message: 'Ha ocurrido un error restableciendo su contraseña', type: 'danger'});
          return;
        }
        _this.alerts.push({message: 'Su contraseña ha sido restablecida', type: 'success'});
        $state.go('login');
      });
    };
  }]);
});
