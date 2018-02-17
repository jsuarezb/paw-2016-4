'use strict';
define(['ChoPidoTurnos', 'services/InstitutionsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('InstitutionDetailCtrl', ['InstitutionsService', '$stateParams', function (institutionsService, $stateParams) {
    var _this = this;
    this.params = $stateParams;

    institutionsService.getInstitution(this.params.institutionId).then(
      function(result) {
        _this.institution = result.data;
      }
    );
  }]);
});
