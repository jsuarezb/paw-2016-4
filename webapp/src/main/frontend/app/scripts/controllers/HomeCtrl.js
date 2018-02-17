'use strict';
define(['ChoPidoTurnos', 'services/specialitiesService', 'services/neighborhoodsService', 'services/InstitutionsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('HomeCtrl',
    ['SpecialitiesService','NeighborhoodsService', 'InstitutionsService','$scope', '$state',
    function(specialitiesService, neighborhoodsService, institutionsService, $scope, $state) {
      var _this = this;

      institutionsService.getInstitutions().then(
        function(result) {
          $scope.institutions = result.data;
          $scope.selectedInstitution = $scope.institutions[0].id;
        }
      );

      specialitiesService.getSpecialities().then(
        function(result) {
          $scope.specialities = result.data;
          $scope.selectedSpeciality = $scope.specialities[0].id;
        }
      );

      neighborhoodsService.getNeighborhoods().then(
        function(result) {
          $scope.neighborhoods = result.data;
          $scope.selectedNeighborhood = $scope.neighborhoods[0].name;
        }
      );

      $scope.searchAppointments = function() {
        $state.go('appointmentSearch', {
          speciality: $scope.selectedSpeciality,
          neighborhood: $scope.selectedNeighborhood,
          institution: $scope.selectedInstitution
        });
      };

      $scope.searchDoctors = function() {
        $state.go('doctors', {
          firstName: $scope.firstName,
          lastName: $scope.lastName
        });
      };
    }
  ]);
});
