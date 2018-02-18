'use strict';
define(['ChoPidoTurnos', 'services/institutionsService'], function (ChoPidoTurnos, institutionsService) {
  function SpecialitiesListCtrl($scope, institutionsService) {
    var _this = this;
    this.getSpecialities = function() {
      institutionsService.getInstitutionSpecialities(this.institutionId).then(
        function (result) {
          _this.specialities = result.data;
          $scope = _this.specialities;
        }
      );
    };

    this.handleSpecialitySelected = function (specialityId) {
      this.onSpecialitySelected({specialityId: specialityId});
    };

    this.$onInit = function() {
      this.getSpecialities();
    };
  }

  SpecialitiesListCtrl.$inject = ['$scope', 'institutionsService'];

  ChoPidoTurnos.component('specialityComponent', {
    templateUrl: 'views/Modal/specialitiesListInnerView.html',
    controller: SpecialitiesListCtrl,
    bindings: {
      onSpecialitySelected: '&',
      institutionId: '<',
      doctorId: '<',
    }
  });


});
