'use strict';

define(['ChoPidoTurnos'], function(ChoPidoTurnos) {
  ChoPidoTurnos.service('sessionService', ['$http', function($http){
    return {
      login: function (data, success, error) {
        return $http
          .post('http://localhost:8080/grupo4/api/v1/login', data)
          .then(function(response) { return response.data; })
          .then(function(data) {
            localStorage.setItem('token', data.token);
            $http.defaults.headers.common.Authorization = data.token;
            success(data)
          }, error);
      },

      register: function (data, success, error) {
        var email = data.email;
        var password = data.password;

        return $http
          .post('http://localhost:8080/grupo4/api/v1/patients', data)
          .then(function(response) { return response.data; })
          .then(function(data) {
            this.login({
              email: email,
              password: password,
              type: 'patient' // TODO: change this
            }, success, error)
          }, error);
      }
    }
  }])
});
