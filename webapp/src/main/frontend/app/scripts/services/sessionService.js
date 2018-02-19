'use strict';

define(['ChoPidoTurnos'], function(ChoPidoTurnos) {
  ChoPidoTurnos.service('sessionService', ['$http', function ($http) {
    function mapTokenToUser(token) {
      return {
        email: token.iss,
        type: token.jti,
        id: token.sub,
        patientId: token.patientId,
        doctorId: token.doctorId
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

      login: function (data, success, error) {
        var _this = this;

        return $http
          .post('/grupo4/api/v1/login', data)
          .then(function (response) {
            if (response.status !== 200) {
              throw response;
            }

            return response.data;
          })
          .then(function (responseData) {
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
        var _this = this;

        return $http
          .post('/grupo4/api/v1/users', data)
          .then(function(response) {
            return response.data;
          })
          .then(function(data) {
            _this.login({
              email: email,
              password: password
            }, success, error);
          }, error);
      }
    };
  }]);
});
