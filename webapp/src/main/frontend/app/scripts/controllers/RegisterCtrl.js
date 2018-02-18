'use strict';

define(['ChoPidoTurnos', 'services/sessionService'], function (ChoPidoTurnos) {
  ChoPidoTurnos
    .controller('RegisterCtrl',
    ['$state', '$scope', 'sessionService',
    function ($state, $scope, sessionService) {
      $scope.$on('onRegisterStart', function (event, args) {
        $scope.isRegistering = true;
      });

      $scope.$on('onRegisterEnd', function (event, args) {
        $scope.isRegistering = false;
      });
      var successRegister = function(response) {
        $scope.$broadcast('onRegisterEnd');
        $state.go('home');
      };

      var errorRegister = function(response) {
        $scope.$broadcast('onRegisterEnd');
      };

      return {
        register: function() {
          var data = {
            email: this.email,
            firstName: this.firstName,
            lastName: this.lastName,
            password: this.password,
            passwordConfirmation: this.passwordConfirmation,
            phone: this.phone
          };

          $scope.$broadcast('onRegisterStart');
          sessionService.register(data, successRegister, errorRegister);
        }
      };
    }]);

  ChoPidoTurnos
    .directive('registerSubmitBtn', function() {
      return {
        restrict: 'E',
        scope: {},
        template:
          '<div class="form-group">' +
            '<button ng-disabled="isRegistering" type="submit" class="btn btn-success">' +
              'Registrarse' +
            '</button>' +
          '</div>',
        replace: true
      };
    });
});
