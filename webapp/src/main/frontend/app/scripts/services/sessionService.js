'use strict';

define(['ChoPidoTurnos'], function(ChoPidoTurnos) {
  ChoPidoTurnos.service('sessionService', ['$http', function ($http) {
    function userFromToken() {
      var token = localStorage.getItem('token');
      if (token === null) {
        return null;
      }
      var parsedToken = JSON.parse(atob(token.split('.')[1]));
      return {email: parsedToken.iss};
    }

    function typeFromToken() {
      var token = localStorage.getItem('token');
      if (token === null) {
        return null;
      }
      var parsedToken = JSON.parse(atob(token.split('.')[1]));
      return parsedToken.jti;
    }

    return {
      getLoggedUser: function() {
        this.loggedUser = this.loggedUser || userFromToken();
        return this.loggedUser;
      },

      getUserType: function() {
        return typeFromToken();
      },

      login: function (data, success, error) {
        this.loggedUser = {
          email: data.email
        };

        return $http
          .post('http://localhost:8080/grupo4/api/v1/login', data)
          .then(function (response) {
            if (response.status !== 200) {
              throw response;
            }

            return response.data;
          })
          .then(function (data) {
            localStorage.setItem('token', data.token);
            $http.defaults.headers.common.Authorization = data.token;
            success(data);
          }, error);
      },

      logout: function() {
        this.loggedUser = null;
        localStorage.removeItem('token');
        delete $http.defaults.headers.common.Authorization;
      },

      register: function (data, success, error) {
        var email = data.email;
        var password = data.password;

        return $http
          .post('http://localhost:8080/grupo4/api/v1/patients', data)
          .then(function(response) {
            return response.data;
          })
          .then(function(data) {
            this.login({
              email: email,
              password: password,
              type: 'patient' // TODO: change this
            }, success, error);
          }, error);
      }
    };
  }]);
});
