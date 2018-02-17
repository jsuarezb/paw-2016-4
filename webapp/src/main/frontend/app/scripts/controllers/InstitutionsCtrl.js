'use strict';
define(['ChoPidoTurnos', 'services/institutionsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('InstitutionsCtrl', ['institutionsService', '$stateParams', function(institutionsService, $stateParams) {
    var _this = this;
    this.params = $stateParams;

    institutionsService.getInstitutions().then(
      function(result) {
        _this.institutions = result.data;
      }
    );
  }]);
});
