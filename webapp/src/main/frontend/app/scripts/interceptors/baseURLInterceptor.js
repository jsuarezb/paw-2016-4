'use strict';
var BASE_URL = 'http://pawserver.it.itba.edu.ar';

define(['ChoPidoTurnos'], function (ChoPidoTurnos) {
  ChoPidoTurnos.factory('baseURLInterceptor', function () {
    return {
      request: function (config) {
        if (config.url.indexOf('/api/v1') !== -1 && config.url.indexOf('http') === -1) {
          config.url = BASE_URL + config.url;
        }
        return config;
      }
    };
  });

  ChoPidoTurnos.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('baseURLInterceptor');
  }]);
});
