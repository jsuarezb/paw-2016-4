'use strict';

define(['ChoPidoTurnos', 'services/sessionService'], function (ChoPidoTurnos) {
  ChoPidoTurnos
    .controller('RegisterCtrl', ['$scope', 'sessionService', function ($scope, sessionService) {
      const successRegister = function(response) {
        $scope.$broadcast('onRegisterEnd');
        document.location.href = '/#';
      }

      const errorRegister = function(response) {
        $scope.$broadcast('onRegisterEnd');
      }

      return {
        register: function() {
          const data = {
            email: $scope.email,
            firstName: $scope.firstName,
            lastName: $scope.lastName,
            password: $scope.password,
            passwordConfirmation: $scope.passwordConfirmation
          };

          $scope.$broadcast('onRegisterStart');
          sessionService.register(data, successRegister, errorRegister);
        }
      }
    }])

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
        replace: true,
        link: function(scope) {
          scope.$on('onRegisterStart', function (event, args) {
            scope.isRegistering = true;
          })

          scope.$on('onRegisterEnd', function (event, args) {
            scope.isRegistering = false;
          })
        }
      }
    })
});
