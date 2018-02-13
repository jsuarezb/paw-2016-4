'use strict';
define(['ChoPidoTurnos', 'services/InstitutionsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('InstitutionDetailCtrl', ['InstitutionsService', '$routeParams', function (institutionsService, $routeParams) {
    var _this = this;
    this.params = $routeParams;

    institutionsService.getInstitution(this.params.institutionId).then(
      function(result){
        console.log(result);
        _this.institution = result.data;
      }
    );
  }]);
});
