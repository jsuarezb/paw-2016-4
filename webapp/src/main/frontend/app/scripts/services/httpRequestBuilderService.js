'use strict';
angular.module('ChoPidoTurnos').factory('httpRequestBuilderService',
  ['$http', function($http) {
    return {
      buildHttpRequest: function (requestMethod, requestUrl, requestData) {
        return $http({
          method: requestMethod,
          // change this to general
          url: 'http://localhost:8080/grupo4/api/v1/' + requestUrl,
          headers: {
            'content-type': 'application/json'
          },
          data: null
        });
      },
      buildAuthenticatedHttpRequest: function (requestMethod, requestUrl, requestData, token) {
        return $http({
          method: requestMethod,
          // change this to general
          url: 'http://localhost:8080/grupo4/api/v1/' + requestUrl,
          headers: {
            'content-type': 'application/json',
            'authentication': token
          },
          data: null
        });
      }
    };
  }]);
