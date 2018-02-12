'use strict';

define(['ChoPidoTurnos', 'services/sessionService'], function (ChoPidoTurnos) {
  ChoPidoTurnos
    .controller('LoginCtrl', ['$scope', 'sessionService', function ($scope, sessionService) {
      const successLogin = function(response) {
        $scope.$broadcast('onLogInEnd');
        document.location.href = '/#';
      }

      const errorLogin = function(response) {
        $scope.$broadcast('onLogInEnd');
      }

      return {
        login: function() {
          const data = {
            email: $scope.email,
            password: $scope.password,
            type: 'patient' // TODO: update this
          };

          $scope.$broadcast('onLogInStart');
          sessionService.login(data, successLogin, errorLogin);
        }
      }
    }])

  ChoPidoTurnos
    .directive('loginForm', function() {
      return {
        restrict: 'E',
        templateUrl: 'views/login-form.html',
        replace: true
      }
    })
    .directive('loginSubmitBtn', function() {
      return {
        restrict: 'E',
        scope: {},
        template:
          '<div class="form-group">' +
            '<button ng-disabled="isLogging" type="submit" class="btn btn-success">Iniciar</button>' +
          '</div>',
        replace: true,
        link: function (scope) {
            scope.$on('onLogInStart', function (event, args) {
              scope.isLogging = true;
            });

            scope.$on('onLogInEnd', function (event, args) {
              scope.isLogging = false;
            })
        }
      }
    })
});
