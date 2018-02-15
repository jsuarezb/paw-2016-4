'use strict';
angular.module('ChoPidoTurnos').factory('httpRequestBuilderService',
  ['$http', function($http) {
    return {
      buildHttpRequest: function (requestMethod, requestUrl, requestData) {
        return $http({
          method: requestMethod,
          // change this to general
          url: '/grupo4/api/v1/' + requestUrl,
          headers: {
            'content-type': 'application/json'
          },
          data: requestData
        });
      },
      buildAuthenticatedHttpRequest: function (requestMethod, requestUrl, requestData, token) {
        return $http({
          method: requestMethod,
          // change this to general
          url: '/grupo4/api/v1/' + requestUrl,
          headers: {
            'content-type': 'application/json',
            'authentication': token
          },
          data: null
        });
      }
    };
  }]);
