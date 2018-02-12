'use strict';
define(['ChoPidoTurnos', 'services/SpecialitiesService', 'services/NeighborhoodsService', 'services/InstitutionsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('HomeCtrl', ['SpecialitiesService','NeighborhoodsService', 'InstitutionsService','$scope', function(specialitiesService,neighborhoodsService,institutionsService,$scope) {
    var _this = this;

    institutionsService.getInstitutions().then(
      function(result) {
        console.log(result);
        $scope.institutions = result.data;
        $scope.selectedInstitution = $scope.institutions[0].id;
      }
    );

    specialitiesService.getSpecialities().then(
      function(result) {
        console.log(result);
        $scope.specialities = result.data;
        $scope.selectedSpeciality = $scope.specialities[0].id;
      }
    );

    neighborhoodsService.getNeighborhoods().then(
      function(result) {
        console.log(result);
        $scope.neighborhoods = result.data;
        $scope.selectedNeighborhoods = $scope.neighborhoods[0].id;
      }
    );

  }]);
});
