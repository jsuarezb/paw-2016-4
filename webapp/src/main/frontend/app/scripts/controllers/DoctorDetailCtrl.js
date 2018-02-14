'use strict';
define(['ChoPidoTurnos', 'services/DoctorsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('DoctorDetailCtrl', ['DoctorsService', '$stateParams', function(doctorsService, $stateParams) {
    var _this = this;
    this.params = $stateParams;

      doctorsService.getDoctor(this.params.doctorId).then(
        function (result) {
          console.log(result);
          _this.doctor = result.data;
        }
      );
  }]);
});
