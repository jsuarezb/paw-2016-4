'use strict';
define(['ChoPidoTurnos', 'services/DoctorsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('DoctorDetailCtrl', ['DoctorsService', '$routeParams', function(doctorsService, $routeParams) {
    var _this = this;
    this.params = $routeParams;

      doctorsService.getDoctor(this.params.doctorId).then(
        function (result) {
          console.log(result);
          _this.doctor = result.data;
        }
      );
  }]);
});
