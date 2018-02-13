'use strict';
define(['ChoPidoTurnos', 'services/InstitutionsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('InstitutionDoctorsCtrl', ['InstitutionsService', '$routeParams', function (institutionsService, $routeParams) {
    var _this = this;
    this.params = $routeParams;

    institutionsService.getInstitution(this.params.institutionId).then(
      function(result){
        console.log(result);
        _this.institution = result.data;
      }
    ),
    institutionsService.getInstitutionDoctors(this.params.institutionId).then(
      function(result) {
        console.log(result);
        _this.doctors = result.data;
      }
    ),
      institutionsService.getInstitutionSpecialities(this.params.institutionId).then(
        function (result) {
          console.log(result);
          _this.specialities = result.data;
        }
      );
  }]);
});
