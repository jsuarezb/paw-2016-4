'use strict';

define(['ChoPidoTurnos', 'services/loginService'], function (ChoPidoTurnos) {
  ChoPidoTurnos
    .controller('LoginCtrl', ['$scope', 'loginService', function ($scope, loginService) {
      const successLogin = (response) => {
        $scope.$broadcast('onLogInEnd');
        document.location.href = '/#';
      }

      const errorLogin = (response) => {
        $scope.$broadcast('onLogInEnd');
      }

      return {
        login: () => {
          const data = {
            email: $scope.email,
            password: $scope.password,
            type: 'patient' // TODO: update this
          };

          $scope.$broadcast('onLogInStart');
          loginService.login(data, successLogin, errorLogin);
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
