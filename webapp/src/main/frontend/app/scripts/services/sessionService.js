'use strict';

define(['ChoPidoTurnos'], function(ChoPidoTurnos) {
  ChoPidoTurnos.service('sessionService', ['$http', function ($http) {
    function mapTokenToUser(token) {
      return {
        email: token.iss,
        type: token.jti,
        id: token.sub
      };
    }
    function userFromToken() {
      var token = localStorage.getItem('token');
      if (token === null) {
        return null;
      }
      var parsedToken = JSON.parse(atob(token.split('.')[1]));
      return mapTokenToUser(parsedToken);
    }

    return {
      getLoggedUser: function() {
        this.loggedUser = this.loggedUser || userFromToken();
        return this.loggedUser;
      },

      getUserType: function() {
        return this.getLoggedUser().type;
      },

      login: function (data, success, error) {
        var _this = this;

        return $http
          .post('http://localhost:8080/grupo4/api/v1/login', data)
          .then(function (response) {
            if (response.status !== 200) {
              throw response;
            }

            return response.data;
          })
          .then(function (responseData) {
            _this.loggedUser = {
              email: data.email
            };

            localStorage.setItem('token', responseData.token);
            $http.defaults.headers.common.Authorization = responseData.token;
            success(responseData);
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
