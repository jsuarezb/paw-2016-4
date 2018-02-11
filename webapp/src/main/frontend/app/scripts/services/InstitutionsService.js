'use strict';
define(['ChoPidoTurnos'], function(ChoPidoTurnos) {

  ChoPidoTurnos.service('InstitutionsService', ['$http', function($http) {
    return {
      getInstitutions: function() {
        return $http({
          method: 'GET',
          // change this to general
          url: 'http://localhost:8080/grupo4/api/v1/institutions',
          headers: {
            'content-type': 'application/json'
          },
          data: null
        });
      }
    };
  }]);
});
