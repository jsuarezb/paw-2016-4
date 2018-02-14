'use strict';
define(['ChoPidoTurnos', 'services/InstitutionsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('InstitutionSpecialitiesCtrl', ['InstitutionsService', '$stateParams', function (institutionsService, $stateParams) {
    var _this = this;
    this.params = $stateParams;

    institutionsService.getInstitution(this.params.institutionId).then(
      function(result) {
        console.log(result);
        _this.institution = result.data;
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
