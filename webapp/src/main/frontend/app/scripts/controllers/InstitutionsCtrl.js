'use strict';
define(['ChoPidoTurnos', 'services/InstitutionsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('InstitutionsCtrl', ['InstitutionsService', '$stateParams', function(institutionsService, $stateParams) {
    var _this = this;
    this.params = $stateParams;

    institutionsService.getInstitutions().then(
      function(result) {
        console.log(result);
        _this.institutions = result.data;
      }
    );
  }]);
});
