'use strict';
define(['ChoPidoTurnos'], function(ChoPidoTurnos) {
  ChoPidoTurnos.factory('sessionInterceptor', function() {
    return {
      request: function(config) {
        var token = localStorage.getItem('token');
        if (token) {
          config.headers.Authorization = token;
        }
        return config;
      },
      responseError: function(response) {
        if (response.status === 403) {
          localStorage.removeItem('token');
        }
        return response;
      }
    };
  });

  ChoPidoTurnos.config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('sessionInterceptor');
  }]);
});
