'use strict';

define(['ChoPidoTurnos'], function(ChoPidoTurnos) {
  ChoPidoTurnos.factory('usersService', ['$http', function($http) {
    return {
      requestPasswordReset: function(params) {
        return $http.post('/grupo4/api/v1/users/password/request_recovery', params);
      },
      resetPassword: function(params) {
        return $http.post('/grupo4/api/v1/users/password/recover', params);
      }
    };
  }]);
});
