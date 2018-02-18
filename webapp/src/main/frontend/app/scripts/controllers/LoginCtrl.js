'use strict';

define(['ChoPidoTurnos', 'services/sessionService'], function (ChoPidoTurnos) {
  ChoPidoTurnos
    .controller('LoginCtrl',
      [
        '$scope',
        '$state',
        '$stateParams',
        'sessionService',
        function ($scope, $state, $stateParams, sessionService) {
      $scope.type = 'patient';

      var successLogin = function(response) {
        $scope.$broadcast('onLogInEnd');
        var params = $stateParams.redirectParams && JSON.parse($stateParams.redirectParams);
        $state.go($stateParams.redirectTo || 'home', params || {});
      };

      var errorLogin = function(response) {
        $scope.$broadcast('onLogInEnd');

        switch (response.status) {
          case 401:
            $scope.loginError = 'Usuario o contraseña incorrectos';
            break;
          case 500:
            $scope.loginError = 'Error del servidor. Ya fuimos notificados del error y en breve lo solucionaremos.';
            break;
          default:
            $scope.loginError = 'Error de la aplicación. Ya fuimos notificados del error y en breve lo solucionaremos.';
            break;
        }
      };

      return {
        login: function() {
          var data = {
            email: $scope.email,
            password: $scope.password,
            type: $scope.type
          };

          $scope.loginError = null;
          $scope.$broadcast('onLogInStart');
          sessionService.login(data, successLogin, errorLogin);
        }
      };}]
    );

  ChoPidoTurnos
    .directive('loginForm', function() {
      return {
        restrict: 'E',
        templateUrl: 'views/login-form.html',
        replace: true
      };
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
            });
        }
      };
    });
});
