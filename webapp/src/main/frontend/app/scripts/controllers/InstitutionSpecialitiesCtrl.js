'use strict';
define(['ChoPidoTurnos', 'services/institutionsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('InstitutionSpecialitiesCtrl', ['institutionsService', '$stateParams', function (institutionsService, $stateParams) {
    var _this = this;
    this.params = $stateParams;

    institutionsService
      .getInstitution(this.params.institutionId)
      .then(function(result) {
        _this.institution = result.data;
      });

    institutionsService
      .getInstitutionSpecialities(this.params.institutionId)
      .then(function (result) {
        _this.specialities = result.data;
      });
  }]);
});
