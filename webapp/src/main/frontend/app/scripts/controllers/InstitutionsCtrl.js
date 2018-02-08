'use strict';
define(['ChoPidoTurnos', 'services/InstitutionsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('InstitutionsCtrl', ['InstitutionsService', function(institutionsService) {
    var _this = this;

    institutionsService.getInstitutions().then(
      function(result){
        _this.institutions = result.data;
      }
    )
  }]);
});
