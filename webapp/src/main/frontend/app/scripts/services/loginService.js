'use strict';

define(['ChoPidoTurnos'], function(ChoPidoTurnos) {
  ChoPidoTurnos.service('loginService', ['$http', function($http){
    return {
      login: function (data, success, error) {
        console.log(data);
        return $http
          .post('http://localhost:8080/grupo4/api/v1/login', data)
          .then(response => response.data)
          .then(data => {
            localStorage.setItem('token', data.token);
            $http.defaults.headers.common.Authorization = data.token;
            success(data)
          }, error);
      }
    }
  }])
});
