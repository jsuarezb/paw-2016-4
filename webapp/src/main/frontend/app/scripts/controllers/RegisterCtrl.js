'use strict';

define(['ChoPidoTurnos', 'services/sessionService'], function (ChoPidoTurnos) {
  ChoPidoTurnos
    .controller('RegisterCtrl', ['$scope', 'sessionService', function ($scope, sessionService) {
      $scope.$on('onRegisterStart', function (event, args) {
        $scope.isRegistering = true;
      });

      $scope.$on('onRegisterEnd', function (event, args) {
        $scope.isRegistering = false;
      });
      var successRegister = function(response) {
        $scope.$broadcast('onRegisterEnd');
        document.location.href = '/#';
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
            passwordConfirmation: this.passwordConfirmation
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
